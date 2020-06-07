import java.util.ArrayList;
import java.util.List;

public class InvokeGenerator {

    private final ExprFunction func;
    private final Expression lhs;
    private final ArrayList<Expression> args;
    private final DescMethod methodDesc;
    private final MethodGenerator method;
    private final boolean oFlag;

    public InvokeGenerator(final ExprFunction func, final MethodGenerator method, final boolean oFlag) {
        this.func = func;
        this.oFlag = oFlag;
        this.lhs = this.func.getLhs();
        this.method = method;
        this.methodDesc = this.func.getMethod();
        this.args = this.func.getArgs();
    }

    private String loadArguments() {
        String args = "";

        for (final Expression expr : this.args) {
            args += new ExpressionGenerator(expr, method, oFlag);
        }

        return args;
    }

    private boolean isIdentifierCall() {
        return lhs.isObject() && (lhs instanceof ExprIdentifier);
    }

    private String loadIdentifier() {
        if (this.isIdentifierCall())
            return new ExpressionGenerator(this.lhs, method, oFlag).toString();
        return new String();
    }

    private String generateInvoke() {
        String code = "";

        if (methodDesc.isStatic())
            code += "\tinvokestatic ";
        else
            code += "\tinvokevirtual ";

        code += methodDesc.getParentClass().getReturnType();
        code += "/";
        code += methodDesc.getName();
        code += "(";
        code += generateParamList();
        code += ")";
        code += methodDesc.getReturnType().getBytecode();

        if (!this.methodDesc.isStatic())
            method.removeStack(1);
        method.removeStack(this.args.size());
        if (!this.methodDesc.getReturnType().toString().equals("void"))
            method.addStack(1);

        if (func.getTermTail() != null)
            code += "\n" + new ExpressionGenerator(func.getTermTail(), method, oFlag);

        return code;
    }

    private String generateParamList() {
        String code = "";
        final List<DescVar> parameters = methodDesc.getParameters();

        for (final DescVar param : parameters)
            code += param.getType().getBytecode();

        return code;
    }

    @Override
    public String toString() {
        String code = "";
        code += loadIdentifier();
        code += loadArguments();
        code += generateInvoke();

        return code;
    }

}
