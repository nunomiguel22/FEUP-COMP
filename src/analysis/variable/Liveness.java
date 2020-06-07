import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builds liveness tables for all statements in a method
 */
public class Liveness {

    private final CompilerData data;
    private final Map<Statement, LivenessTable> livenessMap;
    private final DescMethod method;
    private final LocalRegistry lr;
    private final CFG cfg;
    private final Statement exitSt;
    private boolean hasChanged;

    /**
     * Builds liveness tables for all statements in a method
     * 
     * @param method
     * @param data
     */
    public Liveness(final DescMethod method, final CompilerData data) {
        this.data = data;
        this.method = method;
        this.hasChanged = true;
        this.livenessMap = new HashMap<Statement, LivenessTable>();
        this.lr = this.data.getLocalRegistry(this.method.getSignature());
        this.cfg = this.data.getCFG(this.method.getSignature());
        this.exitSt = this.cfg.getExit().getLastStatement();
        this.initStatement(this.exitSt, new ArrayList<Statement>());
    }

    /**
     * 
     * @param st
     * @param visited
     */
    private void initStatement(final Statement st, final List<Statement> visited) {

        if (visited.contains(st))
            return;

        livenessMap.put(st, new LivenessTable(st, this.lr));
        visited.add(st);

        for (final StatementLink pred : st.getPredecessors())
            this.initStatement(pred.source, visited);
    }

    /**
     * Begins Liveness table building
     * 
     */
    public void build() {
        this.hasChanged = true;
        int it = 0;
        while (hasChanged || it > 1000) {
            this.hasChanged = false;
            this.updateTable(this.exitSt, new ArrayList<Statement>());
            ++it;
        }
    }

    /**
     * 
     * @param st
     * @param visited
     */
    private void updateTable(final Statement st, final List<Statement> visited) {
        if (visited.contains(st))
            return;
        visited.add(st);

        final LivenessTable table = livenessMap.get(st);
        final BitSet prevOut = (BitSet) table.getOutValues().clone();
        final BitSet prevIn = (BitSet) table.getInValues().clone();
        final BitSet out = table.getOutValues();
        final BitSet in = table.getInValues();
        final BitSet use = table.getUseValues();
        final BitSet def = table.getDefValues();

        // Out[n] = U in[s]
        for (final StatementLink sl : st.getSuccessors()) {
            final BitSet succOut = this.livenessMap.get(sl.target).getInValues();
            out.or(succOut);
        }

        // in[n] = use[n] U (out[n] - def[n])
        final BitSet rhs = (BitSet) out.clone();
        rhs.andNot(def);
        in.or(use);
        in.or(rhs);

        if (!prevOut.equals(out) || !prevIn.equals(in))
            this.hasChanged = true;

        for (final StatementLink sl : st.getPredecessors()) {
            this.updateTable(sl.source, visited);
        }
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
    public Map<Statement, LivenessTable> getLivenessMap() {
        return this.livenessMap;
    }
}
