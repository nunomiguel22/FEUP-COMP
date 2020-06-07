public class StReturn extends Statement {

    private final Expression returnExpr;
    private final DescType type;

    public StReturn(final SimpleNode returnNode, final DescMethod parent, final CompilerData data)
            throws SemanticException {
        super(returnNode, parent, data);
        final SimpleNode exprNode = returnNode.getFirstChild().getFirstChild();

        this.returnExpr = ExprFactory.build(exprNode, this);
        this.type = this.getMethod().getReturnType();
    }

    public StReturn(final DescMethod parent, final CompilerData data) {
        super(null, parent, data);
        this.returnExpr = null;
        this.type = new DescType("void");
    }

    public Expression getReturnExpression() {
        return this.returnExpr;
    }

    public DescType getType() {
        return this.type;
    }

    @Override
    public void checkSemantics() throws SemanticException {

    }
}
