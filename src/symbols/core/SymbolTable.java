import java.util.HashMap;
import java.util.Map;

/**
 * Symbol table with Class, Method headers, Class Members and Method Locals
 */
public class SymbolTable implements JmmTreeConstants {

    private final CompilerData data;
    private final SimpleNode rootNode;
    private final Map<String, DescClass> classImports;
    private DescClass compClass;

    public SymbolTable(final CompilerData data) throws CompilerFailureException {
        this.data = data;
        this.rootNode = this.data.rootNode;
        this.classImports = new HashMap<String, DescClass>();
        this.build();
    }

    public DescClass getCompClass() {
        return this.compClass;
    }

    private void build() throws CompilerFailureException {
        final SimpleNode importBody = this.rootNode.getFirstChild();

        for (int i = 0; i < importBody.jjtGetNumChildren(); ++i)
            this.readImport(importBody.jjtGetChild(i));
        try {
            this.buildCompClass();
        } catch (final SemanticException e) {
            this.errorCheck(e);
        }
    }

    private void buildCompClass() throws SemanticException, CompilerFailureException {
        final SimpleNode classNode = this.data.rootNode.jjtGetChild(1);
        final SimpleNode classHeader = classNode.getFirstChild();
        final SimpleNode classBody = classNode.jjtGetChild(1);

        final String className = classHeader.getFirstChild().identifierVal;

        if (classHeader.jjtGetNumChildren() == 1)
            this.compClass = new DescClass(className);
        else {
            final String superName = classHeader.jjtGetChild(1).identifierVal;

            if (!this.containsImportClass(superName)) {
                final int line = classHeader.jjtGetChild(1).line;
                final int column = classHeader.jjtGetChild(1).column;

                final String message = "Class '" + superName + "' is undeclared";
                throw new SemanticException(message, line, column);
            }
            this.compClass = new DescClass(className, superName);
            this.compClass.setSuperClass(this.getImportClass(superName));
        }

        this.readMembers(classBody);
        this.readMethods(classBody);

    }

    private void readMethods(final SimpleNode classBody) throws CompilerFailureException {

        // Read method Headers
        for (int i = 0; i < classBody.jjtGetNumChildren(); ++i) {
            final SimpleNode node = classBody.jjtGetChild(i);
            if (node.isType(JJTMETHODDECLARATION)) {
                final SimpleNode methodNode = node.getFirstChild();
                try {
                    final DescMethod method = new DescMethod(methodNode, this.compClass);
                    this.compClass.addMethod(method);
                    this.readLocals(methodNode, method);
                } catch (final SemanticException e) {
                    this.errorCheck(e);
                }
            }
        }
    }

    private void readLocals(final SimpleNode methodNode, final DescMethod methodDesc) throws CompilerFailureException {
        final SimpleNode methodBody = methodNode.jjtGetChild(1);

        for (int i = 0; i < methodBody.jjtGetNumChildren(); ++i) {
            final SimpleNode node = methodBody.jjtGetChild(i);
            if (node.isType(JJTPRIMITIVEDECLARATION))
                try {
                    readLocal(node, methodDesc);
                } catch (final SemanticException e) {
                    errorCheck(e);
                }
            else if (node.jjtGetNumChildren() == 2 && node.isType(JJTIDENTIFIERINSTRUCTION)) {
                if (isIdentifierVarDeclaration(node))
                    try {
                        readLocal(node, methodDesc);
                    } catch (final SemanticException e) {
                        errorCheck(e);
                    }
            }
        }
    }

    private void readLocal(final SimpleNode node, final DescMethod methodDesc) throws SemanticException {
        final DescVar var = new DescVar(node);
        final String name = var.getName();
        final String typeName = var.getReturnType();

        if (methodDesc.hasLocal(name)) {
            final String msg = "Local " + name + " already exists";
            throw new SemanticException(msg, node.jjtGetChild(1).line, node.jjtGetChild(1).column);
        }

        if (!node.isType(JJTPRIMITIVEDECLARATION))
            if (!(this.containsImportClass(typeName) || this.compClass.getReturnType().equals(typeName))) {
                final String msg = "class '" + typeName + "' is undefined";
                throw new SemanticException(msg, node.getFirstChild().line, node.getFirstChild().column);
            }

        methodDesc.addLocal(var);
    }

    private void readMembers(final SimpleNode classBody) throws CompilerFailureException {
        for (int i = 0; i < classBody.jjtGetNumChildren(); ++i) {
            final SimpleNode node = classBody.jjtGetChild(i);
            if (node.isType(JJTMEMBER)) {
                try {
                    this.readMember(node);
                } catch (final SemanticException e) {
                    errorCheck(e);
                }
            }
        }
    }

    private void readMember(final SimpleNode member) throws SemanticException {
        final SimpleNode node = member.getFirstChild();
        final DescVar var = new DescVar(node);

        if (this.compClass.hasMember(var.getName())) {
            final int line = node.jjtGetChild(var.getIdentifierIndex()).line;
            final int column = node.jjtGetChild(var.getIdentifierIndex()).column;
            final String msg = "Member " + var.getName() + " was already declared";
            throw new SemanticException(msg, line, column);
        }
        this.compClass.addMember(var);
    }

