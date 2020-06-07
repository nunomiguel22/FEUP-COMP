public class MemberDeclaration {

    private final DescClass cls;
    private String bytecode;

    public MemberDeclaration(final DescClass cls) {
        this.cls = cls;
        this.bytecode = "; Class Members\n";

        for (final DescVar var : this.cls.getMembers().values()) {
            this.bytecode += ".field public " + var.getName() + ' ';
            this.bytecode += var.getType().getBytecode() + "\n";
        }
    }

    @Override
    public String toString() {
        return this.bytecode;
    }

}
