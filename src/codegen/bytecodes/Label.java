public class Label {

    private static int globalCounter = 0;
    private final int labelCounter;
    private final String name;

    public Label(final String name) {
        this.name = name;
        this.labelCounter = ++globalCounter;
    };

    public String getLabelString() {
        return "Label_" + this.labelCounter + ": ; LabelType: " + this.name;
    }

    @Override
    public String toString() {
        return "Label_" + this.labelCounter;
    }
}
