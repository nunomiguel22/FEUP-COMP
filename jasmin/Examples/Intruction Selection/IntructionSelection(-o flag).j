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
	iconst_1
	ineg

	istore 1

	iconst_1
	iconst_0
	iand

	istore 2

	;IF STATEMENT
	;If Condition
	iload 1
	iflt Label_4
	;Less than results in false
	iconst_0
	goto Label_3
Label_4: ; LabelType: Less than results in true
	iconst_1
Label_3: ; LabelType: Less than exit label
	ifeq Label_1
	;Then statement body
	iinc 1 1

	goto Label_2
Label_1: ; LabelType: Else Statement Body:
	iinc 1 127

Label_2: ; LabelType: Exit If Statement

	iload 1
	ireturn
.end method


