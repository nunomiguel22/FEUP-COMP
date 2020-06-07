public class ClassDeclaration {

    private final DescClass cls;
    private String bytecode;

    public ClassDeclaration(final DescClass cls) {
        this.cls = cls;
        this.bytecode = "; Class Declaration\n";
        this.bytecode += ".class public " + cls.getReturnType() + "\n";

        if (this.cls.hasSuper())
            this.bytecode += ".super " + cls.getSuperName();
        else
            this.bytecode += ".super java/lang/Object";
    }

    @Override
    public String toString() {
        return this.bytecode;
    }
}
