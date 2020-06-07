public class This {

    private final ExprThis exp;
    private final ExprTermTail tail;
    private final MethodGenerator parent;
    private final boolean oFlag;

    public This(final ExprThis exp, final MethodGenerator method, final boolean oFlag) {
        this.exp = exp;
        this.tail = exp.getTermTail();
        this.parent = method;
        this.oFlag = oFlag;
    }

    public String toString() {
        String code = "\taload 0\n";
        this.parent.addStack(1);
        code += new ExpressionGenerator(this.tail, this.parent, oFlag);
        return code;
    }

    public ExprThis getThisExpr() {
        return this.exp;
    }

}
