public class NewIntArray {

    private final ExprNewArray exp;
    private final MethodGenerator parent;
    private final boolean oFlag;

    public NewIntArray(final ExprNewArray expr, final MethodGenerator method, final boolean oFlag) {
        this.exp = expr;
        this.parent = method;
        this.oFlag = oFlag;
    }

    public String toString() {
        String code = new ExpressionGenerator(this.exp.getAccessExpression(), this.parent, oFlag) + "";
        code += "\tnewarray int";
        return code;
    }

}
