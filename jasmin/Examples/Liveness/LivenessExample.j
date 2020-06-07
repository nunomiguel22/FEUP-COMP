; Class Declaration
.class public LivenessExample
.super java/lang/Object

; Class Members

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public soManyRegisters()I
	.limit stack 1
	.limit locals 5
	iconst_0
	istore 1

	iload 1
	istore 2

	iload 2
	istore 3

	iload 3
	istore 4

	iload 4
	ireturn
.end method


