public class LoadVariable {
    private final DescMethod methodDesc;
    private final DescClass compClass;
    private String bytecode;

    public LoadVariable(final MethodGenerator method, final String name) {
        this.methodDesc = method.getDescriptor();
        this.compClass = this.methodDesc.getParentClass();
        DescVar var = methodDesc.getMethodVariable(name);
        if (var != null) {
            final int index = method.getLocalRegistry().getVarIndex(var);
            this.bytecode = "\t" + var.getType().getReturnByteCode() + "load " + index;
        } else {
            var = compClass.getMember(name);
            this.bytecode = "\taload 0\n\tgetfield " + this.compClass.getReturnType();
            this.bytecode += "/" + var.getName() + " " + var.getType().getBytecode();
        }
        method.addStack(1);
    }

    @Override
    public String toString() {
        return this.bytecode;
    }

}
