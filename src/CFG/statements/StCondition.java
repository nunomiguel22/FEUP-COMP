public class StCondition extends Statement {
    private Expression expr;

    public StCondition(final SimpleNode node, final DescMethod method, final CompilerData data)
            throws SemanticException {
        super(node, method, data);
        if (node.isType(JmmTreeConstants.JJTEXPRESSION))
            this.expr = ExprFactory.build(node.getFirstChild(), this);
        else
            this.expr = ExprFactory.build(node, this);

        this.checkSemantics();

    }

    public Expression getExpr() {
        return this.expr;
    }

    @Override
    public void checkSemantics() throws SemanticException {
        if (!this.expr.isBoolean()) {
            final String msg = "conditional expression is not a boolean type";
            throw new SemanticException(msg, this.node.line, this.node.column);
        }

    }
}
