; Class Declaration
.class public IntArrayList
.super java/lang/Object

; Class Members
.field public arr [I
.field public size I

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public add(I)I
	.limit stack 3
	.limit locals 2
	;IF STATEMENT
	;If Condition
	aload 0
	getfield IntArrayList/arr [I
	arraylength
	aload 0
	getfield IntArrayList/size I
	isub
	iconst_2
	;LESS THAN EXPRESSION
	if_icmplt Label_186
	;Less than results in false
	iconst_0
	goto Label_185
Label_186: ; LabelType: Less than results in true
	iconst_1
Label_185: ; LabelType: Less than exit label
	ifeq Label_183
	;Then statement body
	aload 0
	invokevirtual IntArrayList/grow()I

	pop
	goto Label_184
Label_183: ; LabelType: Else Statement Body:
Label_184: ; LabelType: Exit If Statement

	aload 0
	getfield IntArrayList/arr [I
	aload 0
	getfield IntArrayList/size I
	iload 1
	iastore

	aload 0
	getfield IntArrayList/size I
	iconst_1
	iadd
	aload 0
	swap
	putfield IntArrayList/size I

	iconst_0
	ireturn
.end method


.method public init()I
	.limit stack 2
	.limit locals 1
	iconst_1
	newarray int
	aload 0
	swap
	putfield IntArrayList/arr [I

	iconst_0
	aload 0
	swap
	putfield IntArrayList/size I

	iconst_0
	ireturn
.end method


.method public print()I
	.limit stack 2
	.limit locals 2
	iconst_0
	istore 1

Label_187: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 1
	aload 0
	invokevirtual IntArrayList/size()I

	;LESS THAN EXPRESSION
	if_icmplt Label_190
	;Less than results in false
	iconst_0
	goto Label_189
Label_190: ; LabelType: Less than results in true
	iconst_1
Label_189: ; LabelType: Less than exit label
	ifeq Label_188
	;While Body:
	aload 0
	iload 1
	invokevirtual IntArrayList/get(I)I

	invokestatic io/println(I)V

	iload 1
	iconst_1
	iadd
	istore 1

	goto Label_187
Label_188: ; LabelType: Exit While Statement

	iconst_0
	ireturn
.end method


.method public grow()I
	.limit stack 5
	.limit locals 3
	iconst_0
	istore 2

	aload 0
	getfield IntArrayList/arr [I
	arraylength
	iconst_2
	imul
	newarray int
	astore 1

Label_191: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 2
	aload 0
	getfield IntArrayList/arr [I
	arraylength
	;LESS THAN EXPRESSION
	if_icmplt Label_194
	;Less than results in false
	iconst_0
	goto Label_193
Label_194: ; LabelType: Less than results in true
	iconst_1
Label_193: ; LabelType: Less than exit label
	ifeq Label_192
	;While Body:
	aload 1
	iload 2
	aload 0
	getfield IntArrayList/arr [I
	iload 2
	iaload
	iastore

	iload 2
	iconst_1
	iadd
	istore 2

	goto Label_191
Label_192: ; LabelType: Exit While Statement

	aload 1
	aload 0
	swap
	putfield IntArrayList/arr [I

	iconst_0
	ireturn
.end method


.method public size()I
	.limit stack 1
	.limit locals 1
	aload 0
	getfield IntArrayList/size I
	ireturn
.end method


.method public get(I)I
	.limit stack 3
	.limit locals 2
	aload 0
	getfield IntArrayList/arr [I
	iload 1
	iaload
	ireturn
.end method


.method public static main([Ljava/lang/String;)V
	.limit stack 2
	.limit locals 2
	new IntArrayList
	dup
	invokespecial IntArrayList/<init>()V
	astore 1

	aload 1
	invokevirtual IntArrayList/init()I
	pop
	aload 1
	iconst_3
	invokevirtual IntArrayList/add(I)I
	pop
	aload 1
	iconst_5
	invokevirtual IntArrayList/add(I)I
	pop
	aload 1
	bipush 22
	invokevirtual IntArrayList/add(I)I
	pop
	aload 1
	bipush 14
	invokevirtual IntArrayList/add(I)I
	pop
	aload 1
	bipush 122
	invokevirtual IntArrayList/add(I)I
	pop
	aload 1
	iconst_1
	invokevirtual IntArrayList/add(I)I
	pop
	aload 1
	invokevirtual IntArrayList/print()I
	pop
	return
.end method


