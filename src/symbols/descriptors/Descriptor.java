/**
 * Descriptor interface
 */
public interface Descriptor {
    /**
     * @return Returns true if class descriptor
     */
    public boolean isClass();

    public boolean isInteger();

    public boolean isBoolean();

    public boolean isObject();

    public boolean isArray();

    public boolean isVoid();

    /**
     * @return Returns a string with the name of the descriptor's return type
     */
    public String getReturnType();
}
