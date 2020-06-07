
public class ExprFactory implements JmmTreeConstants {

    public static Expression build(final SimpleNode node, final Statement parent) throws SemanticException {

        switch (node.getId()) {
            case JJTINTEGER:
                return new ExprIntegerLiteral(node, parent);

            case JJTBOOL:
                return new ExprBooleanLiteral(node, parent);

            case JJTAND:
                return getOperationExpresion(node, "&&", parent);

            case JJTLESSTHAN:
                return getOperationExpresion(node, "<", parent);

            case JJTPLUS:
                return getOperationExpresion(node, "+", parent);

            case JJTMINUS:
                return getOperationExpresion(node, "-", parent);

            case JJTTIMES:
                return getOperationExpresion(node, "*", parent);

            case JJTDIVIDED:
                return getOperationExpresion(node, "/", parent);

            case JJTIDENTIFIER:
                return new ExprIdentifier(node, parent, false);

            case JJTIDENTIFIERINSTRUCTION: {
                if (node.jjtGetNumChildren() == 1)
                    return new ExprIdentifier(node.getFirstChild(), parent, false);
                else
                    return new ExprTermTail(node, parent);
            }

            case JJTNEWARRAY:
                return new ExprNewArray(node, parent);

            case JJTNEWOBJECT:
                return new ExprNewObject(node, parent);

            case JJTTHISREF:
                return new ExprThis(node, parent);

            case JJTNOT:
                return new ExprNot(node, parent);

            case JJTBRACKETEDEXPRESSION:
                return new ExprBracket(node, parent);

            default:
                return null;
        }
    }

    private static Expression getOperationExpresion(final SimpleNode node, final String type, final Statement parent)
            throws SemanticException {
        final SimpleNode lhs = node.jjtGetChild(0);
        final SimpleNode rhs = node.jjtGetChild(1);
        return new ExprOperation(lhs, rhs, type, parent);
    }
}
