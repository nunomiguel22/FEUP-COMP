
public class ExprThis extends Expression {

    private final ExprIdentifier identifier;
    private ExprTermTail termTail;
    private final DescClass parentClass;

    public ExprThis(final SimpleNode node, final Statement parent) throws SemanticException {
        super(node, parent);
        this.identifier = new ExprIdentifier(node, "this", parent);
        this.parentClass = parent.getMethod().getParentClass();
        if (node.jjtGetNumChildren() > 0) {
            final SimpleNode tailNode = node.getFirstChild();
            termTail = new ExprTermTail(this.identifier, tailNode, parent);
        }
    }

    public ExprTermTail getTermTail() {
        return this.termTail;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final ExprThis expr2 = (ExprThis) obj;
            if (this.termTail.equals(expr2.termTail))
                return true;
        }
        return false;
    }

    @Override
    public void checkSemantics() throws SemanticException {

    }

    @Override
    public String getReturnType() {
        if (this.termTail != null)
            return termTail.getReturnType();

        return this.parentClass.getReturnType();
    }

    @Override
    public boolean isInteger() {
        if (this.termTail != null)
            return this.termTail.isInteger();

        return false;
    }

    @Override
    public boolean isVoid() {
        if (this.termTail != null)
            return this.termTail.isVoid();

        return false;
    }

    @Override
    public boolean isBoolean() {
        if (this.termTail != null)
            return this.termTail.isBoolean();

        return false;
    }

    @Override
    public boolean isArray() {
        if (this.termTail != null)
            return this.termTail.isArray();

        return false;
    }

    @Override
    public boolean isObject() {
        if (this.termTail != null)
            return this.termTail.isObject();

        return true;
    }

    @Override
    public boolean isClass() {
        if (this.termTail != null)
            return this.termTail.isClass();
        return false;
    }
}
