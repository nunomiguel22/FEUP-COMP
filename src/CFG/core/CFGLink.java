public class CFGLink {
    private CFGBlock source;
    private CFGBlock target;
    private boolean backEdge;

    /**
     * Control flow graph edges, has a back edge flag (Loop edge).
     */
    public CFGLink(CFGBlock source, CFGBlock target, boolean backEdge) {
        this.source = source;
        this.target = target;
        this.backEdge = backEdge;
        this.source.addExit(this);
        this.target.addPredecessor(this);
    }

    public CFGBlock getSource() {
        return this.source;
    }

    public CFGBlock getTarget() {
        return this.target;
    }

    public boolean isBackEdge() {
        return this.backEdge;
    }

}
