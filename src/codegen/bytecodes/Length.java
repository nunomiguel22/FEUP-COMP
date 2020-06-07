public class Length {

    private final ExprLength expr;
    private final Expression lhs;
    private final MethodGenerator parent;
    private final boolean oFlag;

    public Length(final ExprLength func, final MethodGenerator method, final boolean oFlag) {
        this.expr = func;
        this.lhs = this.expr.getLhs();
        this.parent = method;
        this.oFlag = oFlag;
    }

    public String toString() {
        final String code = new ExpressionGenerator(this.lhs, this.parent, oFlag).toString();
        return code + "\tarraylength";
    }
}
