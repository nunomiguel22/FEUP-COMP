; Class Declaration
.class public Divisors
.super java/lang/Object

; Class Members
.field public divisors LIntArrayList;
.field public value I

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public init(I)I
	.limit stack 2
	.limit locals 2
	new IntArrayList
	dup
	invokespecial IntArrayList/<init>()V
	aload 0
	swap
	putfield Divisors/divisors LIntArrayList;

	aload 0
	getfield Divisors/divisors LIntArrayList;
	invokevirtual IntArrayList/init()I
	pop
	iload 1
	aload 0
	swap
	putfield Divisors/value I

	iconst_0
	ireturn
.end method


.method public ne(II)Z
	.limit stack 3
	.limit locals 3
	aload 0
	iload 1
	iload 2
	invokevirtual Divisors/eq(II)Z

	;NEGATION EXPRESSION
	ifeq Label_215
	; Negation results in false
	iconst_0
	goto Label_216
Label_215: ; LabelType: Negation results in true
	iconst_1
Label_216: ; LabelType: Negation Exit Label


	ireturn
.end method


.method public lt(II)Z
	.limit stack 2
	.limit locals 3
	iload 1
	iload 2
	;LESS THAN EXPRESSION
	if_icmplt Label_218
	;Less than results in false
	iconst_0
	goto Label_217
Label_218: ; LabelType: Less than results in true
	iconst_1
Label_217: ; LabelType: Less than exit label

	ireturn
.end method


.method public le(II)Z
	.limit stack 4
	.limit locals 3
	;AND EXPRESSION
	aload 0
	iload 1
	iload 2
	invokevirtual Divisors/lt(II)Z

	;NEGATION EXPRESSION
	ifeq Label_223
	; Negation results in false
	iconst_0
	goto Label_224
Label_223: ; LabelType: Negation results in true
	iconst_1
Label_224: ; LabelType: Negation Exit Label

	ifeq Label_222
	aload 0
	iload 1
	iload 2
	invokevirtual Divisors/eq(II)Z

	;NEGATION EXPRESSION
	ifeq Label_225
	; Negation results in false
	iconst_0
	goto Label_226
Label_225: ; LabelType: Negation results in true
	iconst_1
Label_226: ; LabelType: Negation Exit Label

	ifeq Label_222
	; And results in true
	iconst_1
	goto Label_221
Label_222:
	iconst_0
Label_221:

	;NEGATION EXPRESSION
	ifeq Label_219
	; Negation results in false
	iconst_0
	goto Label_220
Label_219: ; LabelType: Negation results in true
	iconst_1
Label_220: ; LabelType: Negation Exit Label

	ireturn
.end method


.method public static main([Ljava/lang/String;)V
	.limit stack 2
	.limit locals 3
	new Divisors
	dup
	invokespecial Divisors/<init>()V
	astore 1

	aload 1
	bipush 45
	invokevirtual Divisors/init(I)I
	pop
	aload 1
	invokevirtual Divisors/calcDivisors()I
	pop
	aload 1
	invokevirtual Divisors/getDivisors()LIntArrayList;
	astore 2

	aload 2
	invokevirtual IntArrayList/print()I
	pop
	return
.end method


.method public getDivisors()LIntArrayList;
	.limit stack 1
	.limit locals 1
	aload 0
	getfield Divisors/divisors LIntArrayList;
	areturn
.end method


.method public eq(II)Z
	.limit stack 4
	.limit locals 3
	;AND EXPRESSION
	aload 0
	iload 1
	iload 2
	invokevirtual Divisors/lt(II)Z

	;NEGATION EXPRESSION
	ifeq Label_229
	; Negation results in false
	iconst_0
	goto Label_230
Label_229: ; LabelType: Negation results in true
	iconst_1
Label_230: ; LabelType: Negation Exit Label

	ifeq Label_228
	aload 0
	iload 2
	iload 1
	invokevirtual Divisors/lt(II)Z

	;NEGATION EXPRESSION
	ifeq Label_231
	; Negation results in false
	iconst_0
	goto Label_232
Label_231: ; LabelType: Negation results in true
	iconst_1
Label_232: ; LabelType: Negation Exit Label

	ifeq Label_228
	; And results in true
	iconst_1
	goto Label_227
Label_228:
	iconst_0
Label_227:

	ireturn
.end method


.method public remainder(II)I
	.limit stack 4
	.limit locals 3
	iload 1
	iload 2
	iload 1
	iload 2
	idiv

	imul
	isub

	ireturn
.end method


.method public gt(II)Z
	.limit stack 3
	.limit locals 3
	aload 0
	iload 1
	iload 2
	invokevirtual Divisors/le(II)Z

	;NEGATION EXPRESSION
	ifeq Label_233
	; Negation results in false
	iconst_0
	goto Label_234
Label_233: ; LabelType: Negation results in true
	iconst_1
Label_234: ; LabelType: Negation Exit Label


	ireturn
.end method


.method public ge(II)Z
	.limit stack 4
	.limit locals 3
	;AND EXPRESSION
	aload 0
	iload 1
	iload 2
	invokevirtual Divisors/gt(II)Z

	;NEGATION EXPRESSION
	ifeq Label_239
	; Negation results in false
	iconst_0
	goto Label_240
Label_239: ; LabelType: Negation results in true
	iconst_1
Label_240: ; LabelType: Negation Exit Label

	ifeq Label_238
	aload 0
	iload 1
	iload 2
	invokevirtual Divisors/eq(II)Z

	;NEGATION EXPRESSION
	ifeq Label_241
	; Negation results in false
	iconst_0
	goto Label_242
Label_241: ; LabelType: Negation results in true
	iconst_1
Label_242: ; LabelType: Negation Exit Label

	ifeq Label_238
	; And results in true
	iconst_1
	goto Label_237
Label_238:
	iconst_0
Label_237:

	;NEGATION EXPRESSION
	ifeq Label_235
	; Negation results in false
	iconst_0
	goto Label_236
Label_235: ; LabelType: Negation results in true
	iconst_1
Label_236: ; LabelType: Negation Exit Label

	ireturn
.end method


.method public calcDivisors()I
	.limit stack 3
	.limit locals 3
	iconst_1
	istore 2

Label_243: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 2
	aload 0
	getfield Divisors/value I
	iconst_1
	iadd
	;LESS THAN EXPRESSION
	if_icmplt Label_246
	;Less than results in false
	iconst_0
	goto Label_245
Label_246: ; LabelType: Less than results in true
	iconst_1
Label_245: ; LabelType: Less than exit label
	ifeq Label_244
	;While Body:
	aload 0
	aload 0
	getfield Divisors/value I
	iload 2
	invokevirtual Divisors/remainder(II)I

	istore 1

	;IF STATEMENT
	;If Condition
	aload 0
	iload 1
	iconst_0
	invokevirtual Divisors/eq(II)Z

	ifeq Label_247
	;Then statement body
	aload 0
	getfield Divisors/divisors LIntArrayList;
	iload 2
	invokevirtual IntArrayList/add(I)I
	pop
	goto Label_248
Label_247: ; LabelType: Else Statement Body:
Label_248: ; LabelType: Exit If Statement

	iload 2
	iconst_1
	iadd
	istore 2

	goto Label_243
Label_244: ; LabelType: Exit While Statement

	iconst_0
	ireturn
.end method


