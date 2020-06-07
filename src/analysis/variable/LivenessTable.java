import java.util.BitSet;

/**
 * Builds a liveness table using "use", "def", "in" and "out" BitSets
 */
public class LivenessTable {
    private final Statement st;
    private final BitSet use;
    private final BitSet def;
    private final BitSet in;
    private final BitSet out;

    /**
     * Initializes BitSts with "use" and "def" information from statements
     * 
     * @param st
     * @param lr
     */
    public LivenessTable(final Statement st, final LocalRegistry lr) {
        this.st = st;
        this.use = new BitSet(lr.size());
        this.def = new BitSet(lr.size());
        this.in = new BitSet(lr.size());
        this.out = new BitSet(lr.size());

        if (st instanceof StAssign) {
            final StAssign sta = (StAssign) st;
            final DescVar var = lr.getVar(sta.getAssigneeName());
            if (var != null) {
                final int index = lr.getVarIndex(var);
                this.def.set(index, true);
            }
        }

        for (final DescVar var : st.getVarUses()) {
            if (lr.hasVar(var))
                this.use.set(lr.getVarIndex(var), true);
        }

    }

    /**
     * 
     * @return
     */
    public Statement getStatement() {
        return this.st;
    }

    /**
     * 
     * @param index
     * @param val
     */
    public void setIn(final int index, final boolean val) {
        this.in.set(index, val);
    }

    /**
     * 
     * @return
     */
    public BitSet getInValues() {
        return this.in;
    }

    /**
     * 
     * @param index
     * @param val
     */
    public void setOut(final int index, final boolean val) {
        this.out.set(index, val);
    }

    /**
     * 
     * @return
     */
    public BitSet getOutValues() {
        return this.out;
    }

    /**
     * 
     * @return
     */
    public BitSet getUseValues() {
        return this.use;
    }

    /**
     * 
     * @return
     */
    public BitSet getDefValues() {
        return this.def;
    }

}
