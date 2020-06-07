import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * Register interference graph used to build an optimized LocalRegistry table.
 * Builds graph using liveness information
 */
public class InterferenceGraph {
    private final CompilerData data;
    private final DescMethod method;
    private final Liveness liveness;
    private final Map<Statement, LivenessTable> liveMap;
    private final Map<DescVar, LinkedList<DescVar>> adjacentVertices;
    private final Stack<DescVar> varStack;
    private final LocalRegistry lr;
    private final Map<DescVar, Integer> result;

    /**
     * Register interference graph used to build an optimized LocalRegistry table.
     * Builds graph using liveness information
     * 
     * @param liveness
     * @param data
     */
    public InterferenceGraph(final Liveness liveness, final CompilerData data) {
        this.data = data;
        this.liveness = liveness;
        this.liveMap = this.liveness.getLivenessMap();
        this.method = liveness.getMethod();
        this.lr = this.data.getLocalRegistry(this.method.getSignature());
        this.adjacentVertices = new HashMap<DescVar, LinkedList<DescVar>>();
        this.result = new HashMap<DescVar, Integer>();
        this.varStack = new Stack<DescVar>();

        for (final DescVar var : lr.getArgumentMap().values()) {
            result.put(var, lr.getVarIndex(var));
            this.adjacentVertices.put(var, new LinkedList<DescVar>());
        }

        for (final DescVar var : lr.getLocalMap().values()) {
            varStack.push(var);
            this.adjacentVertices.put(var, new LinkedList<DescVar>());
        }

        this.buildGraph();
    }

    private void buildGraph() {
        for (final LivenessTable table : liveMap.values()) {
            this.linkBitset(table.getInValues());
            this.linkBitset(table.getOutValues());
        }
    }

    /**
     * 
     * @param bs
     */
    private void linkBitset(final BitSet bs) {
        final LinkedList<DescVar> vars = new LinkedList<DescVar>();

        for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i + 1)) {
            final DescVar var = lr.getVarFromIndex(i);
            if (var != null)
                vars.add(var);
        }

        for (final DescVar var : vars) {
            for (final DescVar var2 : vars) {
                if (var == var2)
                    continue;
                addAdjacent(var, var2);
            }
        }
    }

    /**
     * 
     * @param source
     * @param target
     */
    private void addAdjacent(final DescVar source, final DescVar target) {
        LinkedList<DescVar> adj = adjacentVertices.get(source);
        if (!adj.contains(target))
            adj.add(target);
    }

    /**
     * Returns optimized local registry
     * 
     * @return LocalRegistry - Optimized Registry
     */
    public LocalRegistry getOptimizedRegistry() {
        LocalRegistry nlr = new LocalRegistry(this.method);

        while (!varStack.isEmpty()) {
            DescVar var = varStack.lastElement();
            LinkedList<DescVar> adjs = this.adjacentVertices.get(var);

            int reg = 1;
            while (indexTaken(reg, adjs))
                ++reg;

            this.result.put(var, Integer.valueOf(reg));
            varStack.pop();
        }

        for (Map.Entry<DescVar, Integer> entry : result.entrySet()) {
            nlr.setVarIndex(entry.getKey(), entry.getValue(), false);

        }

        return nlr;
    }

    /**
     * 
     * @param index
     * @param adjs
     * @return
     */
    private boolean indexTaken(final int index, final LinkedList<DescVar> adjs) {
        for (final DescVar var : adjs) {
            if (this.result.get(var) == null)
                continue;
            if (this.result.get(var).intValue() == index)
                return true;
        }
        return false;

    }

}
