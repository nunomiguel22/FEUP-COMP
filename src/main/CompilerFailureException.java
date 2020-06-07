/**
 * When the compiler failures this exception is used in order to not exit the
 * JVM in unit tests
 */
public class CompilerFailureException extends Exception {

    private static final long serialVersionUID = 1213131231L;

    public CompilerFailureException() {
        super("Compilation failure");
    }

}
