# Optimizations

## ``-r=n`` Flag
When this flag is used the compiler builds a liveness table for each method. The liveness table consists of ``use``, ``def``, ``in`` and ``out`` BitSets, the ``in`` and ``out`` bitsets are built using the "backwards" equations. The compiler uses this information to then build a register interference graph. Using the graph coloring method the register indexes are calculated. If the amount of register is bigger than n an exception is thrown. Spilling or other techninques were not used.


## ``-o`` Flag

### Intruction Selection

Regardless of flags the compiler always chooses the best integer push instruction for the value using ``iconst``, ``bipush`` and ``sipush`` when possible.

#### iinc
Adding values to own value will use iinc instead of loading the variable such as:


Java-- Code:
```
i = i + 127
```

Unoptimized:
```
iload 1
bipush 127
iadd
istore 1
```

Optimized:
```
iinc 1 127
```       

#### ineg
Substracting 0 by a number will use iinc instead of doing the normal operation:

Java-- Code:
```
i = 0 - 30
```

Unoptimized:
```
iconst_0
bipush 30
isub
istore 1
```

Optimized:
```
bipush 30
ineg
istore 1
```       

#### iand
``&&`` operation between two boolean will used iand bytecode:

Java-- Code:
```
b = d && c
```

Unoptimized:
```
iload 2
ifeq falseLabel
iload 3
ifeq falseLabel
iconst_1
goto exitLabel
falseLabel:
iconst_0
exitLabel:
```
           
Optimized:
```
iload 2
iload 3
iand
```       

#### iflt ifgt
On ``less than`` comparisons with 0 iflt and ifgt are used instead of loading 0 to the stack.
