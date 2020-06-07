/**
 * Variable Descriptor
 */
public class DescVar implements Descriptor {
    private final DescType type;
    private String identifier;

    public DescVar(final DescType type, final String identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public DescVar(final SimpleNode node) {
        this.type = new DescType(node);

        if (this.type.isArray()) {
            this.identifier = node.jjtGetChild(node.jjtGetNumChildren() - 1).identifierVal;

        } else
            this.identifier = node.jjtGetChild(1).identifierVal;
    }

    public int getIdentifierIndex() {
        if (this.isArray())
            return 2;
        else
            return 1;
    }

    public DescType getType() {
        return this.type;
    }

    public String getTypeName() {
        return this.type.toString();
    }

    public String getName() {
        return this.identifier;
    }

    @Override
    public boolean isClass() {
        return false;
    }

    public boolean isArray() {
        return this.type.isArray();
    }

    @Override
    public boolean isInteger() {
        return this.type.isInteger();

    }

    @Override
    public boolean isBoolean() {
        return this.type.isBoolean();
    }

    @Override
    public boolean isVoid() {
        return this.type.isVoid();
    }

    @Override
    public boolean isObject() {
        return this.type.isClass();
    }

    @Override
    public String getReturnType() {
        return this.type.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final DescVar desc2 = (DescVar) obj;
            return this.identifier.equals(desc2.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }

}
