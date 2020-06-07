import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains information on registers used by variables
 */
public class LocalRegistry {

    private final Map<DescVar, Integer> varIndexes;
    private final Map<Integer, DescVar> indexVars;
    private final Map<String, DescVar> arguments;
    private final Map<String, DescVar> locals;
    private final DescMethod method;
    private int regSize;

    public LocalRegistry(final DescMethod method) {
        this.method = method;
        this.varIndexes = new HashMap<DescVar, Integer>();
        this.indexVars = new HashMap<Integer, DescVar>();
        this.arguments = new HashMap<String, DescVar>();
        this.locals = new HashMap<String, DescVar>();
        this.regSize = 0;
    }

    /**
     * 
     * @return
     */
    public DescMethod getMethod() {
        return this.method;
    }

    /**
     * 
     * @return
     */
    public Map<DescVar, Integer> getVarIndexes() {
        return this.varIndexes;
    }

    public void setVarIndex(final DescVar var, final int index, final boolean isArgument) {
        if (index > this.regSize)
            regSize = index;

        if (isArgument)
            this.arguments.put(var.getName(), var);
        else
            this.locals.put(var.getName(), var);

        this.varIndexes.put(var, Integer.valueOf(index));
        this.indexVars.put(Integer.valueOf(index), var);
    }

    public int getVarIndex(final DescVar var) {
        return this.varIndexes.get(var).intValue();
    }

    public int getVarIndex(final String name) {
        DescVar var = locals.get(name);
        if (var != null)
            return this.varIndexes.get(var).intValue();

        var = arguments.get(name);
        if (var != null)
            return this.varIndexes.get(var).intValue();

        return -1;
    }

    public DescVar getVarFromIndex(final int index) {
        return this.indexVars.get(Integer.valueOf(index));
    }

    public boolean hasVar(final DescVar var) {
        return this.varIndexes.containsKey(var);
    }

    public int size() {
        return regSize;
    }

    public List<DescVar> getAllVars() {
        final List<DescVar> vars = new ArrayList<DescVar>();

        for (final DescVar arg : this.arguments.values())
            vars.add(arg);

        for (final DescVar local : this.locals.values())
            vars.add(local);

        return vars;
    }

    public Map<String, DescVar> getArgumentMap() {
        return this.arguments;
    }

    public Map<String, DescVar> getLocalMap() {
        return this.locals;
    }

    public DescVar getVar(final String name) {
        DescVar var = this.locals.get(name);
        if (var == null)
            var = this.arguments.get(name);
        return var;
    }

}
