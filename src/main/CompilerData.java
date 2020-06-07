import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Keeps all compiler data information:
 * <li>Abstract syntax tree
 * <li>Symbol Table
 * <li>Control Flow Graphs for each method
 * <li>Statement List for each method
 * <li>Register table for each method
 * <li>Liveness information for each method
 */
public class CompilerData {
    public SimpleNode rootNode;
    public SymbolTable table;
    private final Map<MethodSignature, CFG> cfgs;
    private final Map<MethodSignature, List<Statement>> statements;
    private final Map<MethodSignature, LocalRegistry> localRegistries;
    private final Map<MethodSignature, Liveness> livenessMap;

    public int errors;

    public CompilerData(final SimpleNode rootNode) {
        this.rootNode = rootNode;
        this.errors = 0;
        this.cfgs = new HashMap<MethodSignature, CFG>();
        this.statements = new HashMap<MethodSignature, List<Statement>>();
        this.localRegistries = new HashMap<MethodSignature, LocalRegistry>();
        this.livenessMap = new HashMap<MethodSignature, Liveness>();
    }

    public Map<MethodSignature, Liveness> getLivenessMap() {
        return this.livenessMap;
    }

    public void addMethodLiveness(final MethodSignature method, final Liveness liv) {
        this.livenessMap.put(method, liv);
    }

    public Liveness getMethodLiveness(final MethodSignature method) {
        return this.livenessMap.get(method);
    }

    public Map<MethodSignature, CFG> getCFGs() {
        return this.cfgs;
    }

    public void addCFG(final MethodSignature sig, final CFG cfg) {
        this.cfgs.put(sig, cfg);
    }

    public CFG getCFG(final MethodSignature sig) {
        return this.cfgs.get(sig);
    }

    public Map<MethodSignature, List<Statement>> getStatementMap() {
        return this.statements;
    }

    public void addStatementList(final MethodSignature sig, final List<Statement> statements) {
        this.statements.put(sig, statements);
    }

    public List<Statement> getStatementList(final MethodSignature sig) {
        return this.statements.get(sig);
    }

    public Map<MethodSignature, LocalRegistry> getLocalRegistries() {
        return this.localRegistries;
    }

    public LocalRegistry getLocalRegistry(final MethodSignature sig) {
        return this.localRegistries.get(sig);
    }

    public void addLocalRegistry(final MethodSignature sig, final LocalRegistry reg) {
        this.localRegistries.put(sig, reg);
    }
}
