; Class Declaration
.class public Life
.super java/lang/Object

; Class Members
.field public REPRODUCE_NUM I
.field public yMax I
.field public field [I
.field public OVERPOP_LIM I
.field public UNDERPOP_LIM I
.field public xMax I
.field public LOOPS_PER_MS I

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public init()Z
	.limit stack 4
	.limit locals 3
	iconst_1
	newarray int
	astore 1

	iconst_2
	aload 0
	swap
	putfield Life/UNDERPOP_LIM I

	iconst_3
	aload 0
	swap
	putfield Life/OVERPOP_LIM I

	iconst_3
	aload 0
	swap
	putfield Life/REPRODUCE_NUM I

	ldc 225000
	aload 0
	swap
	putfield Life/LOOPS_PER_MS I

	aload 0
	aload 1
	invokevirtual Life/field([I)[I

	aload 0
	swap
	putfield Life/field [I

	aload 1
	iconst_0
	iaload
	istore 2

	iload 2
	iconst_1
	isub
	aload 0
	swap
	putfield Life/xMax I

	aload 0
	getfield Life/field [I
	arraylength
	iload 2
	idiv
	iconst_1
	isub
	aload 0
	swap
	putfield Life/yMax I

	iconst_1
	ireturn
.end method


.method public trIdx(II)I
	.limit stack 3
	.limit locals 3
	iload 1
	aload 0
	getfield Life/xMax I
	iconst_1
	iadd

	iload 2
	imul
	iadd
	ireturn
.end method


.method public lt(II)Z
	.limit stack 2
	.limit locals 3
	iload 1
	iload 2
	;LESS THAN EXPRESSION
	if_icmplt Label_280
	;Less than results in false
	iconst_0
	goto Label_279
Label_280: ; LabelType: Less than results in true
	iconst_1
Label_279: ; LabelType: Less than exit label

	ireturn
.end method


.method public update()Z
	.limit stack 10
	.limit locals 6
	aload 0
	getfield Life/field [I
	arraylength
	newarray int
	astore 4

	iconst_0
	istore 3

Label_281: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 3
	aload 0
	getfield Life/field [I
	arraylength
	;LESS THAN EXPRESSION
	if_icmplt Label_284
	;Less than results in false
	iconst_0
	goto Label_283
Label_284: ; LabelType: Less than results in true
	iconst_1
Label_283: ; LabelType: Less than exit label
	ifeq Label_282
	;While Body:
	aload 0
	getfield Life/field [I
	iload 3
	iaload
	istore 1

	aload 0
	iload 3
	invokevirtual Life/getLiveNeighborN(I)I

	istore 5

	;IF STATEMENT
	;If Condition
	iload 1
	iconst_1
	;LESS THAN EXPRESSION
	if_icmplt Label_290
	;Less than results in false
	iconst_0
	goto Label_289
Label_290: ; LabelType: Less than results in true
	iconst_1
Label_289: ; LabelType: Less than exit label

	;NEGATION EXPRESSION
	ifeq Label_287
	; Negation results in false
	iconst_0
	goto Label_288
Label_287: ; LabelType: Negation results in true
	iconst_1
Label_288: ; LabelType: Negation Exit Label

	ifeq Label_285
	;Then statement body
	;AND EXPRESSION
	aload 0
	iload 5
	aload 0
	getfield Life/UNDERPOP_LIM I
	invokevirtual Life/ge(II)Z

	ifeq Label_292
	aload 0
	iload 5
	aload 0
	getfield Life/OVERPOP_LIM I
	invokevirtual Life/le(II)Z

	ifeq Label_292
	; And results in true
	iconst_1
	goto Label_291
Label_292:
	iconst_0
Label_291:
	istore 2

	;IF STATEMENT
	;If Condition
	iload 2
	;NEGATION EXPRESSION
	ifeq Label_295
	; Negation results in false
	iconst_0
	goto Label_296
Label_295: ; LabelType: Negation results in true
	iconst_1
Label_296: ; LabelType: Negation Exit Label

	ifeq Label_293
	;Then statement body
	aload 4
	iload 3
	iconst_0
	iastore

	goto Label_294
Label_293: ; LabelType: Else Statement Body:
	aload 4
	iload 3
	aload 0
	getfield Life/field [I
	iload 3
	iaload
	iastore

Label_294: ; LabelType: Exit If Statement

	goto Label_286
Label_285: ; LabelType: Else Statement Body:
	;IF STATEMENT
	;If Condition
	aload 0
	iload 5
	aload 0
	getfield Life/REPRODUCE_NUM I
	invokevirtual Life/eq(II)Z

	ifeq Label_297
	;Then statement body
	aload 4
	iload 3
	iconst_1
	iastore

	goto Label_298
Label_297: ; LabelType: Else Statement Body:
	aload 4
	iload 3
	aload 0
	getfield Life/field [I
	iload 3
	iaload
	iastore

Label_298: ; LabelType: Exit If Statement

Label_286: ; LabelType: Exit If Statement

	iload 3
	iconst_1
	iadd
	istore 3

	goto Label_281
Label_282: ; LabelType: Exit While Statement

	aload 4
	aload 0
	swap
	putfield Life/field [I

	iconst_1
	ireturn
.end method


.method public static main([Ljava/lang/String;)V
	.limit stack 2
	.limit locals 3
	new Life
	dup
	invokespecial Life/<init>()V
	astore 2

	aload 2
	invokevirtual Life/init()Z
	pop
Label_299: ; LabelType: WHILE STATEMENT
	;While Condition:
	iconst_1
	ifeq Label_300
	;While Body:
	aload 2
	invokevirtual Life/printField()Z
	pop
	aload 2
	invokevirtual Life/update()Z
	pop
	invokestatic io/read()I
	istore 1

	goto Label_299
Label_300: ; LabelType: Exit While Statement

	return
.end method


.method public getNeighborCoords(I)[I
	.limit stack 9
	.limit locals 10
	aload 0
	iload 1
	invokevirtual Life/cartIdx(I)[I

	astore 8

	aload 8
	iconst_0
	iaload
	istore 5

	aload 8
	iconst_1
	iaload
	istore 6

	;IF STATEMENT
	;If Condition
	iload 5
	aload 0
	getfield Life/xMax I
	;LESS THAN EXPRESSION
	if_icmplt Label_304
	;Less than results in false
	iconst_0
	goto Label_303
Label_304: ; LabelType: Less than results in true
	iconst_1
Label_303: ; LabelType: Less than exit label
	ifeq Label_301
	;Then statement body
	iload 5
	iconst_1
	iadd
	istore 4

	;IF STATEMENT
	;If Condition
	aload 0
	iload 5
	iconst_0
	invokevirtual Life/gt(II)Z

	ifeq Label_305
	;Then statement body
	iload 5
	iconst_1
	isub
	istore 7

	goto Label_306
Label_305: ; LabelType: Else Statement Body:
	aload 0
	getfield Life/xMax I
	istore 7

Label_306: ; LabelType: Exit If Statement

	goto Label_302
Label_301: ; LabelType: Else Statement Body:
	iconst_0
	istore 4

	iload 5
	iconst_1
	isub
	istore 7

Label_302: ; LabelType: Exit If Statement

	;IF STATEMENT
	;If Condition
	iload 6
	aload 0
	getfield Life/yMax I
	;LESS THAN EXPRESSION
	if_icmplt Label_310
	;Less than results in false
	iconst_0
	goto Label_309
Label_310: ; LabelType: Less than results in true
	iconst_1
Label_309: ; LabelType: Less than exit label
	ifeq Label_307
	;Then statement body
	iload 6
	iconst_1
	iadd
	istore 3

	;IF STATEMENT
	;If Condition
	aload 0
	iload 6
	iconst_0
	invokevirtual Life/gt(II)Z

	ifeq Label_311
	;Then statement body
	iload 6
	iconst_1
	isub
	istore 9

	goto Label_312
Label_311: ; LabelType: Else Statement Body:
	aload 0
	getfield Life/yMax I
	istore 9

Label_312: ; LabelType: Exit If Statement

	goto Label_308
Label_307: ; LabelType: Else Statement Body:
	iconst_0
	istore 3

	iload 6
	iconst_1
	isub
	istore 9

Label_308: ; LabelType: Exit If Statement

	bipush 8
	newarray int
	astore 2

	aload 2
	iconst_0
	aload 0
	iload 5
	iload 9
	invokevirtual Life/trIdx(II)I

	iastore

	aload 2
	iconst_1
	aload 0
	iload 7
	iload 9
	invokevirtual Life/trIdx(II)I

	iastore

	aload 2
	iconst_2
	aload 0
	iload 7
	iload 6
	invokevirtual Life/trIdx(II)I

	iastore

	aload 2
	iconst_3
	aload 0
	iload 7
	iload 3
	invokevirtual Life/trIdx(II)I

	iastore

	aload 2
	iconst_4
	aload 0
	iload 5
	iload 3
	invokevirtual Life/trIdx(II)I

	iastore

	aload 2
	iconst_5
	aload 0
	iload 4
	iload 3
	invokevirtual Life/trIdx(II)I

	iastore

	aload 2
	bipush 6
	aload 0
	iload 4
	iload 6
	invokevirtual Life/trIdx(II)I

	iastore

	aload 2
	bipush 7
	aload 0
	iload 4
	iload 9
	invokevirtual Life/trIdx(II)I

	iastore

	aload 2
	areturn
.end method


.method public getLiveNeighborN(I)I
	.limit stack 7
	.limit locals 5
	iconst_0
	istore 2

	aload 0
	iload 1
	invokevirtual Life/getNeighborCoords(I)[I

	astore 4

	iconst_0
	istore 3

Label_313: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 3
	aload 4
	arraylength
	;LESS THAN EXPRESSION
	if_icmplt Label_316
	;Less than results in false
	iconst_0
	goto Label_315
Label_316: ; LabelType: Less than results in true
	iconst_1
Label_315: ; LabelType: Less than exit label
	ifeq Label_314
	;While Body:
	;IF STATEMENT
	;If Condition
	aload 0
	aload 0
	getfield Life/field [I
	aload 4
	iload 3
	iaload
	iaload
	iconst_0
	invokevirtual Life/ne(II)Z

	ifeq Label_317
	;Then statement body
	iload 2
	iconst_1
	iadd
	istore 2

	goto Label_318
Label_317: ; LabelType: Else Statement Body:
Label_318: ; LabelType: Exit If Statement

	iload 3
	iconst_1
	iadd
	istore 3

	goto Label_313
Label_314: ; LabelType: Exit While Statement

	iload 2
	ireturn
.end method


.method public printField()Z
	.limit stack 4
	.limit locals 3
	iconst_0
	istore 1

	iconst_0
	istore 2

Label_319: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 1
	aload 0
	getfield Life/field [I
	arraylength
	;LESS THAN EXPRESSION
	if_icmplt Label_322
	;Less than results in false
	iconst_0
	goto Label_321
Label_322: ; LabelType: Less than results in true
	iconst_1
Label_321: ; LabelType: Less than exit label
	ifeq Label_320
	;While Body:
	;IF STATEMENT
	;If Condition
	aload 0
	iload 2
	aload 0
	getfield Life/xMax I
	invokevirtual Life/gt(II)Z

	ifeq Label_323
	;Then statement body
	invokestatic io/println()V

	iconst_0
	istore 2

	goto Label_324
Label_323: ; LabelType: Else Statement Body:
Label_324: ; LabelType: Exit If Statement

	aload 0
	getfield Life/field [I
	iload 1
	iaload
	invokestatic io/print(I)V

	iload 1
	iconst_1
	iadd
	istore 1

	iload 2
	iconst_1
	iadd
	istore 2

	goto Label_319
Label_320: ; LabelType: Exit While Statement

	invokestatic io/println()V

	invokestatic io/println()V

	iconst_1
	ireturn
.end method


.method public eq(II)Z
	.limit stack 4
	.limit locals 3
	;AND EXPRESSION
	aload 0
	iload 1
	iload 2
	invokevirtual Life/lt(II)Z

	;NEGATION EXPRESSION
	ifeq Label_327
	; Negation results in false
	iconst_0
	goto Label_328
Label_327: ; LabelType: Negation results in true
	iconst_1
Label_328: ; LabelType: Negation Exit Label

	ifeq Label_326
	aload 0
	iload 2
	iload 1
	invokevirtual Life/lt(II)Z

	;NEGATION EXPRESSION
	ifeq Label_329
	; Negation results in false
	iconst_0
	goto Label_330
Label_329: ; LabelType: Negation results in true
	iconst_1
Label_330: ; LabelType: Negation Exit Label

	ifeq Label_326
	; And results in true
	iconst_1
	goto Label_325
Label_326:
	iconst_0
Label_325:

	ireturn
.end method


.method public gt(II)Z
	.limit stack 3
	.limit locals 3
	aload 0
	iload 1
	iload 2
	invokevirtual Life/le(II)Z

	;NEGATION EXPRESSION
	ifeq Label_331
	; Negation results in false
	iconst_0
	goto Label_332
Label_331: ; LabelType: Negation results in true
	iconst_1
Label_332: ; LabelType: Negation Exit Label


	ireturn
.end method


.method public field([I)[I
	.limit stack 3
	.limit locals 3
	bipush 100
	newarray int
	astore 2

	aload 1
	iconst_0
	bipush 10
	iastore

	aload 2
	iconst_0
	iconst_0
	iastore

	aload 2
	iconst_1
	iconst_0
	iastore

	aload 2
	iconst_2
	iconst_1
	iastore

	aload 2
	iconst_3
	iconst_0
	iastore

	aload 2
	iconst_4
	iconst_0
	iastore

	aload 2
	iconst_5
	iconst_0
	iastore

	aload 2
	bipush 6
	iconst_0
	iastore

	aload 2
	bipush 7
	iconst_0
	iastore

	aload 2
	bipush 8
	iconst_0
	iastore

	aload 2
	bipush 9
	iconst_0
	iastore

	aload 2
	bipush 10
	iconst_1
	iastore

	aload 2
	bipush 11
	iconst_0
	iastore

	aload 2
	bipush 12
	iconst_1
	iastore

	aload 2
	bipush 13
	iconst_0
	iastore

	aload 2
	bipush 14
	iconst_0
	iastore

	aload 2
	bipush 15
	iconst_0
	iastore

	aload 2
	bipush 16
	iconst_0
	iastore

	aload 2
	bipush 17
	iconst_0
	iastore

	aload 2
	bipush 18
	iconst_0
	iastore

	aload 2
	bipush 19
	iconst_0
	iastore

	aload 2
	bipush 20
	iconst_0
	iastore

	aload 2
	bipush 21
	iconst_1
	iastore

	aload 2
	bipush 22
	iconst_1
	iastore

	aload 2
	bipush 23
	iconst_0
	iastore

	aload 2
	bipush 24
	iconst_0
	iastore

	aload 2
	bipush 25
	iconst_0
	iastore

	aload 2
	bipush 26
	iconst_0
	iastore

	aload 2
	bipush 27
	iconst_0
	iastore

	aload 2
	bipush 28
	iconst_0
	iastore

	aload 2
	bipush 29
	iconst_0
	iastore

	aload 2
	bipush 30
	iconst_0
	iastore

	aload 2
	bipush 31
	iconst_0
	iastore

	aload 2
	bipush 32
	iconst_0
	iastore

	aload 2
	bipush 33
	iconst_0
	iastore

	aload 2
	bipush 34
	iconst_0
	iastore

	aload 2
	bipush 35
	iconst_0
	iastore

	aload 2
	bipush 36
	iconst_0
	iastore

	aload 2
	bipush 37
	iconst_0
	iastore

	aload 2
	bipush 38
	iconst_0
	iastore

	aload 2
	bipush 39
	iconst_0
	iastore

	aload 2
	bipush 40
	iconst_0
	iastore

	aload 2
	bipush 41
	iconst_0
	iastore

	aload 2
	bipush 42
	iconst_0
	iastore

	aload 2
	bipush 43
	iconst_0
	iastore

	aload 2
	bipush 44
	iconst_0
	iastore

	aload 2
	bipush 45
	iconst_0
	iastore

	aload 2
	bipush 46
	iconst_0
	iastore

	aload 2
	bipush 47
	iconst_0
	iastore

	aload 2
	bipush 48
	iconst_0
	iastore

	aload 2
	bipush 49
	iconst_0
	iastore

	aload 2
	bipush 50
	iconst_0
	iastore

	aload 2
	bipush 51
	iconst_0
	iastore

	aload 2
	bipush 52
	iconst_0
	iastore

	aload 2
	bipush 53
	iconst_0
	iastore

	aload 2
	bipush 54
	iconst_0
	iastore

	aload 2
	bipush 55
	iconst_0
	iastore

	aload 2
	bipush 56
	iconst_0
	iastore

	aload 2
	bipush 57
	iconst_0
	iastore

	aload 2
	bipush 58
	iconst_0
	iastore

	aload 2
	bipush 59
	iconst_0
	iastore

	aload 2
	bipush 60
	iconst_0
	iastore

	aload 2
	bipush 61
	iconst_0
	iastore

	aload 2
	bipush 62
	iconst_0
	iastore

	aload 2
	bipush 63
	iconst_0
	iastore

	aload 2
	bipush 64
	iconst_0
	iastore

	aload 2
	bipush 65
	iconst_0
	iastore

	aload 2
	bipush 66
	iconst_0
	iastore

	aload 2
	bipush 67
	iconst_0
	iastore

	aload 2
	bipush 68
	iconst_0
	iastore

	aload 2
	bipush 69
	iconst_0
	iastore

	aload 2
	bipush 70
	iconst_0
	iastore

	aload 2
	bipush 71
	iconst_0
	iastore

	aload 2
	bipush 72
	iconst_0
	iastore

	aload 2
	bipush 73
	iconst_0
	iastore

	aload 2
	bipush 74
	iconst_0
	iastore

	aload 2
	bipush 75
	iconst_0
	iastore

	aload 2
	bipush 76
	iconst_0
	iastore

	aload 2
	bipush 77
	iconst_0
	iastore

	aload 2
	bipush 78
	iconst_0
	iastore

	aload 2
	bipush 79
	iconst_0
	iastore

	aload 2
	bipush 80
	iconst_0
	iastore

	aload 2
	bipush 81
	iconst_0
	iastore

	aload 2
	bipush 82
	iconst_0
	iastore

	aload 2
	bipush 83
	iconst_0
	iastore

	aload 2
	bipush 84
	iconst_0
	iastore

	aload 2
	bipush 85
	iconst_0
	iastore

	aload 2
	bipush 86
	iconst_0
	iastore

	aload 2
	bipush 87
	iconst_0
	iastore

	aload 2
	bipush 88
	iconst_0
	iastore

	aload 2
	bipush 89
	iconst_0
	iastore

	aload 2
	bipush 90
	iconst_0
	iastore

	aload 2
	bipush 91
	iconst_0
	iastore

	aload 2
	bipush 92
	iconst_0
	iastore

	aload 2
	bipush 93
	iconst_0
	iastore

	aload 2
	bipush 94
	iconst_0
	iastore

	aload 2
	bipush 95
	iconst_0
	iastore

	aload 2
	bipush 96
	iconst_0
	iastore

	aload 2
	bipush 97
	iconst_0
	iastore

	aload 2
	bipush 98
	iconst_0
	iastore

	aload 2
	bipush 99
	iconst_0
	iastore

	aload 2
	areturn
.end method


.method public busyWait(I)Z
	.limit stack 2
	.limit locals 4
	iload 1
	aload 0
	getfield Life/LOOPS_PER_MS I
	imul
	istore 3

	iconst_0
	istore 2

Label_333: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 2
	iload 3
	;LESS THAN EXPRESSION
	if_icmplt Label_336
	;Less than results in false
	iconst_0
	goto Label_335
Label_336: ; LabelType: Less than results in true
	iconst_1
Label_335: ; LabelType: Less than exit label
	ifeq Label_334
	;While Body:
	iload 2
	iconst_1
	iadd
	istore 2

	goto Label_333
Label_334: ; LabelType: Exit While Statement

	iconst_1
	ireturn
.end method


.method public cartIdx(I)[I
	.limit stack 3
	.limit locals 6
	aload 0
	getfield Life/xMax I
	iconst_1
	iadd
	istore 5

	iload 1
	iload 5
	idiv
	istore 4

	iload 1
	iload 4
	iload 5
	imul
	isub
	istore 3

	iconst_2
	newarray int
	astore 2

	aload 2
	iconst_0
	iload 3
	iastore

	aload 2
	iconst_1
	iload 4
	iastore

	aload 2
	areturn
.end method


.method public ne(II)Z
	.limit stack 3
	.limit locals 3
	aload 0
	iload 1
	iload 2
	invokevirtual Life/eq(II)Z

	;NEGATION EXPRESSION
	ifeq Label_337
	; Negation results in false
	iconst_0
	goto Label_338
Label_337: ; LabelType: Negation results in true
	iconst_1
Label_338: ; LabelType: Negation Exit Label


	ireturn
.end method


.method public le(II)Z
	.limit stack 4
	.limit locals 3
	;AND EXPRESSION
	aload 0
	iload 1
	iload 2
	invokevirtual Life/lt(II)Z

	;NEGATION EXPRESSION
	ifeq Label_343
	; Negation results in false
	iconst_0
	goto Label_344
Label_343: ; LabelType: Negation results in true
	iconst_1
Label_344: ; LabelType: Negation Exit Label

	ifeq Label_342
	aload 0
	iload 1
	iload 2
	invokevirtual Life/eq(II)Z

	;NEGATION EXPRESSION
	ifeq Label_345
	; Negation results in false
	iconst_0
	goto Label_346
Label_345: ; LabelType: Negation results in true
	iconst_1
Label_346: ; LabelType: Negation Exit Label

	ifeq Label_342
	; And results in true
	iconst_1
	goto Label_341
Label_342:
	iconst_0
Label_341:

	;NEGATION EXPRESSION
	ifeq Label_339
	; Negation results in false
	iconst_0
	goto Label_340
Label_339: ; LabelType: Negation results in true
	iconst_1
Label_340: ; LabelType: Negation Exit Label

	ireturn
.end method


.method public ge(II)Z
	.limit stack 4
	.limit locals 3
	;AND EXPRESSION
	aload 0
	iload 1
	iload 2
	invokevirtual Life/gt(II)Z

	;NEGATION EXPRESSION
	ifeq Label_351
	; Negation results in false
	iconst_0
	goto Label_352
Label_351: ; LabelType: Negation results in true
	iconst_1
Label_352: ; LabelType: Negation Exit Label

	ifeq Label_350
	aload 0
	iload 1
	iload 2
	invokevirtual Life/eq(II)Z

	;NEGATION EXPRESSION
	ifeq Label_353
	; Negation results in false
	iconst_0
	goto Label_354
Label_353: ; LabelType: Negation results in true
	iconst_1
Label_354: ; LabelType: Negation Exit Label

	ifeq Label_350
	; And results in true
	iconst_1
	goto Label_349
Label_350:
	iconst_0
Label_349:

	;NEGATION EXPRESSION
	ifeq Label_347
	; Negation results in false
	iconst_0
	goto Label_348
Label_347: ; LabelType: Negation results in true
	iconst_1
Label_348: ; LabelType: Negation Exit Label

	ireturn
.end method


