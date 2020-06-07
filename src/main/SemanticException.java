/**
 * Exception used to display semantic erros, displays line and column of an
 * error using the format: Filepath.jmm (10, 22) Error: "errormessage"
 */
public class SemanticException extends Exception {
    private static final long serialVersionUID = 121313123221L;

    public int line;
    public int column;

    public SemanticException(final String message, final int line, final int column) {
        super(message);
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
