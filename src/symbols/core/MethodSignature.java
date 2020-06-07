import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * This class generates a method signature given a name and argument types
 * </p>
 * Mainly used to identify methods in hashmaps
 */
public class MethodSignature {

    private final String name;
    private final List<DescVar> params;

    public MethodSignature(final String name, final String[] argTypes) {
        this.name = name;
        this.params = new ArrayList<DescVar>();

        for (final String argType : argTypes) {
            final DescType type = new DescType(argType);
            params.add(new DescVar(type, ""));
        }
    }

    public MethodSignature(final String name, final List<DescVar> params) {
        this.name = name;
        this.params = params;
    }

    public List<DescVar> getParameters() {
        return this.params;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            final MethodSignature sig2 = (MethodSignature) obj;
            final List<DescVar> params2 = sig2.getParameters();
            if (!this.name.equals(sig2.getName()))
                return false;

            if (this.params.size() != params2.size()) {
                return false;
            }

            for (int i = 0; i < params.size(); ++i) {
                final DescType type1 = params.get(i).getType();
                final DescType type2 = params2.get(i).getType();

                if (!type1.equals(type2))
                    return false;
            }

            return true;
        }
        return false;
    }
}
