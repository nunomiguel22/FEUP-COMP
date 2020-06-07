
public class ExprNewArray extends Expression {
    private final Expression expr;
    private final SimpleNode exprNode;

    public ExprNewArray(final SimpleNode node, final Statement parent) throws SemanticException {
        super(node, parent);
        this.exprNode = node.getFirstChild().getFirstChild().getFirstChild();
        expr = ExprFactory.build(this.exprNode, parent);

        this.checkSemantics();
    }

    public Expression getAccessExpression() {
        return this.expr;
    }

    /**
     * Check if expression in: new int[expression], is numeric
     */
    @Override
    public void checkSemantics() throws SemanticException {
        if (!expr.isInteger()) {
            int line = this.exprNode.line;
            int column = this.exprNode.column;
            if (this.exprNode.isType(JmmTreeConstants.JJTIDENTIFIERINSTRUCTION)) {
                line = this.exprNode.getFirstChild().line;
                column = this.exprNode.getFirstChild().column;
            }

            String message = "type mismatch: cannot convert " + expr.getReturnType();
            message += " to int";
            throw new SemanticException(message, line, column);
        }
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final ExprNewArray expr2 = (ExprNewArray) obj;
            if (this.expr.equals(expr2.expr))
                return true;
        }
        return false;
    }

    @Override
    public String getReturnType() {
        return "int[]";
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
        return false;
    }

    @Override
    public boolean isArray() {
        return true;
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
