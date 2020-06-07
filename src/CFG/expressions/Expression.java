public abstract class Expression implements Descriptor {
    protected Statement parent;
    protected SimpleNode node;
    protected CompilerData data;

    public Expression(final SimpleNode node, final Statement parent) {
        this.parent = parent;
        this.node = node;
        this.data = parent.getCompilerData();
    }

    public Expression() {

    }

    public SimpleNode getNode() {
        return this.node;
    }

    public Statement getParentStatement() {
        return parent;
    }

    public static boolean hasSameType(final Expression exp1, final Expression exp2) {
        if (exp1 == null || exp2 == null)
            return false;

        if (exp1.isInteger() && exp2.isInteger())
            return true;

        if (exp1.isBoolean() && exp2.isBoolean())
            return true;

        if (exp1.isArray() && exp2.isArray())
            return true;

        if ((exp1.isObject() && exp2.isObject()) || (exp1.isClass() && exp2.isClass())) {
            if (exp1.getReturnType().equals(exp2.getReturnType()))
                return true;
        }

        return false;
    }

    public abstract void checkSemantics() throws SemanticException;
}
