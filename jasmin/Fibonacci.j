; Class Declaration
.class public Fibonacci
.super java/lang/Object

; Class Members

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public static main([Ljava/lang/String;)V
	.limit stack 2
	.limit locals 2
	new Fibonacci
	dup
	invokespecial Fibonacci/<init>()V
	astore 1

	aload 1
	iconst_2
	invokevirtual Fibonacci/fib(I)I
	invokestatic io/println(I)V

	aload 1
	bipush 8
	invokevirtual Fibonacci/fib(I)I
	invokestatic io/println(I)V

	aload 1
	bipush 9
	invokevirtual Fibonacci/fib(I)I
	invokestatic io/println(I)V

	aload 1
	bipush 10
	invokevirtual Fibonacci/fib(I)I
	invokestatic io/println(I)V

	return
.end method


.method public fib(I)I
	.limit stack 4
	.limit locals 3
	;IF STATEMENT
	;If Condition
	iload 1
	iconst_2
	;LESS THAN EXPRESSION
	if_icmplt Label_14
	;Less than results in false
	iconst_0
	goto Label_13
Label_14: ; LabelType: Less than results in true
	iconst_1
Label_13: ; LabelType: Less than exit label
	ifeq Label_11
	;Then statement body
	iload 1
	istore 2

	goto Label_12
Label_11: ; LabelType: Else Statement Body:
	aload 0
	iload 1
	iconst_1
	isub
	invokevirtual Fibonacci/fib(I)I

	aload 0
	iload 1
	iconst_2
	isub
	invokevirtual Fibonacci/fib(I)I

	iadd
	istore 2

Label_12: ; LabelType: Exit If Statement

	iload 2
	ireturn
.end method


