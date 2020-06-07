; Class Declaration
.class public HelloWorld
.super java/lang/Object

; Class Members

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public static main([Ljava/lang/String;)V
	.limit stack 0
	.limit locals 1
	invokestatic ioPlus/printHelloWorld()V

	return
.end method


