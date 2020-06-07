import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Method Descriptor
 */
public class DescMethod implements JmmTreeConstants {
    private final SimpleNode node;
    private SimpleNode returnNode;
    private final DescClass parentClass;

    private DescType returnType;
    private String name;
    private final List<DescVar> parameters;
    private boolean staticFunc;

    private final Map<String, DescVar> localMap;
    private MethodSignature signature;

    public DescMethod(final SimpleNode node, final DescClass parent) throws SemanticException {
        this.node = node;
        this.returnNode = null;
        if (this.node.jjtGetNumChildren() > 2) {
            this.returnNode = node.jjtGetChild(2);
        }

        this.parentClass = parent;
        this.parameters = new ArrayList<DescVar>();
        this.localMap = new HashMap<String, DescVar>();
        if (this.node.isType(JJTMETHOD)) {
            final SimpleNode returnNode = this.node.getFirstChild().getFirstChild();
            final SimpleNode nameNode = this.node.getFirstChild().jjtGetChild(1);
            final SimpleNode parametersNode = this.node.getFirstChild().jjtGetChild(2);

            this.returnType = new DescType(returnNode);
            this.name = nameNode.identifierVal;
            this.staticFunc = false;

            for (int i = 0; i < parametersNode.jjtGetNumChildren(); ++i) {
                final DescVar var = new DescVar(parametersNode.jjtGetChild(i));
                this.parameters.add(var);
            }
        } else {
            this.name = "main";
            this.returnType = new DescType("void");
            this.staticFunc = true;
        }

        this.buildSignature();

        if (this.parentClass.hasMethod(this.signature)) {
            final int line = this.node.getFirstChild().line;
            final int column = this.node.getFirstChild().column;
            String message = "duplicate method '" + this.name + "' ";
            message += "in class '" + this.parentClass.getReturnType() + "'";
            throw new SemanticException(message, line, column);
        }
    }

    public DescMethod(final SimpleNode node, final DescClass parent, final DescType rt, final String name,
            final boolean isStaticF) {
        this.node = node;
        this.parentClass = parent;
        this.returnType = rt;
        this.name = name;
        this.staticFunc = isStaticF;
        this.parameters = new ArrayList<DescVar>();
        this.localMap = new HashMap<String, DescVar>();
    }

    public boolean hasLocal(final String name) {
        return this.localMap.containsKey(name);
    }

    public void addLocal(final DescVar var) {
        this.localMap.put(var.getName(), var);
    }

    public DescVar getLocal(final String name) {
        return this.localMap.get(name);
    }

    public List<DescVar> getParameters() {
        return this.parameters;
    }

    public boolean hasParameter(final String name) {
        for (final DescVar var : parameters)
            if (var.getName().equals(name))
                return true;

        return false;
    }

    public DescVar getParameter(final String name) {
        for (final DescVar var : parameters)
            if (var.getName().equals(name))
                return var;

        return null;
    }

    public SimpleNode getNode() {
        return this.node;
    }

    public SimpleNode getReturnNode() {
        return this.returnNode;
    }

    public Map<String, DescVar> getLocalMap() {
        return this.localMap;
    }

    public void buildSignature() {
        this.signature = new MethodSignature(this.name, this.parameters);
    }

    public MethodSignature getSignature() {
        return this.signature;
    }

    public DescType getReturnType() {
        return this.returnType;
    }

    public boolean isStatic() {
        return this.staticFunc;
    }

    public boolean isMain() {
        return this.name.equals("main");
    }

    public String getName() {
        return this.name;
    }

    public DescClass getParentClass() {
        return this.parentClass;
    }

    public int getParameterCount() {
        return this.parameters.size();
    }

    public void addParameter(final DescVar param) {
        this.parameters.add(param);
    }

    public void addParameter(final DescType paramType) {
        this.parameters.add(new DescVar(paramType, ""));
    }

    public DescVar getMethodVariable(final String name) {
        DescVar var = this.getLocal(name);
        if (var != null)
            return var;

        var = this.getParameter(name);
        return var;

    }

    public String getDeclarationString() {
        String dec = this.returnType.getReturnType() + ' ';
        dec += this.name + ' ';
        dec += getParameterString();
        return dec;
    }

    public String getParameterString() {
        if (this.name.equals("main"))
            return "(String[] args)";

        String param = "(";
        if (parameters.size() > 0)
            param += parameters.get(0).getReturnType() + ' ' + parameters.get(0).getName();

        for (int i = 1; i < parameters.size(); ++i)
            param += ", " + parameters.get(i).getReturnType() + ' ' + parameters.get(i).getName();
        return param += ")";
    }
}
