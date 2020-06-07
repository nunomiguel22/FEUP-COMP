
public class StFactory implements JmmTreeConstants {

    public static Statement build(final SimpleNode node, final DescMethod parent, final CompilerData data)
            throws SemanticException {

        switch (node.getId()) {
            case JJTIDENTIFIERINSTRUCTION: {
                if (node.jjtGetNumChildren() < 2) {
                    final String message = "invalid identifier statement";
                    final int line = node.getFirstChild().line;
                    final int column = node.getFirstChild().column;
                    throw new SemanticException(message, line, column);
                }

                final SimpleNode opNode = node.jjtGetChild(1);

                if (opNode.isType(JJTASSIGNMENTSTATEMENT))
                    return new StAssign(node, parent, data);

                else
                    return new StExpression(node, parent, data);
            }

            case JJTEXPRESSION:
                return new StExpression(node, parent, data);

            case JJTWHILESTATEMENT:
                return new StWhile(node, parent, data);

            case JJTIFSTATEMENT:
                return new StIf(node, parent, data);

            case JJTBLOCKSTATEMENT:
                return new StBlock(node, parent, data);

            default:
                return null;
        }
    }

}
