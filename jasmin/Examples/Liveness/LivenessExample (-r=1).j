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
	.limit locals 2
	iconst_0
	istore 1

	iload 1
	istore 1

	iload 1
	istore 1

	iload 1
	istore 1

	iload 1
	ireturn
.end method


