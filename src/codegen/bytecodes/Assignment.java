
public class Assignment {

    private String bytecode;
    private final MethodGenerator method;
    private final StAssign st;

    public Assignment(final MethodGenerator method, final StAssign st, final boolean oFlag) {
        this.method = method;
        this.st = st;
        this.bytecode = new String();

        if (st.isArrayAssignment()) {
            // value to assign
            this.bytecode = new LoadVariable(method, st.getAssigneeName()) + "\n";
            final ExprArrayAccess access = (ExprArrayAccess) st.getIdentifierExpression();
            this.bytecode += new ExpressionGenerator(access.getAcessExpr(), method, oFlag);
            this.bytecode += new ExpressionGenerator(st.getExpression(), method, oFlag);
            this.bytecode += "\tiastore\n";
            this.method.removeStack(3);

        } else {
            this.bytecode = new ExpressionGenerator(st.getExpression(), method, oFlag).toString();
            this.bytecode += new StoreVariable(method, st.getAssigneeName()) + "\n";
        }
        if (oFlag)
            this.optimize();
    }

    public void setBytecode(final String bytecode) {
        this.bytecode = bytecode;
    }

    public void optimize() {
        final LocalRegistry lr = method.getLocalRegistry();
        final DescVar var = lr.getVar(st.getAssigneeName());
        if (var == null)
            return;

        if (st.getExpression() instanceof ExprOperation) {
            final ExprOperation op = (ExprOperation) st.getExpression();

            if (!op.getOperation().equals("+"))
                return;

            if (op.getLhs() instanceof ExprIdentifier) {
                final ExprIdentifier id = (ExprIdentifier) op.getLhs();
                if (!id.getIdentifierName().equals(st.getAssigneeName()))
                    return;

                if (op.getRhs() instanceof ExprIntegerLiteral) {
                    final ExprIntegerLiteral rhs = (ExprIntegerLiteral) op.getRhs();

                    this.bytecode = "\tiinc " + lr.getVarIndex(var);
                    this.bytecode += " " + rhs.getValue() + "\n";
                }
            } else if (op.getRhs() instanceof ExprIdentifier) {
                final ExprIdentifier id = (ExprIdentifier) op.getRhs();
                if (!id.getIdentifierName().equals(st.getAssigneeName()))
                    return;

                if (op.getLhs() instanceof ExprIntegerLiteral) {
                    final ExprIntegerLiteral lhs = (ExprIntegerLiteral) op.getLhs();

                    this.bytecode = "\tiinc " + lr.getVarIndex(var);
                    this.bytecode += " " + lhs.getValue() + "\n";
                }
            }
        }
    }

    @Override
    public String toString() {
        return this.bytecode;
    }

}