    private void readImport(final SimpleNode importNode) {
        final SimpleNode identifierNode = importNode.getFirstChild();
        final String className = identifierNode.identifierVal;

        // Class import
        if (importNode.jjtGetNumChildren() == 1) {
            if (this.containsImportClass(className)) {
                Compiler.printWarning(identifierNode, "duplicate class import");
                return;
            }
            final DescClass cls = new DescClass(className);
            this.classImports.put(cls.getReturnType(), cls);
            return;
        }

        // Function Import

        // Parent Class
        if (!this.containsImportClass(className)) {
            final DescClass cls = new DescClass(className);
            this.classImports.put(cls.getReturnType(), cls);
        }
        final DescClass parentClass = this.getImportClass(className);

        // Return type
        DescType returnType = new DescType("void");
        if (importNode.jjtGetNumChildren() == 3) {
            final SimpleNode returnNode = importNode.jjtGetChild(2);
            returnType = new DescType(returnNode);
        }

        // Function Name
        final String functionName = importNode.jjtGetChild(1).getFirstChild().identifierVal;

        // Create method base
        final DescMethod method = new DescMethod(importNode, parentClass, returnType, functionName,
                importNode.isStatic);

        // Add Parameters

        final SimpleNode paramsNode = importNode.jjtGetChild(1).jjtGetChild(1);
        final int paramsNum = paramsNode.jjtGetNumChildren();

        for (int i = 0; i < paramsNum; ++i) {
            final SimpleNode arg = paramsNode.jjtGetChild(i);
            String typeVal = "void";
            if (arg.isType(JmmTreeConstants.JJTARRAYDECLARATION))
                continue;

            if (arg.isType(JmmTreeConstants.JJTPRIMITIVE)) {
                typeVal = arg.typeVal;
                if (paramsNum - i > 1)
                    if (paramsNode.jjtGetChild(i + 1).isType(JmmTreeConstants.JJTARRAYDECLARATION))
                        typeVal += "[]";

            } else if (arg.isType(JmmTreeConstants.JJTIDENTIFIER))
                typeVal = arg.identifierVal;

            method.addParameter(new DescType(typeVal));
        }

        // Build signature
        method.buildSignature();

        if (parentClass.hasMethod(method.getSignature())) {
            Compiler.printWarning(identifierNode, "duplicate method import");
            return;
        } else
            parentClass.addMethod(method);

    }

    public boolean containsImportClass(final String name) {
        return this.classImports.containsKey(name);
    }

    public DescClass getImportClass(final String name) {
        return this.classImports.get(name);
    }

    public void printTable() {
        System.out.println(this.toString());
    }

    public DescClass resolveClass(final String name) {
        if (this.compClass != null && this.compClass.getReturnType().equals(name))
            return this.compClass;

        if (this.containsImportClass(name))
            return this.getImportClass(name);

        return null;
    }

    public boolean hasClass(final String name) {
        if (this.compClass != null && this.compClass.getReturnType().equals(name))
            return true;

        return this.containsImportClass(name);
    }

    @Override
    public String toString() {
        String table = "\tSYMBOL TABLE\n";

        if (this.classImports.size() > 0) {
            table += "Imported Classes:\n";

            for (final DescClass cls : classImports.values()) {
                table += "Class Name: " + cls.getReturnType() + "\n";
                table += "\tMethods\n";
                table += "----------------------------------------------------------------------------\n";
                table += String.format("| %-12s | %-6s | %-15s | %-30s |\n", "Return", "Static", "Name", "Parameters");
                table += "----------------------------------------------------------------------------\n";

                final Map<MethodSignature, DescMethod> methods = cls.getMethods();

                for (final DescMethod method : methods.values()) {
                    table += String.format("| %-12s | %-6s | %-15s | %-30s |\n", method.getReturnType(),
                            method.isStatic(), method.getName(), method.getParameterString());
                }
                table += "----------------------------------------------------------------------------\n\n";
            }
        }

        table += "Compiler Class: " + this.compClass.getReturnType() + "\n";

        if (this.compClass.getMembers().size() > 0) {
            table += "\tMembers\n";

            table += "-------------------------------------\n";
            table += String.format("| %-15s | %-15s |\n", "Type", "Name");
            table += "-------------------------------------\n";

            for (final DescVar var : this.compClass.getMembers().values())
                table += String.format("| %-15s | %-15s |\n", var.getReturnType(), var.getName());

            table += "-------------------------------------\n\n";
        }

        table += "\tMethods\n";
        table += "----------------------------------------------------------------------------\n";
        table += String.format("| %-12s | %-6s | %-15s | %-30s |\n", "Return", "Static", "Name", "Parameters");
        table += "----------------------------------------------------------------------------\n";

        for (final DescMethod method : this.compClass.getMethods().values())
            table += String.format("| %-12s | %-6s | %-15s | %-30s |\n", method.getReturnType(), method.isStatic(),
                    method.getName(), method.getParameterString());
        table += "----------------------------------------------------------------------------\n\n";

        table += "\tLocals\n";
        for (final DescMethod method : this.compClass.getMethods().values()) {
            table += "Method: " + method.getDeclarationString() + "\n";

            table += "-------------------------------------\n";
            table += String.format("| %-15s | %-15s |\n", "Type", "Name");
            table += "-------------------------------------\n";

            for (final DescVar var : method.getLocalMap().values())
                table += String.format("| %-15s | %-15s |\n", var.getReturnType(), var.getName());

            table += "-------------------------------------\n";
        }

        return table;
    }

    private void errorCheck(final SemanticException e) throws CompilerFailureException {
        Compiler.printSemanticError(e, ++this.data.errors);
        if (Compiler.errors >= 10) {
            Compiler.printExitMessage();
            throw new CompilerFailureException();
        }
    }

    private boolean isIdentifierVarDeclaration(final SimpleNode node) {
        if (node.isType(JJTWHILESTATEMENT))
            return false;

        if (node.isType(JJTIFSTATEMENT))
            return false;

        return node.jjtGetChild(node.jjtGetNumChildren() - 1).isType(JJTVARDECLARATION);
    }

}
