public class ExprUnknown extends Expression {
    DescType type;

    public ExprUnknown(final DescType type) {
        this.type = type;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return true;
        }
        return false;
    }

    @Override
    public void checkSemantics() throws SemanticException {

    }

    @Override
    public String getReturnType() {
        return this.type.toString();
    }

    @Override
    public boolean isInteger() {
        return this.type.isInteger();
    }

    @Override
    public boolean isVoid() {
        return this.type.isVoid();
    }

    @Override
    public boolean isBoolean() {
        return this.type.isBoolean();
    }

    @Override
    public boolean isArray() {
        return this.type.isArray();
    }

    @Override
    public boolean isObject() {
        return this.type.isObject();
    }

    @Override
    public boolean isClass() {
        return this.type.isClass();
    }
}
