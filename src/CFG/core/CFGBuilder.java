import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Builds Control Flow Graphs for a given method
 */
public class CFGBuilder implements JmmTreeConstants {
    private CompilerData data;
    private SymbolTable sym;
    private DescClass cclass;
    private Map<MethodSignature, DescMethod> methods;

    private CFGBlock currBlock;

    public CFGBuilder(CompilerData data) {
        this.data = data;
        this.sym = this.data.table;
        this.cclass = this.sym.getCompClass();
        this.methods = this.cclass.getMethods();
    }

    public void build() throws CompilerFailureException {
        for (DescMethod method : methods.values()) {
            CFG cfg = new CFG(method, this.data);
            this.currBlock = cfg.getEntry();
            this.buildMethodCFG(method, cfg);
            cfg.cleanup(cfg.getEntry(), new ArrayList<CFGBlock>());
            cfg.linkEdges(cfg.getEntry(), new ArrayList<CFGBlock>());
            this.data.addCFG(method.getSignature(), cfg);
        }
    }

    private void buildMethodCFG(DescMethod method, CFG cfg) throws CompilerFailureException {
        SimpleNode methodNode = method.getNode();
        SimpleNode bodyNode = methodNode.jjtGetChild(1);
        SimpleNode returnNode = null;
        if (methodNode.jjtGetNumChildren() == 3)
            returnNode = methodNode.jjtGetChild(2);

        List<Statement> stList = new ArrayList<Statement>();

        for (int i = 0; i < bodyNode.jjtGetNumChildren(); ++i) {
            if (!isIdentifierVarDeclaration(bodyNode.jjtGetChild(i))) {
                Statement st;
                try {
                    st = StFactory.build(bodyNode.jjtGetChild(i), method, this.data);
                } catch (SemanticException e) {
                    this.errorCheck(e);
                    continue;
                }
                stList.add(st);
                this.addStatement(method, cfg, st);
            }
        }

        Statement ret;
        try {
            ret = (returnNode == null) ? new StReturn(method, this.data) : new StReturn(returnNode, method, this.data);
            stList.add(ret);
            this.addStatement(method, cfg, ret);
            cfg.setExit(this.currBlock);
        } catch (SemanticException e) {
            this.errorCheck(e);
        }

        this.data.addStatementList(method.getSignature(), stList);

    }

    private void addStatement(DescMethod method, CFG cfg, Statement st) {
        if (st instanceof StBranch)
            this.buildBranchSt(method, cfg, st);
        else if (st instanceof StBlock)
            this.buildBlockSt(method, cfg, st);
        else
            this.currBlock.addStatement(st);
    }

    private void buildBranchSt(DescMethod method, CFG cfg, Statement st) {
        if (st instanceof StIf)
            this.buildIfSt(method, cfg, st);
        else if (st instanceof StWhile)
            this.buildWhileSt(method, cfg, st);

    }

    private void buildBlockSt(DescMethod method, CFG cfg, Statement st) {
        StBlock blc = (StBlock) st;
        for (Statement statement : blc.getBlock())
            this.addStatement(method, cfg, statement);
    }

    private void buildIfSt(DescMethod method, CFG cfg, Statement st) {
        CFGBlock startingBlock = this.currBlock;

        StIf ifSt = (StIf) st;
        StCondition cond = ifSt.getCondition();
        Statement thenBody = ifSt.getThenStatement();
        Statement elseBody = ifSt.getElseStatement();

        this.currBlock.addStatement(cond);

        CFGBlock thenBlock = new CFGBlock();
        this.currBlock = thenBlock;
        this.addStatement(method, cfg, thenBody);
        new CFGLink(startingBlock, thenBlock, false);
        CFGBlock thenExit = this.currBlock;

        CFGBlock elseBlock = new CFGBlock();
        this.currBlock = elseBlock;
        this.addStatement(method, cfg, elseBody);
        new CFGLink(startingBlock, elseBlock, false);
        CFGBlock elseExit = this.currBlock;

        CFGBlock exitBlock = new CFGBlock();
        new CFGLink(thenExit, exitBlock, false);
        new CFGLink(elseExit, exitBlock, false);

        this.currBlock = exitBlock;
    }

    private void buildWhileSt(DescMethod method, CFG cfg, Statement st) {
        StWhile whileSt = (StWhile) st;
        StCondition cond = whileSt.getCondition();
        Statement whileBody = whileSt.getStatement();

        CFGBlock condBlock;
        if (this.currBlock.isEmpty()) {
            this.currBlock.addStatement(cond);
            condBlock = this.currBlock;
        } else {
            condBlock = new CFGBlock();
            condBlock.addStatement(cond);
            new CFGLink(this.currBlock, condBlock, false);
        }

        CFGBlock bodyBlock = new CFGBlock();
        this.currBlock = bodyBlock;
        this.addStatement(method, cfg, whileBody);

        new CFGLink(condBlock, bodyBlock, false);
        new CFGLink(this.currBlock, condBlock, true);

        CFGBlock exitBlock = new CFGBlock();
        new CFGLink(condBlock, exitBlock, false);

        this.currBlock = exitBlock;
    }

    private void errorCheck(SemanticException e) throws CompilerFailureException {
        Compiler.printSemanticError(e, ++this.data.errors);
        if (Compiler.errors >= 10) {
            Compiler.printExitMessage();
            throw new CompilerFailureException();
        }
    }

    private boolean isIdentifierVarDeclaration(SimpleNode node) {
        if (node.isType(JJTWHILESTATEMENT))
            return false;

        if (node.isType(JJTIFSTATEMENT))
            return false;

        return node.jjtGetChild(node.jjtGetNumChildren() - 1).isType(JJTVARDECLARATION);
    }
}
