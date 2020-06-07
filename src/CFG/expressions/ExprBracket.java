public class ExprBracket extends Expression {

    private final Expression expression;

    public ExprBracket(final SimpleNode node, final Statement parent) throws SemanticException {
        super(node, parent);
        final SimpleNode child = node.getFirstChild().getFirstChild();
        this.expression = ExprFactory.build(child, parent);
    }

    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final ExprBracket expr2 = (ExprBracket) obj;
            if (this.expression.equals(expr2.expression))
                return true;
        }
        return false;
    }

    @Override
    public void checkSemantics() throws SemanticException {

    }

    @Override
    public String getReturnType() {
        return this.expression.getReturnType();
    }

    @Override
    public boolean isInteger() {
        return this.expression.isInteger();
    }

    @Override
    public boolean isVoid() {
        return this.expression.isVoid();
    }

    @Override
    public boolean isBoolean() {
        return this.expression.isBoolean();
    }

    @Override
    public boolean isObject() {
        return this.expression.isObject();
    }

    @Override
    public boolean isArray() {
        return this.expression.isArray();
    }

    @Override
    public boolean isClass() {
        return this.expression.isClass();
    }
}
