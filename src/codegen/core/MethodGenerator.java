import java.util.List;

/**
 * Generates code for individual methods
 */
public class MethodGenerator {
    private final CompilerData data;
    private final DescMethod method;
    private int stackCount;
    private int stackMaxCount;
    private final List<Statement> statements;
    private final LocalRegistry localReg;
    private String bytecode;
    private final boolean oFlag;

    public MethodGenerator(final DescMethod method, final CompilerData data, final boolean oFlag) {
        this.method = method;
        this.data = data;
        this.statements = this.data.getStatementList(this.method.getSignature());
        this.localReg = this.data.getLocalRegistry(this.method.getSignature());
        this.stackCount = 0;
        this.stackMaxCount = 0;
        this.oFlag = oFlag;

        this.bytecode = this.generateHeader();
        final String body = this.generateBody();
        this.bytecode += this.generateLimits();
        this.bytecode += body;
        this.bytecode += ".end method\n\n\n";
    }

    public void addStack(final int count) {
        this.stackCount += count;
        if (this.stackCount > this.stackMaxCount)
            this.stackMaxCount = this.stackCount;
    }

    public void removeStack(final int count) {
        this.stackCount -= count;
    }

    private String generateHeader() {
        String header = ".method public ";

        if (method.isMain())
            header += "static main([Ljava/lang/String;)V\n";
        else {
            header += this.method.getName() + "(";
            header += this.generateParamList(this.method.getParameters()) + ")";
            header += this.method.getReturnType().getBytecode() + "\n";
        }
        return header;
    }

    private String generateLimits() {
        String limits = "\t.limit stack " + this.stackMaxCount + "\n";
        limits += "\t.limit locals " + (this.localReg.size() + 1) + "\n";
        return limits;
    }

    private String generateBody() {
        String code = new String();

        for (final Statement statement : statements)
            code += generateStatement(statement);

        return code;
    }

    private String generateStatement(final Statement statement) {
        String code = new String();

        if (statement instanceof StAssign) {
            return new Assignment(this, (StAssign) statement, oFlag) + "\n";
        }

        if (statement instanceof StBlock) {
            final StBlock st = (StBlock) statement;
            for (final Statement stm : st.getBlock())
                code += this.generateStatement(stm);
            return code;
        }

        if (statement instanceof StExpression) {
            final StExpression st = (StExpression) statement;
            code = new ExpressionGenerator(st.getExpression(), this, oFlag).toString();

            if (!st.getExpression().isVoid()) {
                code += "\tpop";
                this.removeStack(1);
            }
            return code + "\n";
        }

        if (statement instanceof StReturn) {
            code = new String();
            final StReturn st = (StReturn) statement;

            if (st.getType().isVoid())
                code += "\treturn\n";

            else {
                code += new ExpressionGenerator(st.getReturnExpression(), this, oFlag);
                code += "\t" + st.getType().getReturnByteCode() + "return\n";
            }

            return code;

        }

        if (statement instanceof StIf) {
            final StIf st = (StIf) statement;

            final Label elseLabel = new Label("Else Statement Body");
            final Label exitLabel = new Label("Exit If Statement");

            code += "\t;IF STATEMENT\n";
            code += "\t;If Condition\n";
            code += new ExpressionGenerator(st.getCondition().getExpr(), this, oFlag).toString();
            code += "\tifeq " + elseLabel + "\n";
            this.removeStack(1);
            code += "\t;Then statement body\n";
            code += this.generateStatement(st.getThenStatement());
            code += "\tgoto " + exitLabel + "\n";
            code += elseLabel.getLabelString() + ":\n";
            code += this.generateStatement(st.getElseStatement());
            code += exitLabel.getLabelString() + "\n";

            return code + "\n";
        }

        if (statement instanceof StWhile) {
            final StWhile st = (StWhile) statement;

            final Label startLabel = new Label("WHILE STATEMENT");
            final Label exitLabel = new Label("Exit While Statement");

            code += startLabel.getLabelString() + "\n";
            code += "\t;While Condition:\n";
            code += new ExpressionGenerator(st.getCondition().getExpr(), this, oFlag);
            code += "\tifeq " + exitLabel + "\n";
            this.removeStack(1);
            code += "\t;While Body:\n";
            code += generateStatement(st.getStatement());

            code += "\tgoto " + startLabel + "\n";
            code += exitLabel.getLabelString() + "\n";
            return code + "\n";
        }

        return code;
    }

    private String generateParamList(final List<DescVar> parameters) {
        String code = new String();

        for (final DescVar param : parameters)
            code += param.getType().getBytecode();

        return code;
    }

    public LocalRegistry getLocalRegistry() {
        return this.localReg;
    }

    public DescMethod getDescriptor() {
        return this.method;
    }

    @Override
    public String toString() {
        return this.bytecode;
    }

}
