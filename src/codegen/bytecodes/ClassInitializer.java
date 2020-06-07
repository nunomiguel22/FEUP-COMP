public class ClassInitializer {

    private final DescClass cls;
    private String bytecode;

    public ClassInitializer(final DescClass cls) {
        this.cls = cls;

        this.bytecode = "; Standard Initializer\n";

        this.bytecode += ".method public <init>()V\n";
        this.bytecode += ("\taload 0\n");

        if (!this.cls.hasSuper())
            this.bytecode += "\tinvokenonvirtual java/lang/Object/<init>()V\n";
        else
            this.bytecode += "\tinvokenonvirtual " + this.cls.getSuperName() + "/<init>()V\n";

        this.bytecode += "\treturn\n";
        this.bytecode += ".end method";
    }

    @Override
    public String toString() {
        return this.bytecode;
    }

}
