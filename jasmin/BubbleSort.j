; Class Declaration
.class public BubbleSort
.super java/lang/Object

; Class Members
.field public arr [I

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public bubbleSort([I)I
	.limit stack 12
	.limit locals 6
	iconst_0
	istore 4

	iconst_0
	istore 2

	aload 0
	invokevirtual BubbleSort/getArr()[I
	arraylength


	istore 5

Label_15: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 2
	;NEGATION EXPRESSION
	ifeq Label_17
	; Negation results in false
	iconst_0
	goto Label_18
Label_17: ; LabelType: Negation results in true
	iconst_1
Label_18: ; LabelType: Negation Exit Label

	ifeq Label_16
	;While Body:
	iconst_1
	istore 2

	iconst_0
	istore 4

Label_19: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 4
	iload 5
	iconst_1
	isub
	;LESS THAN EXPRESSION
	if_icmplt Label_22
	;Less than results in false
	iconst_0
	goto Label_21
Label_22: ; LabelType: Less than results in true
	iconst_1
Label_21: ; LabelType: Less than exit label
	ifeq Label_20
	;While Body:
	;IF STATEMENT
	;If Condition
	aload 1
	iload 4
	iconst_1
	iadd
	iaload
	aload 1
	iload 4
	iaload
	;LESS THAN EXPRESSION
	if_icmplt Label_26
	;Less than results in false
	iconst_0
	goto Label_25
Label_26: ; LabelType: Less than results in true
	iconst_1
Label_25: ; LabelType: Less than exit label
	ifeq Label_23
	;Then statement body
	aload 1
	iload 4
	iaload
	istore 3

	aload 1
	iload 4
	aload 1
	iload 4
	iconst_1
	iadd
	iaload
	iastore

	aload 1
	iload 4
	iconst_1
	iadd
	iload 3
	iastore

	iconst_0
	istore 2

	goto Label_24
Label_23: ; LabelType: Else Statement Body:
Label_24: ; LabelType: Exit If Statement

	iload 4
	iconst_1
	iadd
	istore 4

	goto Label_19
Label_20: ; LabelType: Exit While Statement

	goto Label_15
Label_16: ; LabelType: Exit While Statement

	iconst_0
	ireturn
.end method


.method public printArray([I)I
	.limit stack 3
	.limit locals 4
	iconst_0
	istore 2

	aload 1
	arraylength
	istore 3

Label_27: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 2
	iload 3
	;LESS THAN EXPRESSION
	if_icmplt Label_30
	;Less than results in false
	iconst_0
	goto Label_29
Label_30: ; LabelType: Less than results in true
	iconst_1
Label_29: ; LabelType: Less than exit label
	ifeq Label_28
	;While Body:
	aload 0
	invokevirtual BubbleSort/getArr()[I
	iload 2
	iaload


	invokestatic io/println(I)V

	iload 2
	iconst_1
	iadd
	istore 2

	goto Label_27
Label_28: ; LabelType: Exit While Statement

	iconst_0
	ireturn
.end method


.method public getArr()[I
	.limit stack 1
	.limit locals 1
	aload 0
	getfield BubbleSort/arr [I
	areturn
.end method


.method public initArray()Z
	.limit stack 3
	.limit locals 1
	bipush 7
	newarray int
	aload 0
	swap
	putfield BubbleSort/arr [I

	aload 0
	getfield BubbleSort/arr [I
	iconst_0
	bipush 64
	iastore

	aload 0
	getfield BubbleSort/arr [I
	iconst_1
	bipush 34
	iastore

	aload 0
	getfield BubbleSort/arr [I
	iconst_2
	bipush 25
	iastore

	aload 0
	getfield BubbleSort/arr [I
	iconst_3
	bipush 12
	iastore

	aload 0
	getfield BubbleSort/arr [I
	iconst_4
	bipush 22
	iastore

	aload 0
	getfield BubbleSort/arr [I
	iconst_5
	bipush 11
	iastore

	aload 0
	getfield BubbleSort/arr [I
	bipush 6
	bipush 90
	iastore

	iconst_0
	ireturn
.end method


.method public static main([Ljava/lang/String;)V
	.limit stack 2
	.limit locals 3
	new BubbleSort
	dup
	invokespecial BubbleSort/<init>()V
	astore 1

	aload 1
	invokevirtual BubbleSort/initArray()Z
	pop
	aload 1
	aload 1
	invokevirtual BubbleSort/getArr()[I
	invokevirtual BubbleSort/bubbleSort([I)I
	pop
	aload 1
	aload 1
	invokevirtual BubbleSort/getArr()[I
	invokevirtual BubbleSort/printArray([I)I
	pop
	return
.end method


