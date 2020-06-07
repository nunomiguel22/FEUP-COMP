import java.util.ArrayList;

public class ExprFunction extends Expression {

    private final Expression lhs;
    private final ArrayList<Expression> args;
    private DescMethod method;
    private ExprTermTail termTail;
    private final DescClass callerCls;
    private String[] argTypes;
    private final String functionName;
    private String returnType;

    private boolean numericType;
    private boolean booleanType;
    private boolean arrayType;
    private boolean objectType;
    private boolean voidType;

    public ExprFunction(final Expression lhs, SimpleNode rhs, final Statement parent) throws SemanticException {
        super(rhs, parent);
        this.lhs = lhs;
        SimpleNode tailNode = null;
        this.parent.funCall = true;

        // Check for Function TermTail
        if (rhs.isType(JmmTreeConstants.JJTTERMTAIL)) {
            if (rhs.jjtGetNumChildren() > 1)
                tailNode = rhs.jjtGetChild(1);
            rhs = rhs.getFirstChild();
        }

        // Generate function name and argument type list
        this.args = new ArrayList<Expression>();
        this.functionName = rhs.getFirstChild().identifierVal;

        if (rhs.jjtGetNumChildren() > 1) {
            final SimpleNode argList = rhs.jjtGetChild(1);
            this.argTypes = new String[argList.jjtGetNumChildren()];

            for (int i = 0; i < argList.jjtGetNumChildren(); ++i) {
                final SimpleNode argNode = argList.jjtGetChild(i).getFirstChild();
                final Expression argExpr = ExprFactory.build(argNode, parent);
                args.add(argExpr);
                this.argTypes[i] = argExpr.getReturnType();
            }
        } else
            this.argTypes = new String[0];

        // Generate and resolve function signature
        final MethodSignature sig = new MethodSignature(this.functionName, argTypes);
        this.callerCls = this.data.table.resolveClass(lhs.getReturnType());

        // Get Method information
        if (this.callerCls != null) {
            this.method = callerCls.getMethod(sig);

            if (this.method != null) {
                this.returnType = method.getReturnType().toString();
                this.numericType = this.returnType.equals("int");
                this.booleanType = this.returnType.equals("boolean");
                this.arrayType = this.returnType.equals("int[]");
                this.voidType = this.returnType.equals("void");
                this.objectType = !(isInteger() || isBoolean());
            }
        }

        this.checkSemantics();

        // Build next tail node if it exists
        if (tailNode != null) {
            termTail = new ExprTermTail(this, tailNode, parent);
            this.returnType = termTail.getReturnType().toString();
            this.numericType = termTail.isInteger();
            this.arrayType = termTail.isArray();
            this.booleanType = termTail.isBoolean();
            this.objectType = termTail.isObject();
            this.voidType = termTail.isVoid();
        }

    }

    /**
     * Check if the function caller is a valid class or object
     * 
     * Check if the method belongs to the caller class
     */
    @Override
    public void checkSemantics() throws SemanticException {
        int line = 0;
        int column = 0;

        if (this.node.isType(JmmTreeConstants.JJTTERMTAIL)) {
            line = node.getFirstChild().getFirstChild().line;
            column = node.getFirstChild().getFirstChild().column;
        } else {
            line = node.getFirstChild().line;
            column = node.getFirstChild().column;
        }

        // Check if lhs is a class
        if (callerCls == null) {
            String msg = "Cannot invoke '" + this.functionName + "' ";
            msg += "for type " + this.lhs.getReturnType();
            throw new SemanticException(msg, line, column);
        }

        if (method == null) {
            String msg = "Method '" + this.functionName + "(";
            if (argTypes.length > 1)
                msg += argTypes[0];

            for (int i = 1; i < argTypes.length; ++i)
                msg += ", " + argTypes[i];

            msg += ")' is not a part of class '" + callerCls.getReturnType() + "'";

            throw new SemanticException(msg, line, column);
        }
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return false;
        }
        return false;
    }

    public ExprTermTail getTermTail() {
        return this.termTail;
    }

    public Expression getLhs() {
        return this.lhs;
    }

    public ArrayList<Expression> getArgs() {
        return this.args;
    }

    public DescMethod getMethod() {
        return this.method;
    }

    @Override
    public String getReturnType() {
        return this.returnType;
    }

    @Override
    public boolean isInteger() {
        return this.numericType;
    }

    @Override
    public boolean isBoolean() {
        return this.booleanType;
    }

    @Override
    public boolean isVoid() {
        return this.voidType;
    }

    @Override
    public boolean isArray() {
        return this.arrayType;
    }

    @Override
    public boolean isObject() {
        return this.objectType;
    }

    @Override
    public boolean isClass() {
        return false;
    }
}
