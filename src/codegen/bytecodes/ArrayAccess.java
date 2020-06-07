public class ArrayAccess {
    private final ExprArrayAccess expr;
    private final MethodGenerator parent;
    private final Expression lhs;
    private final Expression accessExpr;
    private final boolean oFlag;

    public ArrayAccess(final ExprArrayAccess exp, final MethodGenerator method, final boolean oFlag) {
        this.expr = exp;
        this.parent = method;
        this.lhs = exp.getLhs();
        this.accessExpr = exp.getAcessExpr();
        this.oFlag = oFlag;
    }

    public ExprArrayAccess getAccessExpr() {
        return this.expr;
    }

    private String loadIdentifier() {
        if (lhs.isObject() && (lhs instanceof ExprIdentifier)) {
            this.parent.addStack(1);
            return new ExpressionGenerator(this.lhs, parent, oFlag).toString();
        } else
            return new String();
    }

    @Override
    public String toString() {
        String code = "";
        code += loadIdentifier();
        code += new ExpressionGenerator(this.accessExpr, parent, oFlag);
        code += "\tiaload";

        return code;
    }

}
