/**
 * Driver Class reads arguments and initializes the compiler
 */
public class Main {
    public static void main(final String args[]) throws ParseException, CompilerFailureException {
        if (args.length == 0) {
            System.out.println("Not enough arguments");
            System.exit(1);
        }
        boolean printAST = false;
        boolean printTable = false;
        boolean oFlag = false;
        int regCount = 0;

        for (int i = 1; i < args.length; ++i) {
            if (args[i].equals("-printAST")) {
                printAST = true;
                continue;
            }
            if (args[i].equals("-printTable")) {
                printTable = true;
                continue;
            }
            if (args[i].equals("-o")) {
                oFlag = true;
                continue;
            }
            if (args[i].substring(0, 3).equals("-r=")) {
                regCount = Integer.valueOf(args[i].substring(3));
                continue;
            }

        }

        final Compiler compiler = new Compiler(args[0], printAST, printTable, oFlag, regCount);
        compiler.compile();

    }
}
