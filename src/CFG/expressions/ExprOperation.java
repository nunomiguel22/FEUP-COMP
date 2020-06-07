
public class ExprOperation extends Expression {
    private final Expression lhs;
    private final Expression rhs;
    private final String op;

    private boolean isNumericExp;
    private boolean isBooleanExp;

    public ExprOperation(final SimpleNode lhs, final SimpleNode rhs, final String op, final Statement parent)
            throws SemanticException {
        super(lhs.jjtGetParent(), parent);
        this.lhs = ExprFactory.build(lhs, parent);
        this.rhs = ExprFactory.build(rhs, parent);
        this.op = op;
        this.isNumericExp = false;
        this.isBooleanExp = false;

        this.checkSemantics();

    }

    private void throwIncompatibleOp(final int line, final int column) throws SemanticException {
        String msg = "operator '" + op + "' is undefined for types '" + this.lhs.getReturnType();
        msg += "', '" + this.rhs.getReturnType() + "'";
        throw new SemanticException(msg, line, column);
    }

    public Expression getLhs() {
        return this.lhs;
    }

    public Expression getRhs() {
        return this.rhs;
    }

    public String getOperation() {
        return this.op;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final ExprOperation expr2 = (ExprOperation) obj;
            if (this.lhs.equals(expr2.lhs) && this.op.equals(expr2.op) && this.rhs.equals(expr2.rhs))
                return true;
        }
        return false;
    }

    /**
     * Check if both sides of '&&' operations are boolean
     * 
     * Check if both sides of '+', '-', '*', '/', '<' operations are numeric
     */
    @Override
    public void checkSemantics() throws SemanticException {
        if (this.lhs != null && this.rhs != null) {
            switch (op) {
                case "&&": {
                    isBooleanExp = true;
                    if (!this.lhs.isBoolean() || !this.rhs.isBoolean())
                        throwIncompatibleOp(this.node.line, this.node.column);
                    break;
                }
                case "<": {
                    isBooleanExp = true;
                    if (!this.lhs.isInteger() || !this.rhs.isInteger())
                        throwIncompatibleOp(this.node.line, this.node.column);
                    break;
                }
                default: {
                    isNumericExp = true;
                    if (!this.lhs.isInteger() || !this.rhs.isInteger())
                        throwIncompatibleOp(this.node.line, this.node.column);
                    break;
                }
            }
        }
    }

    @Override
    public String getReturnType() {
        if (this.isBoolean())
            return "boolean";
        else
            return "int";
    }

    @Override
    public boolean isInteger() {
        return isNumericExp;
    }

    @Override
    public boolean isVoid() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return isBooleanExp;
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
