public class ExprLength extends Expression {

    private final Expression lhs;

    public ExprLength(final Expression expr, final SimpleNode node, final Statement parent) throws SemanticException {
        super(node, parent);
        this.lhs = expr;
        this.checkSemantics();

    }

    public Expression getLhs() {
        return this.lhs;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final ExprLength expr2 = (ExprLength) obj;
            if (this.lhs.equals(expr2.lhs))
                return true;
        }
        return false;
    }

    /**
     * Check if left hand side of length expression is an array type
     */
    @Override
    public void checkSemantics() throws SemanticException {
        if (!this.lhs.isArray()) {
            final int line = this.node.getFirstChild().getFirstChild().line;
            final int column = this.node.getFirstChild().getFirstChild().column;

            final String msg = "not possible to get length of non array elements";
            throw new SemanticException(msg, line, column);
        }
    }

    @Override
    public String getReturnType() {
        return "int";
    }

    @Override
    public boolean isInteger() {
        return true;
    }

    @Override
    public boolean isVoid() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return false;
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
