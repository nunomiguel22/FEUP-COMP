; Class Declaration
.class public DefTest
.super java/lang/Object

; Class Members

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public test(I)I
	.limit stack 2
	.limit locals 6
	;IF STATEMENT
	;If Condition
	iload 1
	iconst_0
	;LESS THAN EXPRESSION
	if_icmplt Label_4
	;Less than results in false
	iconst_0
	goto Label_3
Label_4: ; LabelType: Less than results in true
	iconst_1
Label_3: ; LabelType: Less than exit label
	ifeq Label_1
	;Then statement body
	iconst_3
	istore 4

	goto Label_2
Label_1: ; LabelType: Else Statement Body:
	iconst_2
	istore 4

Label_2: ; LabelType: Exit If Statement

	iload 4
	istore 1

Label_5: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 1
	iconst_4
	;LESS THAN EXPRESSION
	if_icmplt Label_8
	;Less than results in false
	iconst_0
	goto Label_7
Label_8: ; LabelType: Less than results in true
	iconst_1
Label_7: ; LabelType: Less than exit label
	ifeq Label_6
	;While Body:
	;IF STATEMENT
	;If Condition
	iload 1
	iconst_0
	;LESS THAN EXPRESSION
	if_icmplt Label_12
	;Less than results in false
	iconst_0
	goto Label_11
Label_12: ; LabelType: Less than results in true
	iconst_1
Label_11: ; LabelType: Less than exit label
	ifeq Label_9
	;Then statement body
	iconst_3
	istore 1

	goto Label_10
Label_9: ; LabelType: Else Statement Body:
Label_10: ; LabelType: Exit If Statement

	goto Label_5
Label_6: ; LabelType: Exit While Statement

	iload 5
	iconst_5
	imul
	istore 1

	iconst_0
	ireturn
.end method


