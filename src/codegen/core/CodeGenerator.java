import java.io.*;

/**
 * Generates code for the entire class and does Instruction Selection
 * optimization
 */
public class CodeGenerator {
    private final CompilerData data;
    private final DescClass compClass;
    private String code;
    private FileWriter writer;
    private final String filename;
    private final boolean oFlag;

    public CodeGenerator(final CompilerData data, final boolean oFlag) {
        this.data = data;
        this.oFlag = oFlag;
        this.code = new String();
        this.compClass = this.data.table.getCompClass();
        this.filename = "jasmin/" + this.data.table.getCompClass().getReturnType() + ".j";
        this.createFile();

        try {
            writer = new FileWriter(filename);
        } catch (final IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void build() {
        this.genClassDeclaration();
        this.genMemberDeclarations();
        this.genClassInitializer();
        this.genMethods();

        this.write(this.code);
        this.closeWriter();
    }

    private void createFile() {
        try {
            final File file = new File(filename);
            file.createNewFile();
        } catch (final IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    private void genClassDeclaration() {
        final ClassDeclaration decl = new ClassDeclaration(this.compClass);
        this.code += decl + "\n\n";
    }

    private void genMemberDeclarations() {
        final MemberDeclaration md = new MemberDeclaration(this.compClass);
        this.code += md + "\n";
    }

    private void genClassInitializer() {
        final ClassInitializer ci = new ClassInitializer(this.compClass);
        this.code += ci + "\n\n";
    }

    private void genMethods() {
        for (final DescMethod method : compClass.getMethods().values())
            this.code += new MethodGenerator(method, this.data, this.oFlag);
    }

    private void write(final String str) {
        try {
            writer.write(str);
        } catch (final IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    private void closeWriter() {
        try {
            writer.close();
        } catch (final IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }
}
