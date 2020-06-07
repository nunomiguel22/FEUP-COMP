# Control Flow Graph

### Summary
After the generation of the abstract syntax tree and the symbol table a control flow graph is created for each method of the class to be compiled. The graph nodes are basic blocks of statements with links between branches. As the control flow graph is built, the AST nodes containing the statements are transformed to CFG statements to be used in the basic blocks.

### Cleanup
After the initial control flow graph is build a cleanup operation is performed that removes empty blocks that may be left in the creation of branches. All individual statements are then linked with their predecessors and successors.

### Purpose
The control flow graphs facilitate dataflow analysis allowing for optimizations and verifications such as liveness analysis, variable definition status, constant propagation, etc... More in the optimization section.
