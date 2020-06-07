; Class Declaration
.class public FindMaximum
.super java/lang/Object

; Class Members
.field public test_arr [I

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public find_maximum([I)I
	.limit stack 6
	.limit locals 5
	iconst_1
	istore 2

	aload 1
	iconst_0
	iaload
	istore 3

Label_539: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 2
	aload 1
	arraylength
	;LESS THAN EXPRESSION
	if_icmplt Label_542
	;Less than results in false
	iconst_0
	goto Label_541
Label_542: ; LabelType: Less than results in true
	iconst_1
Label_541: ; LabelType: Less than exit label
	ifeq Label_540
	;While Body:
	aload 1
	iload 2
	iaload
	istore 4

	;IF STATEMENT
	;If Condition
	iload 3
	iload 4
	;LESS THAN EXPRESSION
	if_icmplt Label_546
	;Less than results in false
	iconst_0
	goto Label_545
Label_546: ; LabelType: Less than results in true
	iconst_1
Label_545: ; LabelType: Less than exit label
	ifeq Label_543
	;Then statement body
	iload 4
	istore 3

	goto Label_544
Label_543: ; LabelType: Else Statement Body:
Label_544: ; LabelType: Exit If Statement

	iload 2
	iconst_1
	iadd
	istore 2

	goto Label_539
Label_540: ; LabelType: Exit While Statement

	iload 3
	ireturn
.end method


.method public build_test_arr()I
	.limit stack 4
	.limit locals 1
	iconst_5
	newarray int
	aload 0
	swap
	putfield FindMaximum/test_arr [I

	aload 0
	getfield FindMaximum/test_arr [I
	iconst_0
	bipush 14
	iastore

	aload 0
	getfield FindMaximum/test_arr [I
	iconst_1
	bipush 28
	iastore

	aload 0
	getfield FindMaximum/test_arr [I
	iconst_2
	iconst_0
	iastore

	aload 0
	getfield FindMaximum/test_arr [I
	iconst_3
	iconst_0
	iconst_5
	isub
	iastore

	aload 0
	getfield FindMaximum/test_arr [I
	iconst_4
	bipush 12
	iastore

	iconst_0
	ireturn
.end method


.method public static main([Ljava/lang/String;)V
	.limit stack 2
	.limit locals 2
	new FindMaximum
	dup
	invokespecial FindMaximum/<init>()V
	astore 1

	aload 1
	invokevirtual FindMaximum/build_test_arr()I
	pop
	aload 1
	aload 1
	invokevirtual FindMaximum/get_array()[I
	invokevirtual FindMaximum/find_maximum([I)I
	invokestatic ioPlus/printResult(I)V

	return
.end method


.method public get_array()[I
	.limit stack 1
	.limit locals 1
	aload 0
	getfield FindMaximum/test_arr [I
	areturn
.end method


