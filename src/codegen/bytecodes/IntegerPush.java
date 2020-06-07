
public class IntegerPush {
    private final int value;

    public IntegerPush(final int val, final MethodGenerator method) {
        this.value = val;
        method.addStack(1);
    }

    public IntegerPush(final boolean val, final MethodGenerator method) {
        this.value = val ? 1 : 0;
        method.addStack(1);
    }

    @Override
    public String toString() {

        if (value == -1)
            return "\ticonst_m1";

        if (value > -1 && value < 6)
            return "\ticonst_" + value;

        if (value > -129 && value < 128)
            return "\tbipush " + value;

        if (value > -32769 && value < 32768)
            return "\tsipush " + value;

        return "\tldc " + value;
    }
}
