; Class Declaration
.class public QuickSort
.super java/lang/Object

; Class Members

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public partition([III)I
	.limit stack 15
	.limit locals 8
	aload 1
	iload 3
	iaload
	istore 4

	iload 2
	istore 6

	iload 2
	istore 7

Label_547: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 7
	iload 3
	;LESS THAN EXPRESSION
	if_icmplt Label_550
	;Less than results in false
	iconst_0
	goto Label_549
Label_550: ; LabelType: Less than results in true
	iconst_1
Label_549: ; LabelType: Less than exit label
	ifeq Label_548
	;While Body:
	;IF STATEMENT
	;If Condition
	aload 1
	iload 7
	iaload
	iload 4
	;LESS THAN EXPRESSION
	if_icmplt Label_554
	;Less than results in false
	iconst_0
	goto Label_553
Label_554: ; LabelType: Less than results in true
	iconst_1
Label_553: ; LabelType: Less than exit label
	ifeq Label_551
	;Then statement body
	aload 1
	iload 6
	iaload
	istore 5

	aload 1
	iload 6
	aload 1
	iload 7
	iaload
	iastore

	aload 1
	iload 7
	iload 5
	iastore

	iload 6
	iconst_1
	iadd
	istore 6

	goto Label_552
Label_551: ; LabelType: Else Statement Body:
Label_552: ; LabelType: Exit If Statement

	iload 7
	iconst_1
	iadd
	istore 7

	goto Label_547
Label_548: ; LabelType: Exit While Statement

	aload 1
	iload 6
	iaload
	istore 5

	aload 1
	iload 6
	aload 1
	iload 3
	iaload
	iastore

	aload 1
	iload 3
	iload 5
	iastore

	iload 6
	ireturn
.end method


.method public printL([I)Z
	.limit stack 4
	.limit locals 3
	iconst_0
	istore 2

Label_555: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 2
	aload 1
	arraylength
	;LESS THAN EXPRESSION
	if_icmplt Label_558
	;Less than results in false
	iconst_0
	goto Label_557
Label_558: ; LabelType: Less than results in true
	iconst_1
Label_557: ; LabelType: Less than exit label
	ifeq Label_556
	;While Body:
	aload 1
	iload 2
	iaload
	invokestatic io/println(I)V

	iload 2
	iconst_1
	iadd
	istore 2

	goto Label_555
Label_556: ; LabelType: Exit While Statement

	iconst_1
	ireturn
.end method


.method public static main([Ljava/lang/String;)V
	.limit stack 4
	.limit locals 4
	bipush 10
	newarray int
	astore 3

	iconst_0
	istore 2

Label_559: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 2
	aload 3
	arraylength
	;LESS THAN EXPRESSION
	if_icmplt Label_562
	;Less than results in false
	iconst_0
	goto Label_561
Label_562: ; LabelType: Less than results in true
	iconst_1
Label_561: ; LabelType: Less than exit label
	ifeq Label_560
	;While Body:
	aload 3
	iload 2
	aload 3
	arraylength
	iload 2
	isub
	iastore

	iload 2
	iconst_1
	iadd
	istore 2

	goto Label_559
Label_560: ; LabelType: Exit While Statement

	new QuickSort
	dup
	invokespecial QuickSort/<init>()V
	astore 1

	aload 1
	aload 3
	invokevirtual QuickSort/quicksort([I)Z
	pop
	aload 1
	aload 3
	invokevirtual QuickSort/printL([I)Z
	pop
	return
.end method


.method public quicksort([I)Z
	.limit stack 5
	.limit locals 2
	aload 0
	aload 1
	iconst_0
	aload 1
	arraylength
	iconst_1
	isub
	invokevirtual QuickSort/quicksort([III)Z

	ireturn
.end method


.method public quicksort([III)Z
	.limit stack 5
	.limit locals 5
	;IF STATEMENT
	;If Condition
	iload 2
	iload 3
	;LESS THAN EXPRESSION
	if_icmplt Label_566
	;Less than results in false
	iconst_0
	goto Label_565
Label_566: ; LabelType: Less than results in true
	iconst_1
Label_565: ; LabelType: Less than exit label
	ifeq Label_563
	;Then statement body
	aload 0
	aload 1
	iload 2
	iload 3
	invokevirtual QuickSort/partition([III)I

	istore 4

	aload 0
	aload 1
	iload 2
	iload 4
	iconst_1
	isub
	invokevirtual QuickSort/quicksort([III)Z

	pop
	aload 0
	aload 1
	iload 4
	iconst_1
	iadd
	iload 3
	invokevirtual QuickSort/quicksort([III)Z

	pop
	goto Label_564
Label_563: ; LabelType: Else Statement Body:
Label_564: ; LabelType: Exit If Statement

	iconst_1
	ireturn
.end method


