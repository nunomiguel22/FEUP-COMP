; Class Declaration
.class public MonteCarloPi
.super java/lang/Object

; Class Members

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public performSingleEstimate()Z
	.limit stack 3
	.limit locals 5
	iconst_0
	bipush 100
	isub
	bipush 100
	invokestatic MathUtils/random(II)I
	istore 3

	iconst_0
	bipush 100
	isub
	bipush 100
	invokestatic MathUtils/random(II)I
	istore 4

	iload 3
	iload 3
	imul
	iload 4
	iload 4
	imul
	iadd

	bipush 100
	idiv
	istore 1

	;IF STATEMENT
	;If Condition
	iload 1
	bipush 100
	;LESS THAN EXPRESSION
	if_icmplt Label_272
	;Less than results in false
	iconst_0
	goto Label_271
Label_272: ; LabelType: Less than results in true
	iconst_1
Label_271: ; LabelType: Less than exit label
	ifeq Label_269
	;Then statement body
	iconst_1
	istore 2

	goto Label_270
Label_269: ; LabelType: Else Statement Body:
	iconst_0
	istore 2

Label_270: ; LabelType: Exit If Statement

	iload 2
	ireturn
.end method


.method public estimatePi100(I)I
	.limit stack 2
	.limit locals 5
	iconst_0
	istore 3

	iconst_0
	istore 2

Label_273: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 3
	iload 1
	;LESS THAN EXPRESSION
	if_icmplt Label_276
	;Less than results in false
	iconst_0
	goto Label_275
Label_276: ; LabelType: Less than results in true
	iconst_1
Label_275: ; LabelType: Less than exit label
	ifeq Label_274
	;While Body:
	;IF STATEMENT
	;If Condition
	aload 0
	invokevirtual MonteCarloPi/performSingleEstimate()Z

	ifeq Label_277
	;Then statement body
	iload 2
	iconst_1
	iadd
	istore 2

	goto Label_278
Label_277: ; LabelType: Else Statement Body:
Label_278: ; LabelType: Exit If Statement

	iload 3
	iconst_1
	iadd
	istore 3

	goto Label_273
Label_274: ; LabelType: Exit While Statement

	sipush 400
	iload 2
	imul
	iload 1
	idiv
	istore 4

	iload 4
	ireturn
.end method


.method public static main([Ljava/lang/String;)V
	.limit stack 2
	.limit locals 3
	invokestatic ioPlus/requestNumber()I
	istore 2

	new MonteCarloPi
	dup
	invokespecial MonteCarloPi/<init>()V
	iload 2
	invokevirtual MonteCarloPi/estimatePi100(I)I

	istore 1

	iload 1
	invokestatic ioPlus/printResult(I)V

	return
.end method


