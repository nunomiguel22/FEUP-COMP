
public class ExpressionGenerator {
    private final Expression exp;
    private final MethodGenerator method;
    private final boolean oFlag;

    public ExpressionGenerator(final Expression expr, final MethodGenerator method, final boolean oFlag) {
        this.exp = expr;
        this.method = method;
        this.oFlag = oFlag;
    }

    @Override
    public String toString() {
        if (this.exp instanceof ExprIntegerLiteral) {
            final ExprIntegerLiteral val = (ExprIntegerLiteral) this.exp;
            return new IntegerPush(val.getValue(), method) + "\n";
        }

        if (this.exp instanceof ExprBooleanLiteral) {
            final ExprBooleanLiteral val = (ExprBooleanLiteral) this.exp;
            return new IntegerPush(val.getValue(), method) + "\n";
        }

        if (this.exp instanceof ExprBracket) {
            final ExprBracket expr = (ExprBracket) this.exp;
            return new ExpressionGenerator(expr.getExpression(), method, this.oFlag) + "\n";
        }

        if (this.exp instanceof ExprNewArray) {
            final ExprNewArray expr = (ExprNewArray) this.exp;
            return new NewIntArray(expr, method, oFlag) + "\n";
        }

        if (this.exp instanceof ExprNot) {
            final ExprNot expr = (ExprNot) this.exp;
            return new Not(expr, method, oFlag) + "\n";
        }

        if (this.exp instanceof ExprThis) {
            final ExprThis expr = (ExprThis) this.exp;
            return new This(expr, method, oFlag) + "\n";
        }

        if (this.exp instanceof ExprOperation) {
            final ExprOperation expr = (ExprOperation) this.exp;
            return new OperationGenerator(expr, this.method, oFlag) + "\n";
        }

        if (this.exp instanceof ExprIdentifier) {
            final ExprIdentifier expr = (ExprIdentifier) this.exp;
            final String name = expr.getIdentifierName();
            return new LoadVariable(method, name).toString() + "\n";
        }

        if (this.exp instanceof ExprTermTail) {
            final ExprTermTail tail = (ExprTermTail) this.exp;
            final Expression expr = tail.getExpression();

            if (expr instanceof ExprFunction) {
                final ExprFunction funcExpr = (ExprFunction) expr;
                return new InvokeGenerator(funcExpr, method, oFlag) + "\n";
            }

            if (expr instanceof ExprLength) {
                final ExprLength lengthExpr = (ExprLength) expr;
                return new Length(lengthExpr, method, oFlag).toString() + "\n";
            }

            if (expr instanceof ExprArrayAccess) {
                final ExprArrayAccess arrayExpr = (ExprArrayAccess) expr;
                return new ArrayAccess(arrayExpr, method, oFlag).toString() + "\n";
            }
        }

        if (this.exp instanceof ExprNewObject) {
            final ExprNewObject expr = (ExprNewObject) this.exp;
            return new NewObject(expr, method, oFlag) + "\n";
        }

        return new String();
    }
}
