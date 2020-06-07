import java.util.HashMap;
import java.util.Map;

/**
 * Class Descriptor
 */
public class DescClass implements Descriptor {
    private final String name;
    private final String superName;
    private DescClass superClass;
    private Map<String, DescVar> members;
    private Map<MethodSignature, DescMethod> methods;

    public DescClass(final String name) {
        this.name = name;
        this.superName = new String();
        this.initMaps();
    }

    public DescClass(final String name, final String superName) {
        this.name = name;
        this.superName = superName;
        this.initMaps();
    }

    private void initMaps() {
        this.members = new HashMap<String, DescVar>();
        this.methods = new HashMap<MethodSignature, DescMethod>();
    }

    public void setSuperClass(final DescClass superClass) {
        this.superClass = superClass;
    }

    public DescClass getSuperClass() {
        return this.superClass;
    }

    public void addMethod(final DescMethod method) {
        this.methods.put(method.getSignature(), method);
    }

    public boolean hasMethod(final MethodSignature sig) {
        return this.methods.containsKey(sig);
    }

    public DescMethod getMethod(final MethodSignature sig) {
        DescMethod method = this.methods.get(sig);
        if (method == null && this.hasSuper())
            method = superClass.getMethod(sig);
        return method;
    }

    public Map<MethodSignature, DescMethod> getMethods() {
        return this.methods;
    }

    public void addMember(final DescVar member) {
        this.members.put(member.getName(), member);
    }

    public Map<String, DescVar> getMembers() {
        return this.members;
    }

    public DescVar getMember(final String name) {
        return this.members.get(name);
    }

    public boolean hasMember(final String name) {
        return this.members.containsKey(name);
    }

    public boolean hasSuper() {
        return !this.superName.isEmpty();
    }

    public String getSuperName() {
        return this.superName;
    }

    @Override
    public boolean isClass() {
        return true;
    }

    @Override
    public boolean isInteger() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public boolean isObject() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isVoid() {
        return false;
    }

    @Override
    public String getReturnType() {
        return this.name;
    }

}
