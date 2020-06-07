public class Not {
    private final ExprNot expr;
    private final MethodGenerator parent;
    private final boolean oFlag;

    public Not(final ExprNot expr, final MethodGenerator method, final boolean oFlag) {
        this.expr = expr;
        this.parent = method;
        this.oFlag = oFlag;
    }

    @Override
    public String toString() {
        final Label falseLabel = new Label("Negation results in true");
        final Label exitLabel = new Label("Negation Exit Label");
        String code = new ExpressionGenerator(this.expr.getExpression(), this.parent, oFlag) + "";

        // if expr == false goto falseLabel
        // iconst_0 //Make false if true
        // goto exitLabel
        // falseLabel:
        // iconst_1 //Make true if false
        // exitLabel:
        code += "\t;NEGATION EXPRESSION\n";
        code += "\tifeq " + falseLabel + "\n";
        code += "\t; Negation results in false\n";
        code += "\ticonst_0\n";
        code += "\tgoto " + exitLabel + "\n";
        code += falseLabel.getLabelString() + "\n";
        code += "\ticonst_1\n";
        code += exitLabel.getLabelString() + "\n";
        return code;
    }

}
