import java.util.ArrayList;
import java.util.List;

public abstract class Statement {
    protected CompilerData data;
    protected SimpleNode node;
    protected DescMethod method;

    protected List<StatementLink> predecessors;
    protected List<StatementLink> successors;

    protected List<DescVar> varUses;
    protected boolean funCall;

    protected VarDefTable defTable;

    public int line;
    public int column;

    public Statement(final SimpleNode node, final DescMethod method, final CompilerData data) {
        this.node = node;
        this.method = method;
        this.data = data;
        this.predecessors = new ArrayList<StatementLink>();
        this.successors = new ArrayList<StatementLink>();
        this.varUses = new ArrayList<DescVar>();
        this.funCall = false;
    }

    public DescMethod getMethod() {
        return this.method;
    }

    public CompilerData getCompilerData() {
        return this.data;
    }

    public void addPredecessor(final StatementLink st) {
        this.predecessors.add(st);
    }

    public void addSuccessor(final StatementLink st) {
        this.successors.add(st);
    }

    public boolean hasSuccessors() {
        return this.successors.size() > 0;
    }

    public boolean hasPredecessors() {
        return this.predecessors.size() > 0;
    }

    public boolean hasNonLoopPredecessors() {
        if (this.hasPredecessors()) {
            for (final StatementLink pred : this.predecessors)
                if (!pred.isBackEdge())
                    return true;
        }
        return false;
    }

    public boolean hasFunctionCall() {
        return this.funCall;
    }

    public void addVarUse(final DescVar var) {
        this.varUses.add(var);
    }

    public List<DescVar> getVarUses() {
        return this.varUses;
    }

    public List<StatementLink> getPredecessors() {
        return this.predecessors;
    }

    public List<StatementLink> getSuccessors() {
        return this.successors;
    }

    public static void link(final Statement source, final Statement target, final boolean backEdge) {
        source.addSuccessor(new StatementLink(source, target, backEdge));
        target.addPredecessor(new StatementLink(source, target, backEdge));
    }

    public void setVarDefTable(final VarDefTable table) {
        this.defTable = table;
    }

    public VarDefTable getVarDefTable() {
        return this.defTable;
    }

    public abstract void checkSemantics() throws SemanticException;
}
