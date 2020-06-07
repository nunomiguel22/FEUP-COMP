import java.io.*;

/**
 * Main compiler class, controls compiler workflow, error handling, etc
 */
public class Compiler {

    private InputStream fileStream;
    private final boolean printAST;
    private final boolean printTable;
    private final boolean oFlag;
    private final int regCount;
    private SimpleNode root;
    private CompilerData compilerData;

    private static String filename;
    public static int errors = 0;
    public static int warnings = 0;

    public Compiler(final String filename, final boolean printAST, final boolean printTable, final boolean oFlag,
            final int regCount) {
        try {
            System.out.println("Compiling " + filename + "\n");
            Compiler.filename = filename;
            this.fileStream = new FileInputStream(filename);
        } catch (final FileNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        }
        this.printAST = printAST;
        this.printTable = printTable;
        this.oFlag = oFlag;
        this.regCount = regCount;
    }

    public void compile() throws CompilerFailureException, ParseException {
        this.parse();

        this.buildSymbolTable();

        this.buildIR();

        this.optimize(this.oFlag, this.regCount);

        this.generateCode(this.oFlag);

        printExitMessage();
    }

    private void parse() throws ParseException {
        System.out.println(filename + " \u001B[36mInfo:\u001B[0m building AST");
        final Jmm parser = new Jmm(fileStream);
        root = parser.program();

        if (parser.errorCount > 0) {
            printExitMessage();
            throw new ParseException();
        }
        this.compilerData = new CompilerData(root);

        if (printAST) {
            System.out.println("\nAST Dump:\n");
            compilerData.rootNode.dump("");
            System.out.println("\n");
        }
    }

    private void buildSymbolTable() throws CompilerFailureException {
        this.compilerData.table = new SymbolTable(this.compilerData);

        if (compilerData.errors > 0) {
            printExitMessage();
            throw new CompilerFailureException();
        }

        if (this.printTable)
            this.compilerData.table.printTable();
    }

    private void buildIR() throws CompilerFailureException {
        final CFGBuilder cfg = new CFGBuilder(this.compilerData);
        cfg.build();

        if (compilerData.errors > 0) {
            printExitMessage();
            throw new CompilerFailureException();
        }
    }

    private void optimize(final boolean oFlag, final int regCount) throws CompilerFailureException {
        final Optimizer opt = new Optimizer(this.compilerData, oFlag, regCount);
        opt.optimize();

        if (compilerData.errors > 0) {
            printExitMessage();
            throw new CompilerFailureException();
        }
    }

    private void generateCode(final boolean oFlag) {
        final CodeGenerator gen = new CodeGenerator(this.compilerData, oFlag);
        gen.build();
    }

    public static void printWarning(final SimpleNode node, final String message) {
        String header = filename + " (" + node.line + "," + node.column + ") ";
        header += "\u001B[33mWarning: \u001B[0m";

        System.out.println(header + message);

        ++warnings;
    }

    public static void printParseError(final ParseException e, final String type, final int errorCount) {
        String header = filename + " (" + e.currentToken.beginLine + ",";
        header += e.currentToken.beginColumn + ")";
        header += "\u001B[31m Error:\n\u001B[0m";
        System.out.println(header + e);
        Compiler.errors = errorCount;
    }

    public static void printSemanticError(final SemanticException e, final int errorCount) {
        String header = filename + " (" + e.line + ",";
        header += e.column + ")";
        header += "\u001B[31m Error: \u001B[0m";
        System.out.println(header + e);
        Compiler.errors = errorCount;
    }

    public static void printExitMessage() {
        String outcome = "Compiler";
        if (Compiler.errors > 0) {
            outcome += "\u001B[31m failed\u001B[0m with ";
            outcome += Compiler.errors + " error(s)";

        } else
            outcome += " was\u001B[32m successful\u001B[0m with 0 error(s)";
        outcome += " and " + Compiler.warnings + " warning(s)";
        System.out.println(outcome);
    }
}
