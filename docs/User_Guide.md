
For this project, you need to [install Gradle](https://gradle.org/install/)

## Compile

To compile the program, run ``gradle build``. This will compile your classes to ``classes/main/java`` and copy the JAR file to the root directory. The JAR file will have the same name as the repository folder.

### Run

To run you have two options: Run the ``.class`` files or run the JAR.

### Run ``.class``

To run the ``.class`` files, do the following:

```cmd
java -cp "./build/classes/java/main/" <class_name> <arguments>
```

Where ``<class_name>`` is the name of the class you want to run and ``<arguments>`` are the arguments to be passed to ``main()``.

### Run ``.jar``

To run the JAR, do the following command:

```cmd
java -jar <jar filename> <arguments>
```

Where ``<jar filename>`` is the name of the JAR file that has been copied to the root folder, and ``<arguments>`` are the arguments to be passed to ``main()``.

## Test

To test the program, run ``gradle test``. This will execute the build, and run the JUnit tests in the ``test`` folder. If you want to see output printed during the tests, use the flag ``-i`` (i.e., ``gradle test -i``).

## Printing AST and symbol table

After the filename add ``-printAST`` to print the AST and/or ``-printTable`` to print the symbol table. Such as:
```cmd
java -jar comp2020-4f.jar test/custom/launch.jmm -printAST -printTable
```

## ``-o`` and ``-r`` flags

After the filename add ``-o`` to run optimizations.
The ``-r`` flag used as ``-r=n`` limits the number of registers to be used by the compiler, if the compiler needs more registers than allowed it will present an error. This flag will enable liveness analysis as explained in the optimization section.
Example of a command:
```cmd
java -jar comp2020-4f.jar test/custom/launch.jmm -printAST -printTable -o -r=4
```

## Visual Studio Code

To use with Visual Studio Code install the Java extension and open the folder on the editor. There are three tasks configured:

* ``gradle test`` will run tests without building.

* ``gradle build`` will build and run tests.

* ``gradle build - no tests`` will run gradle build without running the tests.

``gradle clean`` will be run before the build tasks.

There are four debug configurations, all will automatically run the compiler using the jmm code in ``test/custom/launch.jmm``. The profiles are:

* ``debug`` Runs debug without extra options.

* ``Debug Print`` Runs debug and print the symbol table and the AST.

* ``Debug -o`` Runs debug using the -o flag optimizations.

* ``Debug -r=3`` Runs debug and attempts to restrict register use to 3.


## Jasmin
If the compiler successfuly completes the compilation without errors a file with the format ClassName.j will be writen under the folder ``jasmin``. To compile this file using jasmin,  [download jasmin](https://sourceforge.net/projects/jasmin/files/) and place ``jasmin.jar`` inside the jasmin folder, then run the command:

```cmd
java -jar jasmin.jar <ClassName>.j
```
