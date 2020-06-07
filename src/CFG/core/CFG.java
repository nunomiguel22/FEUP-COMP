import java.util.List;

/**
 * Control Flow Graph of basic blocks. CFG also links individual statements
 * together
 */
public class CFG {
    private CompilerData data;
    private DescMethod method;
    private CFGBlock entry;
    private CFGBlock exit;

    public CFG(DescMethod method, CompilerData data) {
        this.method = method;
        this.data = data;
        this.entry = new CFGBlock();
    }

    public void setExit(CFGBlock exit) {
        this.exit = exit;
    }

    public CFGBlock getExit() {
        return this.exit;
    }

    public void cleanup(CFGBlock blc, List<CFGBlock> visited) {

        if (visited.contains(blc))
            return;

        visited.add(blc);

        if (blc.isEmpty()) {
            blc.eliminate();

            for (CFGLink lnk : blc.getExits())
                this.cleanup(lnk.getTarget(), visited);

            blc.reset();
            return;
        }

        for (CFGLink lnk : blc.getExits())
            this.cleanup(lnk.getTarget(), visited);
    }

    public void linkEdges(CFGBlock blc, List<CFGBlock> visited) {
        if (visited.contains(blc))
            return;

        visited.add(blc);

        Statement last = blc.getLastStatement();

        if (last != null) {
            for (CFGLink lnk : blc.getExits()) {
                if (!lnk.getTarget().isEmpty()) {
                    Statement first = lnk.getTarget().getFirstStatement();
                    Statement.link(last, first, lnk.isBackEdge());
                }
            }
        }

        for (CFGLink lnk : blc.getExits())
            this.linkEdges(lnk.getTarget(), visited);

    }

    public CompilerData getData() {
        return this.data;
    }

    public CFGBlock getEntry() {
        return this.entry;
    }

    public DescMethod getMethodDescriptor() {
        return this.method;
    }
}
