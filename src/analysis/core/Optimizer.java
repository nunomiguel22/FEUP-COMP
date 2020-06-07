import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Optimizes code structure.
 */
public class Optimizer {
    private final CompilerData data;
    private final boolean oFlag;
    private final int regCount;
    private final DescClass cls;
    private final Map<MethodSignature, DescMethod> methods;

    /**
     * Optimizer constructor
     * 
     * @param data     - Compiler data
     * @param oFlag    - True to perform optimizations
     * @param regCount - Limit of registers used
     */
    public Optimizer(final CompilerData data, final boolean oFlag, final int regCount) {
        this.data = data;
        this.cls = this.data.table.getCompClass();
        this.methods = this.cls.getMethods();
        this.oFlag = oFlag;
        this.regCount = regCount;
    }

    /**
     * Perform optimizations
     * 
     * @throws CompilerFailureException
     */
    public void optimize() throws CompilerFailureException {
        this.buildMethodData();
    }

    /**
     * Builds optimizations
     * 
     * @throws CompilerFailureException
     */
    private void buildMethodData() throws CompilerFailureException {
        for (final DescMethod method : this.methods.values()) {
            this.buildLocalRegistry(method);
            this.buildVarDefTable(method);
            this.buildLivenessTables(method);
            if (regCount > 0) {
                final InterferenceGraph g = new InterferenceGraph(data.getMethodLiveness(method.getSignature()), data);
                final LocalRegistry l = g.getOptimizedRegistry();
                if (l.size() > regCount) {
                    System.out.println(l.size() + " register(s) needed, limit is " + regCount);
                    throw new CompilerFailureException();
                }
                this.data.addLocalRegistry(method.getSignature(), l);
            }
        }
    }

    /**
     * Builds default local registry with no optimizations(args + locals)
     * 
     * @param method
     */
    private void buildLocalRegistry(final DescMethod method) {
        int index = 1;
        final LocalRegistry lr = new LocalRegistry(method);

        for (final DescVar arg : method.getParameters()) {
            lr.setVarIndex(arg, index, true);
            ++index;
        }

        for (final DescVar local : method.getLocalMap().values()) {
            lr.setVarIndex(local, index, false);
            ++index;
        }

        this.data.addLocalRegistry(method.getSignature(), lr);
    }

    /**
     * Builds a table with assigned expressions for each variable at each statement
     * 
     * @param method
     * @throws CompilerFailureException
     */
    private void buildVarDefTable(final DescMethod method) throws CompilerFailureException {
        final Statement entry = this.data.getCFG(method.getSignature()).getEntry().getFirstStatement();
        if (entry == null)
            return;
        final LocalRegistry lr = this.data.getLocalRegistry(method.getSignature());
        final List<DescVar> vars = lr.getAllVars();

        entry.setVarDefTable(new VarDefTable(method, this.data));
        try {
            this.buildVarDef(entry, vars, lr, new ArrayList<Statement>());
        } catch (final SemanticException e) {
            this.errorCheck(e);
        }
    }

    /**
     * Builds an individual variable table
     * 
     * @param st
     * @param vars
     * @param lr
     * @param visited
     * @throws SemanticException
     */
    private void buildVarDef(final Statement st, final List<DescVar> vars, final LocalRegistry lr,
            final List<Statement> visited) throws SemanticException {

        for (final StatementLink pred : st.getPredecessors())
            if (!visited.contains(pred.source) && !pred.isBackEdge())
                return;

        visited.add(st);

        VarDefTable table = new VarDefTable(st.getMethod(), this.data);

        if (st.hasNonLoopPredecessors()) {
            if (!st.getPredecessors().get(0).isBackEdge())
                table = new VarDefTable(st.getPredecessors().get(0).source.getVarDefTable());
            for (int i = 1; i < st.getPredecessors().size(); ++i)
                if (!st.getPredecessors().get(i).isBackEdge())
                    table = new VarDefTable(table, st.getPredecessors().get(i).source.getVarDefTable());
            st.setVarDefTable(table);
        }

        for (final DescVar var : st.getVarUses()) {
            if (st.getVarDefTable().getVarDef(var) == null)
                throw new SemanticException(var.getName() + " may not be initialized!", st.line, st.column);
        }

        if (st instanceof StAssign) {
            final StAssign sta = (StAssign) st;
            st.getVarDefTable().addVarDef(lr.getVar(sta.getAssigneeName()), sta.getExpression());
        }

        if (st.hasSuccessors()) {
            for (final StatementLink suc : st.getSuccessors()) {
                if (suc.isBackEdge())
                    continue;
                buildVarDef(suc.target, vars, lr, visited);
            }
        }
    }

    /**
     * builds tables with variable lifeness information
     * 
     * @param method
     */
    private void buildLivenessTables(final DescMethod method) {
        final Liveness liv = new Liveness(method, this.data);
        liv.build();
        this.data.addMethodLiveness(method.getSignature(), liv);
    }

    /**
     * Checks if compiler has passed the error threshold
     * 
     * @param e
     * @throws CompilerFailureException
     */
    private void errorCheck(final SemanticException e) throws CompilerFailureException {
        Compiler.printSemanticError(e, ++this.data.errors);
        if (Compiler.errors >= 10) {
            Compiler.printExitMessage();
            throw new CompilerFailureException();
        }
    }

}
