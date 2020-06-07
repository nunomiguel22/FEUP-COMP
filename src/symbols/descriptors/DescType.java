/**
 * Type Descriptor
 */
public class DescType implements Descriptor, JmmTreeConstants {
    private String type;

    public DescType(final String type) {
        this.type = type;
    }

    public DescType(final SimpleNode node) {

        String typeName = "void";

        if (node.jjtGetNumChildren() < 1)
            this.type = typeName;

        final SimpleNode typeNode = node.getFirstChild();

        if (typeNode.isType(JJTPRIMITIVE))
            typeName = typeNode.typeVal;
        else if (typeNode.isType(JJTIDENTIFIER) || typeNode.isType(JJTMEMBERIDENTIFIERTYPE))
            typeName = typeNode.identifierVal;

        if (node.jjtGetNumChildren() > 1) {
            final SimpleNode nodeArray = node.jjtGetChild(1);
            if (nodeArray.isType(JJTARRAYDECLARATION))
                typeName += "[]";
        }
        this.type = typeName;
    }

    public String getBytecode() {
        if (this.isArray())
            return "[I";

        if (this.isInteger())
            return "I";

        if (this.isBoolean())
            return "Z";

        if (this.isVoid())
            return "V";

        return "L" + this.type + ";";
    }

    public String getReturnByteCode() {
        if (this.isBoolean() || this.isInteger())
            return "i";

        return "a";
    }

    @Override
    public boolean isClass() {
        return !(this.isArray() || this.isInteger() || this.isVoid() || this.isBoolean());
    }

    public boolean isArray() {
        return this.type.equals("int[]");
    }

    @Override
    public boolean isInteger() {
        return this.type.equals("int");

    }

    @Override
    public boolean isBoolean() {
        return this.type.equals("boolean");
    }

    @Override
    public boolean isVoid() {
        return this.type.equals("void");
    }

    @Override
    public boolean isObject() {
        return false;
    }

    @Override
    public String getReturnType() {
        return this.type;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final DescType desc2 = (DescType) obj;
            return type.equals(desc2.toString());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
