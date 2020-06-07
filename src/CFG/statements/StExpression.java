
public class StExpression extends Statement {
    private Expression expr;

    public StExpression(final SimpleNode node, final DescMethod parent, final CompilerData data)
            throws SemanticException {
        super(node, parent, data);
        if (node.isType(JmmTreeConstants.JJTEXPRESSION))
            this.expr = ExprFactory.build(node.getFirstChild(), this);
        else
            this.expr = ExprFactory.build(node, this);
    }

    public Expression getExpression() {
        return this.expr;
    }

    @Override
    public void checkSemantics() throws SemanticException {

    }
}
