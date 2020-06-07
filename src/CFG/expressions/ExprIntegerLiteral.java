
public class ExprIntegerLiteral extends Expression {
    private final int value;

    public ExprIntegerLiteral(final SimpleNode node, final Statement parent) {
        super(node, parent);
        this.value = this.node.integerVal;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final ExprIntegerLiteral expr2 = (ExprIntegerLiteral) obj;
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
