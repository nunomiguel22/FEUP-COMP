public abstract class StBranch extends Statement {

    protected StCondition condition;

    public StBranch(final SimpleNode node, final DescMethod method, final CompilerData data) throws SemanticException {
        super(node, method, data);
    }

    public StCondition getCondition() {
        return this.condition;
    }

    public abstract void checkSemantics() throws SemanticException;

}
