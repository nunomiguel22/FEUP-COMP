import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Table with Expression assignments to each variable at each statement. Used
 * for variable definition analysis and was to be used for constant propagation
 * optimization
 */
public class VarDefTable {

    private CompilerData data;
    private Map<DescVar, Expression> table;
    private LocalRegistry lr;
    private DescMethod method;

    public VarDefTable(DescMethod method, CompilerData data) {
        this.data = data;
        this.table = new HashMap<DescVar, Expression>();
        this.method = method;
        this.lr = this.data.getLocalRegistry(this.method.getSignature());

        for (DescVar arg : this.lr.getArgumentMap().values())
            table.put(arg, new ExprUnknown(arg.getType()));

        for (DescVar local : this.lr.getLocalMap().values())
            table.put(local, null);
    }

    public VarDefTable(VarDefTable table) {
        this.data = table.data;
        this.table = new HashMap<DescVar, Expression>();
        this.table.putAll(table.table);
        this.method = table.method;
        this.lr = table.lr;
    }

    public VarDefTable(VarDefTable table1, VarDefTable table2) {
        this.data = table1.data;
        this.table = new HashMap<DescVar, Expression>();
        this.method = table1.method;
        this.lr = table1.lr;
        List<DescVar> vars = lr.getAllVars();

        for (DescVar var : vars) {
            if (table1.getVarDef(var) == null || table2.getVarDef(var) == null) {
                this.table.put(var, null);
                continue;
            }

            if (table1.getVarDef(var).equals(table2.getVarDef(var)))
                this.table.put(var, table1.getVarDef(var));
            else
                this.table.put(var, new ExprUnknown(var.getType()));
        }
    }

    public VarDefTable clone() {
        return new VarDefTable(this);
    }

    public VarDefTable merge(VarDefTable table1, VarDefTable table2) {
        return new VarDefTable(table1, table2);
    }

    public void addVarDef(DescVar var, Expression expr) {
        this.table.put(var, expr);
    }

    public boolean isVarDef(DescVar var) {
        return this.table.get(var) != null;
    }

    public Expression getVarDef(DescVar var) {
        return this.table.get(var);
    }
}
