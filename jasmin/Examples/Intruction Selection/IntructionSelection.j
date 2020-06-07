; Class Declaration
.class public IntructionSelection
.super java/lang/Object

; Class Members

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public example()I
	.limit stack 3
	.limit locals 3
	iconst_0
	iconst_1
	isub
	istore 1

	;AND EXPRESSION
	iconst_1
	ifeq Label_2
	iconst_0
	ifeq Label_2
	; And results in true
	iconst_1
	goto Label_1
Label_2:
	iconst_0
Label_1:
	istore 2

	;IF STATEMENT
	;If Condition
	iload 1
	iconst_0
	;LESS THAN EXPRESSION
	if_icmplt Label_6
	;Less than results in false
	iconst_0
	goto Label_5
Label_6: ; LabelType: Less than results in true
	iconst_1
Label_5: ; LabelType: Less than exit label
	ifeq Label_3
	;Then statement body
	iload 1
	iconst_1
	iadd
	istore 1

	goto Label_4
Label_3: ; LabelType: Else Statement Body:
	iload 1
	bipush 127
	iadd
	istore 1

Label_4: ; LabelType: Exit If Statement

	iload 1
	ireturn
.end method


