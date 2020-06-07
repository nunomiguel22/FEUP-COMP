
public class ExprArrayAccess extends Expression {
    private final Expression lhs;
    private final Expression accessExp;

    public ExprArrayAccess(final SimpleNode lhs, final SimpleNode exp, final Statement parent)
            throws SemanticException {
        super(lhs, parent);
        this.lhs = new ExprIdentifier(lhs, parent, false);
        this.accessExp = ExprFactory.build(exp, parent);

        this.checkSemantics();
    }

    public ExprArrayAccess(final Expression lhs, final SimpleNode node, final Statement parent)
            throws SemanticException {
        super(node, parent);
        this.lhs = lhs;
        final SimpleNode expNode = node.getFirstChild().getFirstChild().getFirstChild();
        this.accessExp = ExprFactory.build(expNode, parent);

        this.checkSemantics();
    }

    public Expression getLhs() {
        return this.lhs;
    }

    public Expression getAcessExpr() {
        return this.accessExp;
    }

    /**
     * Check if array access is used on non-array expression
     * 
     * Check if expression in: arrayVar[expression], is numeric
     */
    @Override
    public void checkSemantics() throws SemanticException {
        //
        if (!this.lhs.isArray()) {
            final String message = "'" + this.lhs.getReturnType() + "' is not an array type";
            throw new SemanticException(message, this.node.line, this.node.column);
        }

        //
        if (!this.accessExp.isInteger()) {
            final String message = "array access expression is not numeric";
            throw new SemanticException(message, this.node.line, this.node.column);
        }
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final ExprArrayAccess expr2 = (ExprArrayAccess) obj;
            if (this.lhs.equals(expr2.lhs) && this.accessExp.equals(expr2.accessExp))
                return true;
        }
        return false;
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
