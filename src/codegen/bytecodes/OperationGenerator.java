
public class OperationGenerator {

    private final Expression lhs;
    private final Expression rhs;
    private final String op;
    private final MethodGenerator method;
    private final boolean oFlag;

    public OperationGenerator(final ExprOperation exp, final MethodGenerator method, final boolean oFlag) {
        this.lhs = exp.getLhs();
        this.rhs = exp.getRhs();
        this.op = exp.getOperation();
        this.method = method;
        this.oFlag = oFlag;
    }

    private String handleExpression(final Expression expr) {
        return new ExpressionGenerator(expr, method, oFlag) + "";
    }

    @Override
    public String toString() {

        String code = new String();

        if (oFlag) {
            if (op.equals("-") && (lhs instanceof ExprIntegerLiteral)) {
                final ExprIntegerLiteral exp = (ExprIntegerLiteral) lhs;
                if (exp.getValue() == 0)
                    return code = handleExpression(rhs) + "\tineg\n";
            }

            if (op.equals("&&")) {
                code += handleExpression(lhs);
                code += handleExpression(rhs);
                code += "\tiand\n";
                return code;
            }

            if (op.equals("<")) {
                if (rhs instanceof ExprIntegerLiteral) {
                    final ExprIntegerLiteral exp = (ExprIntegerLiteral) rhs;
                    if (exp.getValue() == 0) {
                        code = code += handleExpression(lhs);

                        final Label exitLabel = new Label("Less than exit label");
                        final Label trueLabel = new Label("Less than results in true");
                        code += "\tiflt " + trueLabel + "\n";
                        code += "\t;Less than results in false\n";
                        code += "\ticonst_0\n";
                        code += "\tgoto " + exitLabel + "\n";
                        code += trueLabel.getLabelString() + "\n";
                        code += "\ticonst_1\n";
                        code += exitLabel.getLabelString();
                        return code;
                    }

                } else if (lhs instanceof ExprIntegerLiteral) {
                    final ExprIntegerLiteral exp = (ExprIntegerLiteral) lhs;
                    if (exp.getValue() == 0) {
                        code = code += handleExpression(rhs);

                        final Label exitLabel = new Label("Less than exit label");
                        final Label trueLabel = new Label("Less than results in true");
                        code += "\tifgt " + trueLabel + "\n";
                        code += "\t;Less than results in false\n";
                        code += "\ticonst_0\n";
                        code += "\tgoto " + exitLabel + "\n";
                        code += trueLabel.getLabelString() + "\n";
                        code += "\ticonst_1\n";
                        code += exitLabel.getLabelString();
                        return code;
                    }

                }
            }
        }

        if (op.equals("&&")) {
            final Label exitLabel = new Label("And exit label");
            final Label falseLabel = new Label("And results in false");
            // if lhs == false goto falseLabel
            // if rhs == false goto falseLabel
            // iconst_1 //true
            // goto exitLabel
            // falseLabel:
            // iconst_0 //false
            // exitLabel: //continue

            code += "\t;AND EXPRESSION\n";
            code += handleExpression(lhs);

            code += "\tifeq " + falseLabel + "\n";

            code += handleExpression(rhs);
            code += "\tifeq " + falseLabel + "\n";

            code += "\t; And results in true\n";
            code += "\ticonst_1\n";
            code += "\tgoto " + exitLabel + "\n";
            code += falseLabel + ":\n";
            code += "\ticonst_0\n";
            code += exitLabel + ":";

            return code;
        }

        code = handleExpression(lhs);
        code += handleExpression(rhs);

        switch (op) {
            case "+": {
                code += "\tiadd";
                break;
            }
            case "-": {
                code += "\tisub";
                break;
            }
            case "*": {
                code += "\timul";
                break;
            }
            case "/": {
                code += "\tidiv";
                break;
            }
            case "<": {
                final Label exitLabel = new Label("Less than exit label");
                final Label trueLabel = new Label("Less than results in true");

                // if < goto trueLabel
                // iconst_0 //false
                // goto exitLabel
                // trueLabel:
                // iconst_1 //true
                // exitLabel: //continue

                code += "\t;LESS THAN EXPRESSION\n";
                code += "\tif_icmplt " + trueLabel + "\n";
                code += "\t;Less than results in false\n";
                code += "\ticonst_0\n";
                code += "\tgoto " + exitLabel + "\n";
                code += trueLabel.getLabelString() + "\n";
                code += "\ticonst_1\n";
                code += exitLabel.getLabelString();
                break;
            }

            default:
                break;
        }

        method.removeStack(1);
        return code;
    }

}
