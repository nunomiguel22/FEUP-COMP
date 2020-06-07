public class ExprNot extends Expression {

    private final Expression expr;

    public ExprNot(final SimpleNode node, final Statement parent) throws SemanticException {
        super(node, parent);
        final SimpleNode exprNode = node.getFirstChild();
        expr = ExprFactory.build(exprNode, parent);

        this.checkSemantics();

    }

    public Expression getExpression() {
        return this.expr;
    }

    /**
     * Check if negated expression is of boolean type
     */
    @Override
    public void checkSemantics() throws SemanticException {
        if (this.expr != null)
            if (!this.expr.isBoolean()) {
                String msg = "operator '!' is undefined for type '";
                msg += this.expr.getReturnType() + "'";
                throw new SemanticException(msg, this.node.line, this.node.column);
            }
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final ExprNot expr2 = (ExprNot) obj;
            if (this.expr.equals(expr2.expr))
                return true;
        }
        return false;
    }

    @Override
    public String getReturnType() {
        return expr.getReturnType();
    }

    @Override
    public boolean isInteger() {
        return false;
    }

    @Override
    public boolean isVoid() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return true;
    }

    @Override
    public boolean isObject() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isClass() {
        return false;
    }
}
