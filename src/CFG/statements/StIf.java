public class StIf extends StBranch {

    private final Statement thenStatement;
    private final Statement elseStatement;

    public StIf(final SimpleNode node, final DescMethod parent, final CompilerData data) throws SemanticException {
        super(node, parent, data);
        final SimpleNode conditionalNode = node.getFirstChild().getFirstChild();
        this.condition = new StCondition(conditionalNode, parent, data);

        final SimpleNode ifChild = node.jjtGetChild(1);
        this.thenStatement = StFactory.build(ifChild, parent, data);

        final SimpleNode elseChild = node.jjtGetChild(2);
        this.elseStatement = StFactory.build(elseChild, parent, data);
    }

    public Statement getThenStatement() {
        return this.thenStatement;
    }

    public Statement getElseStatement() {
        return this.elseStatement;
    }

    /**
     * Check if conditional expression is boolean type
     */
    @Override
    public void checkSemantics() throws SemanticException {
    }
}
