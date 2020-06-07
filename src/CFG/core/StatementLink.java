public class StatementLink {

    public Statement source;
    public Statement target;
    private boolean backEdge;

    /**
     * Statement edges used to link statements together, has a back edge flag (Loop
     * edge).
     */
    public StatementLink(Statement source, Statement target, boolean backEdge) {
        this.source = source;
        this.target = target;
        this.backEdge = backEdge;
    }

    public boolean isBackEdge() {
        return this.backEdge;
    }

}
