
public class ExprNewObject extends Expression {

    private final String idName;
    private final DescClass idDesc;
    private final SimpleNode idNode;
    private ExprTermTail termTail;

    public ExprNewObject(final SimpleNode node, final Statement parent) throws SemanticException {
        super(node, parent);
        this.idNode = node.getFirstChild();
        this.idName = idNode.identifierVal;
        this.idDesc = this.parent.data.table.resolveClass(idName);
        this.checkSemantics();

        if (node.jjtGetNumChildren() > 1 && this.idDesc != null) {
            final SimpleNode tailNode = node.jjtGetChild(1);
            termTail = new ExprTermTail(this, tailNode, parent);
        }
    }

    public String getIdentifierName() {
        return this.idName;
    }

    public ExprTermTail getTermTail() {
        return this.termTail;
    }

    public DescClass getClassDescriptor() {
        return this.idDesc;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final ExprNewObject expr2 = (ExprNewObject) obj;
            if (this.idName.equals(expr2.idName))
                if (this.termTail != null && expr2.termTail != null && this.termTail.equals(expr2.termTail))
                    return true;
        }
        return false;
    }

    /**
     * Check if the new object identifier is a valid class
     */
    @Override
    public void checkSemantics() throws SemanticException {
        if (this.idDesc == null) {
            final String message = "'" + this.idName + "' is not a class";
            throw new SemanticException(message, this.idNode.line, this.idNode.column);
        }
    }

    @Override
    public String getReturnType() {
        if (this.termTail != null)
            return termTail.getReturnType();

        return this.idName;
    }

    @Override
    public boolean isInteger() {
        if (this.termTail != null)
            return this.termTail.isInteger();

        return false;
    }

    @Override
    public boolean isVoid() {
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
