public class StWhile extends StBranch {

    private final Statement statement;

    public StWhile(final SimpleNode node, final DescMethod parent, final CompilerData data) throws SemanticException {
        super(node, parent, data);

        final SimpleNode conditionalNode = node.getFirstChild().getFirstChild();
        this.condition = new StCondition(conditionalNode, parent, data);
        this.checkSemantics();

        final SimpleNode block = node.jjtGetChild(1);
        this.statement = StFactory.build(block, parent, data);
    }

    public Statement getStatement() {
        return this.statement;
    }

    /**
     * Check if conditional expression is boolean type
     */
    @Override
    public void checkSemantics() throws SemanticException {

    }
}
