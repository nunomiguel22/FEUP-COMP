; Class Declaration
.class public Simple
.super java/lang/Object

; Class Members

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public add(II)I
	.limit stack 2
	.limit locals 4
	iload 1
	aload 0
	invokevirtual Simple/constInstr()I

	iadd
	istore 3

	iload 3
	ireturn
.end method


.method public constInstr()I
	.limit stack 1
	.limit locals 2
	iconst_0
	istore 1

	iconst_4
	istore 1

	bipush 8
	istore 1

	bipush 14
	istore 1

	sipush 250
	istore 1

	sipush 400
	istore 1

	sipush 1000
	istore 1

	ldc 100474650
	istore 1

	bipush 10
	istore 1

	iload 1
	ireturn
.end method


.method public static main([Ljava/lang/String;)V
	.limit stack 3
	.limit locals 5
	bipush 20
	istore 1

	bipush 10
	istore 2

	new Simple
	dup
	invokespecial Simple/<init>()V
	astore 4

	aload 4
	iload 1
	iload 2
	invokevirtual Simple/add(II)I
	istore 3

	iload 3
	invokestatic io/println(I)V

	return
.end method


