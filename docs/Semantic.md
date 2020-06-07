# Semantic Checks
* Warning on duplicate class import warning
* Warning on duplicate method import warning
* Duplicate functions (Same argument count, type and order)
* Duplicate variables (locals or members with same name)
* Array access is used on non-array expression
* Expression in: ``arrayVar[Expression]``, is not numeric
* Expression in: ``new int[Expression]``, is not numeric
* Function caller is not a valid class or object
* Method does not belong to the caller class
* Identifier cannot be resolved
* Left hand side of ``length`` expression is not an array type
* Identifier used in new object identifier is not a valid class
* Negated expression is not boolean
* Both sides of ``&&`` operations are not boolean
* Both sides of ``+``, ``-``, ``*``, ``/``, ``<`` operations are not numeric
* Used variable is undefined
* Error on variables that may not be defined at the time of the statement, using dataflow analysis
