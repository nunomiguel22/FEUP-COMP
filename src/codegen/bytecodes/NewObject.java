public class NewObject {

    private final ExprNewObject expr;
    private final MethodGenerator method;
    private final boolean oFlag;

    public NewObject(final ExprNewObject expr, final MethodGenerator method, final boolean oFlag) {
        this.expr = expr;
        this.method = method;
        this.oFlag = oFlag;
    }

    @Override
    public String toString() {
        String code = "\tnew ";

        code += expr.getIdentifierName() + "\n";
        code += "\tdup\n";
        method.addStack(2);
        code += "\tinvokespecial ";
        code += expr.getIdentifierName();
        code += "/<init>()V";
        method.removeStack(1);

        if (expr.getTermTail() != null)
            code += "\n" + new ExpressionGenerator(expr.getTermTail(), method, oFlag);

        return code;
    }
}
