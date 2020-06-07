import java.util.ArrayList;

public class StBlock extends Statement {
    private CompilerData data;
    private final ArrayList<Statement> sts;

    public StBlock(final SimpleNode node, final DescMethod parent, final CompilerData data) throws SemanticException {
        super(node, parent, data);
        this.sts = new ArrayList<Statement>();

        for (int i = 0; i < node.jjtGetNumChildren(); ++i) {
            final Statement st = StFactory.build(node.jjtGetChild(i), parent, data);
            this.sts.add(st);
        }
    }

    public CompilerData getCompilerData() {
        return this.data;
    }

    public ArrayList<Statement> getBlock() {
        return this.sts;
    }

    @Override
    public void checkSemantics() throws SemanticException {

    }
}
