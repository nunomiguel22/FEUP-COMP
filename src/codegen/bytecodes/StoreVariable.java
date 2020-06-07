public class StoreVariable {
    private final DescMethod methodDesc;
    private final DescClass compClass;
    private String bytecode;

    public StoreVariable(final MethodGenerator method, final String name) {
        this.methodDesc = method.getDescriptor();
        this.compClass = this.methodDesc.getParentClass();
        DescVar var = methodDesc.getMethodVariable(name);
        if (var != null) {
            final int index = method.getLocalRegistry().getVarIndex(var);
            this.bytecode = "\t" + var.getType().getReturnByteCode() + "store " + index;
            method.removeStack(1);
        } else {
            var = compClass.getMember(name);
            method.addStack(1);
            this.bytecode = "\taload 0\n\tswap\n\tputfield " + this.compClass.getReturnType();
            this.bytecode += "/" + var.getName() + " " + var.getType().getBytecode();
            method.removeStack(2);
        }
    }

    @Override
    public String toString() {
        return this.bytecode;
    }

}
