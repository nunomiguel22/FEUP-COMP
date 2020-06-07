; Class Declaration
.class public TicTacToe
.super java/lang/Object

; Class Members
.field public pieces [I
.field public movesmade I
.field public row1 [I
.field public row0 [I
.field public row2 [I
.field public whoseturn I

; Standard Initializer
.method public <init>()V
	aload 0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public init()Z
	.limit stack 3
	.limit locals 1
	iconst_3
	newarray int
	aload 0
	swap
	putfield TicTacToe/row0 [I

	iconst_3
	newarray int
	aload 0
	swap
	putfield TicTacToe/row1 [I

	iconst_3
	newarray int
	aload 0
	swap
	putfield TicTacToe/row2 [I

	iconst_2
	newarray int
	aload 0
	swap
	putfield TicTacToe/pieces [I

	aload 0
	getfield TicTacToe/pieces [I
	iconst_0
	iconst_1
	iastore

	aload 0
	getfield TicTacToe/pieces [I
	iconst_1
	iconst_2
	iastore

	iconst_0
	aload 0
	swap
	putfield TicTacToe/whoseturn I

	iconst_0
	aload 0
	swap
	putfield TicTacToe/movesmade I

	iconst_1
	ireturn
.end method


.method public changeturn()Z
	.limit stack 2
	.limit locals 1
	iconst_1
	aload 0
	getfield TicTacToe/whoseturn I
	isub
	aload 0
	swap
	putfield TicTacToe/whoseturn I

	iconst_1
	ireturn
.end method


.method public winner()I
	.limit stack 53
	.limit locals 4
	iconst_0
	iconst_1
	isub
	istore 1

	iconst_3
	newarray int
	astore 2

	;IF STATEMENT
	;If Condition
	;AND EXPRESSION
	aload 0
	getfield TicTacToe/row0 [I
	invokestatic BoardBase/sameArray([I)Z
	ifeq Label_378
	iconst_0
	aload 0
	getfield TicTacToe/row0 [I
	iconst_0
	iaload
	;LESS THAN EXPRESSION
	if_icmplt Label_380
	;Less than results in false
	iconst_0
	goto Label_379
Label_380: ; LabelType: Less than results in true
	iconst_1
Label_379: ; LabelType: Less than exit label
	ifeq Label_378
	; And results in true
	iconst_1
	goto Label_377
Label_378:
	iconst_0
Label_377:
	ifeq Label_375
	;Then statement body
	aload 0
	getfield TicTacToe/row0 [I
	iconst_0
	iaload
	istore 1

	goto Label_376
Label_375: ; LabelType: Else Statement Body:
	;IF STATEMENT
	;If Condition
	;AND EXPRESSION
	aload 0
	getfield TicTacToe/row1 [I
	invokestatic BoardBase/sameArray([I)Z
	ifeq Label_384
	iconst_0
	aload 0
	getfield TicTacToe/row1 [I
	iconst_0
	iaload
	;LESS THAN EXPRESSION
	if_icmplt Label_386
	;Less than results in false
	iconst_0
	goto Label_385
Label_386: ; LabelType: Less than results in true
	iconst_1
Label_385: ; LabelType: Less than exit label
	ifeq Label_384
	; And results in true
	iconst_1
	goto Label_383
Label_384:
	iconst_0
Label_383:
	ifeq Label_381
	;Then statement body
	aload 0
	getfield TicTacToe/row1 [I
	iconst_0
	iaload
	istore 1

	goto Label_382
Label_381: ; LabelType: Else Statement Body:
	;IF STATEMENT
	;If Condition
	;AND EXPRESSION
	aload 0
	getfield TicTacToe/row2 [I
	invokestatic BoardBase/sameArray([I)Z
	ifeq Label_390
	iconst_0
	aload 0
	getfield TicTacToe/row2 [I
	iconst_0
	iaload
	;LESS THAN EXPRESSION
	if_icmplt Label_392
	;Less than results in false
	iconst_0
	goto Label_391
Label_392: ; LabelType: Less than results in true
	iconst_1
Label_391: ; LabelType: Less than exit label
	ifeq Label_390
	; And results in true
	iconst_1
	goto Label_389
Label_390:
	iconst_0
Label_389:
	ifeq Label_387
	;Then statement body
	aload 0
	getfield TicTacToe/row2 [I
	iconst_0
	iaload
	istore 1

	goto Label_388
Label_387: ; LabelType: Else Statement Body:
	iconst_0
	istore 3

Label_393: ; LabelType: WHILE STATEMENT
	;While Condition:
	;AND EXPRESSION
	iload 1
	iconst_1
	;LESS THAN EXPRESSION
	if_icmplt Label_398
	;Less than results in false
	iconst_0
	goto Label_397
Label_398: ; LabelType: Less than results in true
	iconst_1
Label_397: ; LabelType: Less than exit label
	ifeq Label_396
	iload 3
	iconst_3
	;LESS THAN EXPRESSION
	if_icmplt Label_400
	;Less than results in false
	iconst_0
	goto Label_399
Label_400: ; LabelType: Less than results in true
	iconst_1
Label_399: ; LabelType: Less than exit label
	ifeq Label_396
	; And results in true
	iconst_1
	goto Label_395
Label_396:
	iconst_0
Label_395:
	ifeq Label_394
	;While Body:
	aload 2
	iconst_0
	aload 0
	getfield TicTacToe/row0 [I
	iload 3
	iaload
	iastore

	aload 2
	iconst_1
	aload 0
	getfield TicTacToe/row1 [I
	iload 3
	iaload
	iastore

	aload 2
	iconst_2
	aload 0
	getfield TicTacToe/row2 [I
	iload 3
	iaload
	iastore

	;IF STATEMENT
	;If Condition
	;AND EXPRESSION
	aload 2
	invokestatic BoardBase/sameArray([I)Z
	ifeq Label_404
	iconst_0
	aload 2
	iconst_0
	iaload
	;LESS THAN EXPRESSION
	if_icmplt Label_406
	;Less than results in false
	iconst_0
	goto Label_405
Label_406: ; LabelType: Less than results in true
	iconst_1
Label_405: ; LabelType: Less than exit label
	ifeq Label_404
	; And results in true
	iconst_1
	goto Label_403
Label_404:
	iconst_0
Label_403:
	ifeq Label_401
	;Then statement body
	aload 2
	iconst_0
	iaload
	istore 1

	goto Label_402
Label_401: ; LabelType: Else Statement Body:
Label_402: ; LabelType: Exit If Statement

	iload 3
	iconst_1
	iadd
	istore 3

	goto Label_393
Label_394: ; LabelType: Exit While Statement

	;IF STATEMENT
	;If Condition
	iload 1
	iconst_1
	;LESS THAN EXPRESSION
	if_icmplt Label_410
	;Less than results in false
	iconst_0
	goto Label_409
Label_410: ; LabelType: Less than results in true
	iconst_1
Label_409: ; LabelType: Less than exit label
	ifeq Label_407
	;Then statement body
	aload 2
	iconst_0
	aload 0
	getfield TicTacToe/row0 [I
	iconst_0
	iaload
	iastore

	aload 2
	iconst_1
	aload 0
	getfield TicTacToe/row1 [I
	iconst_1
	iaload
	iastore

	aload 2
	iconst_2
	aload 0
	getfield TicTacToe/row2 [I
	iconst_2
	iaload
	iastore

	;IF STATEMENT
	;If Condition
	;AND EXPRESSION
	aload 2
	invokestatic BoardBase/sameArray([I)Z
	ifeq Label_414
	iconst_0
	aload 2
	iconst_0
	iaload
	;LESS THAN EXPRESSION
	if_icmplt Label_416
	;Less than results in false
	iconst_0
	goto Label_415
Label_416: ; LabelType: Less than results in true
	iconst_1
Label_415: ; LabelType: Less than exit label
	ifeq Label_414
	; And results in true
	iconst_1
	goto Label_413
Label_414:
	iconst_0
Label_413:
	ifeq Label_411
	;Then statement body
	aload 2
	iconst_0
	iaload
	istore 1

	goto Label_412
Label_411: ; LabelType: Else Statement Body:
	aload 2
	iconst_0
	aload 0
	getfield TicTacToe/row0 [I
	iconst_2
	iaload
	iastore

	aload 2
	iconst_1
	aload 0
	getfield TicTacToe/row1 [I
	iconst_1
	iaload
	iastore

	aload 2
	iconst_2
	aload 0
	getfield TicTacToe/row2 [I
	iconst_0
	iaload
	iastore

	;IF STATEMENT
	;If Condition
	;AND EXPRESSION
	aload 2
	invokestatic BoardBase/sameArray([I)Z
	ifeq Label_420
	iconst_0
	aload 2
	iconst_0
	iaload
	;LESS THAN EXPRESSION
	if_icmplt Label_422
	;Less than results in false
	iconst_0
	goto Label_421
Label_422: ; LabelType: Less than results in true
	iconst_1
Label_421: ; LabelType: Less than exit label
	ifeq Label_420
	; And results in true
	iconst_1
	goto Label_419
Label_420:
	iconst_0
Label_419:
	ifeq Label_417
	;Then statement body
	aload 2
	iconst_0
	iaload
	istore 1

	goto Label_418
Label_417: ; LabelType: Else Statement Body:
Label_418: ; LabelType: Exit If Statement

Label_412: ; LabelType: Exit If Statement

	goto Label_408
Label_407: ; LabelType: Else Statement Body:
Label_408: ; LabelType: Exit If Statement

Label_388: ; LabelType: Exit If Statement

Label_382: ; LabelType: Exit If Statement

Label_376: ; LabelType: Exit If Statement

	;IF STATEMENT
	;If Condition
	;AND EXPRESSION
	;AND EXPRESSION
	iload 1
	iconst_1
	;LESS THAN EXPRESSION
	if_icmplt Label_430
	;Less than results in false
	iconst_0
	goto Label_429
Label_430: ; LabelType: Less than results in true
	iconst_1
Label_429: ; LabelType: Less than exit label
	ifeq Label_428
	aload 0
	getfield TicTacToe/movesmade I
	bipush 9
	;LESS THAN EXPRESSION
	if_icmplt Label_434
	;Less than results in false
	iconst_0
	goto Label_433
Label_434: ; LabelType: Less than results in true
	iconst_1
Label_433: ; LabelType: Less than exit label

	;NEGATION EXPRESSION
	ifeq Label_431
	; Negation results in false
	iconst_0
	goto Label_432
Label_431: ; LabelType: Negation results in true
	iconst_1
Label_432: ; LabelType: Negation Exit Label

	ifeq Label_428
	; And results in true
	iconst_1
	goto Label_427
Label_428:
	iconst_0
Label_427:
	ifeq Label_426
	bipush 9
	aload 0
	getfield TicTacToe/movesmade I
	;LESS THAN EXPRESSION
	if_icmplt Label_438
	;Less than results in false
	iconst_0
	goto Label_437
Label_438: ; LabelType: Less than results in true
	iconst_1
Label_437: ; LabelType: Less than exit label

	;NEGATION EXPRESSION
	ifeq Label_435
	; Negation results in false
	iconst_0
	goto Label_436
Label_435: ; LabelType: Negation results in true
	iconst_1
Label_436: ; LabelType: Negation Exit Label

	ifeq Label_426
	; And results in true
	iconst_1
	goto Label_425
Label_426:
	iconst_0
Label_425:
	ifeq Label_423
	;Then statement body
	iconst_0
	istore 1

	goto Label_424
Label_423: ; LabelType: Else Statement Body:
Label_424: ; LabelType: Exit If Statement

	iload 1
	ireturn
.end method


.method public Move(II)Z
	.limit stack 6
	.limit locals 4
	;IF STATEMENT
	;If Condition
	;AND EXPRESSION
	iload 1
	iconst_0
	;LESS THAN EXPRESSION
	if_icmplt Label_446
	;Less than results in false
	iconst_0
	goto Label_445
Label_446: ; LabelType: Less than results in true
	iconst_1
Label_445: ; LabelType: Less than exit label

	;NEGATION EXPRESSION
	ifeq Label_443
	; Negation results in false
	iconst_0
	goto Label_444
Label_443: ; LabelType: Negation results in true
	iconst_1
Label_444: ; LabelType: Negation Exit Label

	ifeq Label_442
	iconst_0
	iload 1
	;LESS THAN EXPRESSION
	if_icmplt Label_450
	;Less than results in false
	iconst_0
	goto Label_449
Label_450: ; LabelType: Less than results in true
	iconst_1
Label_449: ; LabelType: Less than exit label

	;NEGATION EXPRESSION
	ifeq Label_447
	; Negation results in false
	iconst_0
	goto Label_448
Label_447: ; LabelType: Negation results in true
	iconst_1
Label_448: ; LabelType: Negation Exit Label

	ifeq Label_442
	; And results in true
	iconst_1
	goto Label_441
Label_442:
	iconst_0
Label_441:
	ifeq Label_439
	;Then statement body
	aload 0
	aload 0
	getfield TicTacToe/row0 [I
	iload 2
	invokevirtual TicTacToe/MoveRow([II)Z

	istore 3

	goto Label_440
Label_439: ; LabelType: Else Statement Body:
	;IF STATEMENT
	;If Condition
	;AND EXPRESSION
	iload 1
	iconst_1
	;LESS THAN EXPRESSION
	if_icmplt Label_458
	;Less than results in false
	iconst_0
	goto Label_457
Label_458: ; LabelType: Less than results in true
	iconst_1
Label_457: ; LabelType: Less than exit label

	;NEGATION EXPRESSION
	ifeq Label_455
	; Negation results in false
	iconst_0
	goto Label_456
Label_455: ; LabelType: Negation results in true
	iconst_1
Label_456: ; LabelType: Negation Exit Label

	ifeq Label_454
	iconst_1
	iload 1
	;LESS THAN EXPRESSION
	if_icmplt Label_462
	;Less than results in false
	iconst_0
	goto Label_461
Label_462: ; LabelType: Less than results in true
	iconst_1
Label_461: ; LabelType: Less than exit label

	;NEGATION EXPRESSION
	ifeq Label_459
	; Negation results in false
	iconst_0
	goto Label_460
Label_459: ; LabelType: Negation results in true
	iconst_1
Label_460: ; LabelType: Negation Exit Label

	ifeq Label_454
	; And results in true
	iconst_1
	goto Label_453
Label_454:
	iconst_0
Label_453:
	ifeq Label_451
	;Then statement body
	aload 0
	aload 0
	getfield TicTacToe/row1 [I
	iload 2
	invokevirtual TicTacToe/MoveRow([II)Z

	istore 3

	goto Label_452
Label_451: ; LabelType: Else Statement Body:
	;IF STATEMENT
	;If Condition
	;AND EXPRESSION
	iload 1
	iconst_2
	;LESS THAN EXPRESSION
	if_icmplt Label_470
	;Less than results in false
	iconst_0
	goto Label_469
Label_470: ; LabelType: Less than results in true
	iconst_1
Label_469: ; LabelType: Less than exit label

	;NEGATION EXPRESSION
	ifeq Label_467
	; Negation results in false
	iconst_0
	goto Label_468
Label_467: ; LabelType: Negation results in true
	iconst_1
Label_468: ; LabelType: Negation Exit Label

	ifeq Label_466
	iconst_2
	iload 1
	;LESS THAN EXPRESSION
	if_icmplt Label_474
	;Less than results in false
	iconst_0
	goto Label_473
Label_474: ; LabelType: Less than results in true
	iconst_1
Label_473: ; LabelType: Less than exit label

	;NEGATION EXPRESSION
	ifeq Label_471
	; Negation results in false
	iconst_0
	goto Label_472
Label_471: ; LabelType: Negation results in true
	iconst_1
Label_472: ; LabelType: Negation Exit Label

	ifeq Label_466
	; And results in true
	iconst_1
	goto Label_465
Label_466:
	iconst_0
Label_465:
	ifeq Label_463
	;Then statement body
	aload 0
	aload 0
	getfield TicTacToe/row2 [I
	iload 2
	invokevirtual TicTacToe/MoveRow([II)Z

	istore 3

	goto Label_464
Label_463: ; LabelType: Else Statement Body:
	iconst_0
	istore 3

Label_464: ; LabelType: Exit If Statement

Label_452: ; LabelType: Exit If Statement

Label_440: ; LabelType: Exit If Statement

	iload 3
	ireturn
.end method


.method public getRow0()[I
	.limit stack 1
	.limit locals 1
	aload 0
	getfield TicTacToe/row0 [I
	areturn
.end method


.method public getRow1()[I
	.limit stack 1
	.limit locals 1
	aload 0
	getfield TicTacToe/row1 [I
	areturn
.end method


.method public getRow2()[I
	.limit stack 1
	.limit locals 1
	aload 0
	getfield TicTacToe/row2 [I
	areturn
.end method


.method public static main([Ljava/lang/String;)V
	.limit stack 12
	.limit locals 6
	new TicTacToe
	dup
	invokespecial TicTacToe/<init>()V
	astore 2

	aload 2
	invokevirtual TicTacToe/init()Z
	pop
Label_475: ; LabelType: WHILE STATEMENT
	;While Condition:
	;AND EXPRESSION
	aload 2
	invokevirtual TicTacToe/winner()I
	iconst_0
	iconst_1
	isub
	;LESS THAN EXPRESSION
	if_icmplt Label_482
	;Less than results in false
	iconst_0
	goto Label_481
Label_482: ; LabelType: Less than results in true
	iconst_1
Label_481: ; LabelType: Less than exit label

	;NEGATION EXPRESSION
	ifeq Label_479
	; Negation results in false
	iconst_0
	goto Label_480
Label_479: ; LabelType: Negation results in true
	iconst_1
Label_480: ; LabelType: Negation Exit Label

	ifeq Label_478
	iconst_0
	iconst_1
	isub
	aload 2
	invokevirtual TicTacToe/winner()I
	;LESS THAN EXPRESSION
	if_icmplt Label_486
	;Less than results in false
	iconst_0
	goto Label_485
Label_486: ; LabelType: Less than results in true
	iconst_1
Label_485: ; LabelType: Less than exit label

	;NEGATION EXPRESSION
	ifeq Label_483
	; Negation results in false
	iconst_0
	goto Label_484
Label_483: ; LabelType: Negation results in true
	iconst_1
Label_484: ; LabelType: Negation Exit Label

	ifeq Label_478
	; And results in true
	iconst_1
	goto Label_477
Label_478:
	iconst_0
Label_477:
	ifeq Label_476
	;While Body:
	iconst_0
	istore 4

Label_487: ; LabelType: WHILE STATEMENT
	;While Condition:
	iload 4
	;NEGATION EXPRESSION
	ifeq Label_489
	; Negation results in false
	iconst_0
	goto Label_490
Label_489: ; LabelType: Negation results in true
	iconst_1
Label_490: ; LabelType: Negation Exit Label

	ifeq Label_488
	;While Body:
	aload 2
	invokevirtual TicTacToe/getRow0()[I
	aload 2
	invokevirtual TicTacToe/getRow1()[I
	aload 2
	invokevirtual TicTacToe/getRow2()[I
	invokestatic BoardBase/printBoard([I[I[I)V

	aload 2
	invokevirtual TicTacToe/getCurrentPlayer()I
	istore 5

	iload 5
	invokestatic BoardBase/playerTurn(I)[I
	astore 1

	;IF STATEMENT
	;If Condition
	aload 2
	aload 1
	iconst_0
	iaload
	aload 1
	iconst_1
	iaload
	invokevirtual TicTacToe/inbounds(II)Z
	;NEGATION EXPRESSION
	ifeq Label_493
	; Negation results in false
	iconst_0
	goto Label_494
Label_493: ; LabelType: Negation results in true
	iconst_1
Label_494: ; LabelType: Negation Exit Label

	ifeq Label_491
	;Then statement body
	invokestatic BoardBase/wrongMove()V

	goto Label_492
Label_491: ; LabelType: Else Statement Body:
	;IF STATEMENT
	;If Condition
	aload 2
	aload 1
	iconst_0
	iaload
	aload 1
	iconst_1
	iaload
	invokevirtual TicTacToe/Move(II)Z
	;NEGATION EXPRESSION
	ifeq Label_497
	; Negation results in false
	iconst_0
	goto Label_498
Label_497: ; LabelType: Negation results in true
	iconst_1
Label_498: ; LabelType: Negation Exit Label

	ifeq Label_495
	;Then statement body
	invokestatic BoardBase/placeTaken()V

	goto Label_496
Label_495: ; LabelType: Else Statement Body:
	iconst_1
	istore 4

Label_496: ; LabelType: Exit If Statement

Label_492: ; LabelType: Exit If Statement

	goto Label_487
Label_488: ; LabelType: Exit While Statement

	aload 2
	invokevirtual TicTacToe/changeturn()Z
	pop
	goto Label_475
Label_476: ; LabelType: Exit While Statement

	aload 2
	invokevirtual TicTacToe/getRow0()[I
	aload 2
	invokevirtual TicTacToe/getRow1()[I
	aload 2
	invokevirtual TicTacToe/getRow2()[I
	invokestatic BoardBase/printBoard([I[I[I)V

	aload 2
	invokevirtual TicTacToe/winner()I
	istore 3

	iload 3
	invokestatic BoardBase/printWinner(I)V

	return
.end method


.method public getCurrentPlayer()I
	.limit stack 2
	.limit locals 1
	aload 0
	getfield TicTacToe/whoseturn I
	iconst_1
	iadd
	ireturn
.end method


.method public inbounds(II)Z
	.limit stack 2
	.limit locals 4
	;IF STATEMENT
	;If Condition
	iload 1
	iconst_0
	;LESS THAN EXPRESSION
	if_icmplt Label_502
	;Less than results in false
	iconst_0
	goto Label_501
Label_502: ; LabelType: Less than results in true
	iconst_1
Label_501: ; LabelType: Less than exit label
	ifeq Label_499
	;Then statement body
	iconst_0
	istore 3

	goto Label_500
Label_499: ; LabelType: Else Statement Body:
	;IF STATEMENT
	;If Condition
	iload 2
	iconst_0
	;LESS THAN EXPRESSION
	if_icmplt Label_506
	;Less than results in false
	iconst_0
	goto Label_505
Label_506: ; LabelType: Less than results in true
	iconst_1
Label_505: ; LabelType: Less than exit label
	ifeq Label_503
	;Then statement body
	iconst_0
	istore 3

	goto Label_504
Label_503: ; LabelType: Else Statement Body:
	;IF STATEMENT
	;If Condition
	iconst_2
	iload 1
	;LESS THAN EXPRESSION
	if_icmplt Label_510
	;Less than results in false
	iconst_0
	goto Label_509
Label_510: ; LabelType: Less than results in true
	iconst_1
Label_509: ; LabelType: Less than exit label
	ifeq Label_507
	;Then statement body
	iconst_0
	istore 3

	goto Label_508
Label_507: ; LabelType: Else Statement Body:
	;IF STATEMENT
	;If Condition
	iconst_2
	iload 2
	;LESS THAN EXPRESSION
	if_icmplt Label_514
	;Less than results in false
	iconst_0
	goto Label_513
Label_514: ; LabelType: Less than results in true
	iconst_1
Label_513: ; LabelType: Less than exit label
	ifeq Label_511
	;Then statement body
	iconst_0
	istore 3

	goto Label_512
Label_511: ; LabelType: Else Statement Body:
	iconst_1
	istore 3

Label_512: ; LabelType: Exit If Statement

Label_508: ; LabelType: Exit If Statement

Label_504: ; LabelType: Exit If Statement

Label_500: ; LabelType: Exit If Statement

	iload 3
	ireturn
.end method


.method public MoveRow([II)Z
	.limit stack 7
	.limit locals 4
	;IF STATEMENT
	;If Condition
	iload 2
	iconst_0
	;LESS THAN EXPRESSION
	if_icmplt Label_518
	;Less than results in false
	iconst_0
	goto Label_517
Label_518: ; LabelType: Less than results in true
	iconst_1
Label_517: ; LabelType: Less than exit label
	ifeq Label_515
	;Then statement body
	iconst_0
	istore 3

	goto Label_516
Label_515: ; LabelType: Else Statement Body:
	;IF STATEMENT
	;If Condition
	iconst_2
	iload 2
	;LESS THAN EXPRESSION
	if_icmplt Label_522
	;Less than results in false
	iconst_0
	goto Label_521
Label_522: ; LabelType: Less than results in true
	iconst_1
Label_521: ; LabelType: Less than exit label
	ifeq Label_519
	;Then statement body
	iconst_0
	istore 3

	goto Label_520
Label_519: ; LabelType: Else Statement Body:
	;IF STATEMENT
	;If Condition
	iconst_0
	aload 1
	iload 2
	iaload
	;LESS THAN EXPRESSION
	if_icmplt Label_526
	;Less than results in false
	iconst_0
	goto Label_525
Label_526: ; LabelType: Less than results in true
	iconst_1
Label_525: ; LabelType: Less than exit label
	ifeq Label_523
	;Then statement body
	iconst_0
	istore 3

	goto Label_524
Label_523: ; LabelType: Else Statement Body:
	aload 1
	iload 2
	aload 0
	getfield TicTacToe/pieces [I
	aload 0
	getfield TicTacToe/whoseturn I
	iaload
	iastore

	aload 0
	getfield TicTacToe/movesmade I
	iconst_1
	iadd
	aload 0
	swap
	putfield TicTacToe/movesmade I

	iconst_1
	istore 3

Label_524: ; LabelType: Exit If Statement

Label_520: ; LabelType: Exit If Statement

Label_516: ; LabelType: Exit If Statement

	iload 3
	ireturn
.end method


