
public class ExprBooleanLiteral extends Expression {
    private final boolean value;

    public ExprBooleanLiteral(final SimpleNode node, final Statement parent) {
        super(node, parent);
        this.value = node.booleanVal;
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final ExprBooleanLiteral expr2 = (ExprBooleanLiteral) obj;
            if (this.value == expr2.value)
                return true;
        }
        return false;
    }

    @Override
    public void checkSemantics() throws SemanticException {

    }

    @Override
    public String getReturnType() {
        return "boolean";
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
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isObject() {
        return false;
    }

    @Override
    public boolean isClass() {
        return false;
    }
}
