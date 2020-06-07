import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Control Flow Graph Basic Block, contains the block's statements and links to
 * the predecessors and successors
 */
public class CFGBlock {
    private static int blockCount = 0;

    private int id;
    private List<Statement> statements;
    private List<CFGLink> predecessors;
    private List<CFGLink> exits;

    public CFGBlock() {
        this.id = blockCount++;
        this.statements = new LinkedList<Statement>();
        this.predecessors = new ArrayList<CFGLink>();
        this.exits = new ArrayList<CFGLink>();
    }

    public int getId() {
        return this.id;
    }

    public boolean isEmpty() {
        return this.statements.size() == 0;
    }

    public boolean hasSuccessors() {
        return this.exits.size() > 0;
    }

    public boolean hasPredecessors() {
        return this.predecessors.size() > 0;
    }

    public List<CFGLink> getExits() {
        return this.exits;
    }

    public List<CFGLink> getPredecessors() {
        return this.predecessors;
    }

    public List<Statement> getStatements() {
        return this.statements;
    }

    public void addStatement(Statement st) {
        if (this.size() > 0) {
            Statement last = this.getLastStatement();
            Statement.link(last, st, false);
        }
        this.statements.add(st);
    }

    public void addPredecessor(CFGLink lnk) {
        this.predecessors.add(lnk);
    }

    public void addExit(CFGLink lnk) {
        this.exits.add(lnk);
    }

    public int size() {
        return this.statements.size();
    }

    public void reset() {
        this.statements = new LinkedList<Statement>();
        this.predecessors = new ArrayList<CFGLink>();
        this.exits = new ArrayList<CFGLink>();
    }

    public void removeSuccessorLink(CFGBlock block) {
        for (CFGLink lnk : this.exits) {
            if (lnk.getTarget() == block) {
                this.exits.remove(lnk);
                return;
            }
        }
    }

    public void removePredecessorLink(CFGBlock block) {
        for (CFGLink lnk : this.predecessors) {
            if (lnk.getSource() == block) {
                this.predecessors.remove(lnk);
                return;
            }
        }
    }

    public Statement getFirstStatement() {
        if (!this.statements.isEmpty())
            return this.statements.get(0);
        return null;
    }

    public Statement getLastStatement() {
        if (!this.statements.isEmpty())
            return this.statements.get(this.statements.size() - 1);
        return null;
    }

    public void eliminate() {

        if (!this.hasPredecessors() || !this.hasSuccessors())
            return;

        for (CFGLink pred : this.predecessors)
            for (CFGLink exit : this.exits) {
                new CFGLink(pred.getSource(), exit.getTarget(), false);
            }

        for (CFGLink pred : this.predecessors)
            pred.getSource().getExits().remove(pred);

        for (CFGLink exit : this.exits) {
            exit.getTarget().getPredecessors().remove(exit);
        }

    }
}
