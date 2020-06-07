
public class StAssign extends Statement {
    private final String idName;
    private Expression idexpr;
    private final SimpleNode idNode;
    private final Expression exp;

    public StAssign(final SimpleNode node, final DescMethod parent, final CompilerData data) throws SemanticException {
        super(node, parent, data);
        this.idNode = node.getFirstChild();
        final SimpleNode opNode = node.jjtGetChild(1);
        final SimpleNode expNode = opNode.jjtGetChild(opNode.jjtGetNumChildren() - 1).getFirstChild();

        this.idName = this.idNode.identifierVal;

        this.exp = ExprFactory.build(expNode, this);

        if (opNode.jjtGetNumChildren() == 2) {
            final SimpleNode accessNode = opNode.getFirstChild().getFirstChild().getFirstChild();
            this.idexpr = new ExprArrayAccess(this.idNode, accessNode, this);
        } else
            this.idexpr = new ExprIdentifier(this.idNode, this, true);

        this.checkSemantics();
    }

    public String getAssigneeName() {
        return this.idName;
    }

    public Expression getIdentifierExpression() {
        return this.idexpr;
    }

    public Expression getExpression() {
        return this.exp;
    }

    public boolean isArrayAssignment() {
        return (this.idexpr instanceof ExprArrayAccess);
    }

    /**
     * Check if both sides have the same type
     */
    @Override
    public void checkSemantics() throws SemanticException {
        if (!Expression.hasSameType(this.idexpr, this.exp)) {
            String message = "cannot assign " + this.idexpr.getReturnType();
            message += " to " + this.exp.getReturnType();
            throw new SemanticException(message, this.idNode.line, this.idNode.column);
        }
    }
}
