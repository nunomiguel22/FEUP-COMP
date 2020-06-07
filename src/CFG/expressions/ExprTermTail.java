
public class ExprTermTail extends Expression {

    private Expression expr;

    public ExprTermTail(final SimpleNode node, final Statement parent) throws SemanticException {
        super(node.getFirstChild(), parent);

        final SimpleNode identifierNode = node.getFirstChild();
        final SimpleNode termTailNode = node.jjtGetChild(1);
        final SimpleNode opNode = termTailNode.getFirstChild();
        final Expression idExpr = ExprFactory.build(identifierNode, parent);

        if (opNode.isType(JmmTreeConstants.JJTARRAYACCESS)) {
            final SimpleNode exprNode = opNode.getFirstChild().getFirstChild();
            this.expr = new ExprArrayAccess(identifierNode, exprNode, parent);
        } else if (opNode.isType(JmmTreeConstants.JJTFUNCTION)) {
            final SimpleNode funcType = opNode.getFirstChild();
            if (funcType.isType(JmmTreeConstants.JJTLENGTH))
                this.expr = new ExprLength(idExpr, identifierNode, parent);
            else
                this.expr = new ExprFunction(idExpr, opNode, parent);
        }
    }

    public ExprTermTail(final Expression identifier, final SimpleNode node, final Statement parent)
            throws SemanticException {
        super(identifier.getNode(), parent);
        final SimpleNode child = node.getFirstChild();

        if (child.isType(JmmTreeConstants.JJTFUNCTION)) {
            if (child.getFirstChild().isType(JmmTreeConstants.JJTLENGTH))
                this.expr = new ExprLength(identifier, node, parent);
            else
                this.expr = new ExprFunction(identifier, node, parent);
        } else if (child.isType(JmmTreeConstants.JJTARRAYACCESS)) {
            this.expr = new ExprArrayAccess(identifier, node, parent);
        }
    }

    public Expression getExpression() {
        return this.expr;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final ExprTermTail expr2 = (ExprTermTail) obj;
            if (this.expr.equals(expr2.expr))
                return true;
        }
        return false;
    }

    @Override
    public void checkSemantics() throws SemanticException {

    }

    @Override
    public String getReturnType() {
        return expr.getReturnType();
    }

    @Override
    public boolean isInteger() {
        return expr.isInteger();
    }

    @Override
    public boolean isVoid() {
        return expr.isVoid();
    }

    @Override
    public boolean isBoolean() {
        return expr.isBoolean();
    }

    @Override
    public boolean isArray() {
        return expr.isArray();
    }

    @Override
    public boolean isObject() {
        return expr.isObject();
    }

    @Override
    public boolean isClass() {
        return expr.isClass();
    }
}
