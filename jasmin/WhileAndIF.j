; Class Declaration
.class public WhileAndIF
.super java/lang/Object

; Class Members

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public static main([Ljava/lang/String;)V
	.limit stack 4
	.limit locals 5
	bipush 20
	istore 1

	bipush 10
	istore 2

	bipush 10
	newarray int
	astore 4

	;IF STATEMENT
	;If Condition
	iload 1
	iload 2
	;LESS THAN EXPRESSION
	if_icmplt Label_530
	;Less than results in false
	iconst_0
	goto Label_529
Label_530: ; LabelType: Less than results in true
	iconst_1
Label_529: ; LabelType: Less than exit label
	ifeq Label_527
	;Then statement body
	iload 1
	iconst_1
	isub
	istore 3

	goto Label_528
Label_527: ; LabelType: Else Statement Body:
	iload 2
	iconst_1
	isub
	istore 3

Label_528: ; LabelType: Exit If Statement

Label_531: ; LabelType: WHILE STATEMENT
	;While Condition:
	iconst_0
	iconst_1
	isub

	iload 3
	;LESS THAN EXPRESSION
	if_icmplt Label_534
	;Less than results in false
	iconst_0
	goto Label_533
Label_534: ; LabelType: Less than results in true
	iconst_1
Label_533: ; LabelType: Less than exit label
	ifeq Label_532
	;While Body:
	aload 4
	iload 3
	iload 1
	iload 2
	isub
	iastore

	iload 3
	iconst_1
	isub
	istore 3

	iload 1
	iconst_1
	isub
	istore 1

	iload 2
	iconst_1
	isub
	istore 2

	goto Label_531
Label_532: ; LabelType: Exit While Statement

	iconst_0
	istore 3

Label_535: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 3
	aload 4
	arraylength
	;LESS THAN EXPRESSION
	if_icmplt Label_538
	;Less than results in false
	iconst_0
	goto Label_537
Label_538: ; LabelType: Less than results in true
	iconst_1
Label_537: ; LabelType: Less than exit label
	ifeq Label_536
	;While Body:
	aload 4
	iload 3
	iaload
	invokestatic io/println(I)V

	iload 3
	iconst_1
	iadd
	istore 3

	goto Label_535
Label_536: ; LabelType: Exit While Statement

	return
.end method


