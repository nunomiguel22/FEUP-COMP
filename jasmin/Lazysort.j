; Class Declaration
.class public Lazysort
.super Quicksort

; Class Members

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual Quicksort/<init>()V
	return
.end method

.method public beLazy([I)Z
	.limit stack 4
	.limit locals 4
	aload 1
	arraylength
	istore 3

	iconst_0
	istore 2

Label_355: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 2
	iload 3
	iconst_2
	idiv
	;LESS THAN EXPRESSION
	if_icmplt Label_358
	;Less than results in false
	iconst_0
	goto Label_357
Label_358: ; LabelType: Less than results in true
	iconst_1
Label_357: ; LabelType: Less than exit label
	ifeq Label_356
	;While Body:
	aload 1
	iload 2
	iconst_0
	bipush 10
	invokestatic MathUtils/random(II)I
	iastore

	iload 2
	iconst_1
	iadd
	istore 2

	goto Label_355
Label_356: ; LabelType: Exit While Statement

Label_359: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 2
	iload 3
	;LESS THAN EXPRESSION
	if_icmplt Label_362
	;Less than results in false
	iconst_0
	goto Label_361
Label_362: ; LabelType: Less than results in true
	iconst_1
Label_361: ; LabelType: Less than exit label
	ifeq Label_360
	;While Body:
	aload 1
	iload 2
	iconst_0
	bipush 10
	invokestatic MathUtils/random(II)I
	iconst_1
	iadd
	iastore

	iload 2
	iconst_1
	iadd
	istore 2

	goto Label_359
Label_360: ; LabelType: Exit While Statement

	iconst_1
	ireturn
.end method


.method public static main([Ljava/lang/String;)V
	.limit stack 4
	.limit locals 5
	bipush 10
	newarray int
	astore 4

	iconst_0
	istore 3

Label_363: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 3
	aload 4
	arraylength
	;LESS THAN EXPRESSION
	if_icmplt Label_366
	;Less than results in false
	iconst_0
	goto Label_365
Label_366: ; LabelType: Less than results in true
	iconst_1
Label_365: ; LabelType: Less than exit label
	ifeq Label_364
	;While Body:
	aload 4
	iload 3
	aload 4
	arraylength
	iload 3
	isub
	iastore

	iload 3
	iconst_1
	iadd
	istore 3

	goto Label_363
Label_364: ; LabelType: Exit While Statement

	new Lazysort
	dup
	invokespecial Lazysort/<init>()V
	astore 1

	aload 1
	aload 4
	invokevirtual Lazysort/quicksort([I)Z
	pop
	aload 1
	aload 4
	invokevirtual Quicksort/printL([I)Z
	istore 2

	return
.end method


.method public quicksort([I)Z
	.limit stack 5
	.limit locals 3
	;IF STATEMENT
	;If Condition
	iconst_0
	iconst_5
	invokestatic MathUtils/random(II)I
	iconst_4
	;LESS THAN EXPRESSION
	if_icmplt Label_370
	;Less than results in false
	iconst_0
	goto Label_369
Label_370: ; LabelType: Less than results in true
	iconst_1
Label_369: ; LabelType: Less than exit label
	ifeq Label_367
	;Then statement body
	aload 0
	aload 1
	invokevirtual Lazysort/beLazy([I)Z

	pop
	iconst_1
	istore 2

	goto Label_368
Label_367: ; LabelType: Else Statement Body:
	iconst_0
	istore 2

Label_368: ; LabelType: Exit If Statement

	;IF STATEMENT
	;If Condition
	iload 2
	ifeq Label_371
	;Then statement body
	iload 2
	;NEGATION EXPRESSION
	ifeq Label_373
	; Negation results in false
	iconst_0
	goto Label_374
Label_373: ; LabelType: Negation results in true
	iconst_1
Label_374: ; LabelType: Negation Exit Label

	istore 2

	goto Label_372
Label_371: ; LabelType: Else Statement Body:
	aload 0
	aload 1
	iconst_0
	aload 1
	arraylength
	iconst_1
	isub
	invokevirtual Quicksort/quicksort([III)Z

	istore 2

Label_372: ; LabelType: Exit If Statement

	iload 2
	ireturn
.end method


