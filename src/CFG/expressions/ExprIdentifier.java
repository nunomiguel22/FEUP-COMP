
public class ExprIdentifier extends Expression {

    private final String identifier;
    private final Descriptor descriptor;
    private final boolean def;

    private enum idType {
        PARAMETER, MEMBER, LOCAL, CLASS, THIS
    };

    private idType type;

    public ExprIdentifier(final SimpleNode node, final Statement parent, final boolean def) throws SemanticException {
        super(node, parent);
        this.identifier = node.identifierVal;
        this.def = def;
        this.descriptor = resolveIdentifier(this.identifier);
        this.checkSemantics();
        this.parent.line = this.node.line;
        this.parent.column = this.node.column;
    }

    public ExprIdentifier(final SimpleNode node, final String name, final Statement parent) throws SemanticException {
        super(node, parent);
        this.identifier = name;
        this.type = idType.THIS;
        this.descriptor = parent.getMethod().getParentClass();
        this.def = false;
        this.parent.line = this.node.line;
        this.parent.column = this.node.column;
    }

    private Descriptor resolveIdentifier(final String name) {

        final DescMethod method = this.parent.getMethod();
        final DescClass pclass = method.getParentClass();
        final SymbolTable sym = this.parent.data.table;

        if (method.hasLocal(identifier)) {
            this.type = idType.LOCAL;
            final DescVar var = method.getLocal(identifier);
            if (!def)
                this.parent.addVarUse(var);
            return var;
        }
        if (method.hasParameter(identifier)) {
            this.type = idType.PARAMETER;
            final DescVar var = method.getParameter(identifier);
            if (!def)
                this.parent.addVarUse(var);
            return var;
        }
        if (pclass.hasMember(identifier)) {
            this.type = idType.MEMBER;
            return pclass.getMember(identifier);
        }
        if (sym.hasClass(identifier)) {
            this.type = idType.CLASS;
            return sym.resolveClass(identifier);
        }
        return null;
    }

    public DescType getType() {
        if (this.isClass())
            return null;

        final DescVar var = (DescVar) descriptor;
        return var.getType();
    }

    public String getIdentifierName() {
        return this.identifier;
    }

    public boolean isLocal() {
        return this.type == idType.LOCAL;
    }

    public boolean isParameter() {
        return this.type == idType.PARAMETER;
    }

    public boolean isMember() {
        return this.type == idType.MEMBER;
    }

    public idType getIdentifierType() {
        return this.type;
    }

    public boolean isVariable() {
        return this.isLocal() || this.isParameter() || this.isMember();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final ExprIdentifier expr2 = (ExprIdentifier) obj;
            if (this.identifier.equals(expr2.identifier))
                return true;
        }
        return false;
    }

    /**
     * Check if identifier can be resolved
     * 
     * Variable is undefined
     *
     * Warning on used variable being defined in a ``if-else`` or ``while``
     * statement
     */
    @Override
    public void checkSemantics() throws SemanticException {
        if (descriptor == null) {
            final String message = "could not resolve identifier '" + this.identifier + "'";
            throw new SemanticException(message, this.node.line, this.node.column);
        }
    }

    @Override
    public String getReturnType() {
        if (this.isClass()) {
            final DescClass cls = (DescClass) descriptor;
            return cls.getReturnType();
        }

        return getType().toString();
    }

    @Override
    public boolean isInteger() {

        if (this.isClass())
            return false;
        else {
            final DescVar var = (DescVar) descriptor;
            if (var.getReturnType().equals("int"))
                return true;
        }
        return false;
    }

    @Override
    public boolean isBoolean() {

        if (this.isClass())
            return false;
        else {
            final DescVar var = (DescVar) descriptor;
            if (var.getReturnType().equals("boolean"))
                return true;
        }
        return false;
    }

    @Override
    public boolean isArray() {

        if (this.isClass())
            return false;
        else {
            final DescVar var = (DescVar) descriptor;
            if (var.getReturnType().equals("int[]"))
                return true;
        }
        return false;
    }

    @Override
    public boolean isVoid() {
        return false;
    }

    @Override
    public boolean isObject() {
        if (this.isClass())
            return false;
        if (isInteger() || isBoolean())
            return false;
        return true;
    }

    @Override
    public boolean isClass() {
        return (this.descriptor instanceof DescClass);
    }
}
