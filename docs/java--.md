## Java-- Grammar

---

**Program** ``=`` ImportDeclaration, ClassDeclaration, EOF

**ImportDeclaration** ``=`` ``{`` "import", ``Complete``, ";" **``};``**

**ClassDeclaration** ``=`` "class", Identifier, ``[`` "extends", Identifier ``]``, "{" ,``{`` VarDeclaration ``}``, ``{`` 
MethodDeclaration ``}`` "}" **``;``**

**VarDeclaration** ``=`` Type, Identifier, ";"``;``

**MethodDeclaration** ``=`` "public", Type, Identifier, "(" , ``[`` Type, Identifier, ``{`` ",", Type, Identifier ``}, ],`` ")"``,``   
"{"``,``   
``{`` VarDeclaration ``},``  
``{`` Statement ``},``  
"return"``,`` Expression, ";"``,``  
"}"  
``|``  
"public"``,`` "static"``,`` "void"``,`` "main"``,`` "("``,`` "String" ``,`` "[" ``,`` "]" ``,`` Identifier ``,`` ")" ``,``   
"{"``,``  
``{`` VarDeclaration ``},``   
``{`` Statement ``},``  
"}"   
**``;``**

**Type** ``=`` "int", "[" ``,`` "]"  
``|``  
"boolean"  
``|``  
"int"  
``|``  
Identifier  
**``;``**

**Statement** ``=`` "{"``, {`` Statement ``},`` "}"  
``|``  
"if"``,`` "("``,`` Expression``,`` ")"``,`` Statement``,`` "else"``,`` Statement  
``|``  
"while"``,`` "("``,`` Expression``,`` ")"``,`` Statement  
``|``  
Expression``,``";"  
``|``   
Identifier``,`` "="``,`` Expression``,`` ";"  
``|``  
Identifier``,`` "["``,`` Expression``,`` "]"``,`` "="``,`` Expression``,``";"  
**``;``**  


**Expression** ``=`` Expression``,`` ``(`` "&&" ``|`` "<" ``|`` "+" ``|`` "-" ``|`` "*" ``|`` "/" ``),`` Expression  
``|``  
Expression``,`` "["``,`` Expression``,`` "]"  
``|``  
Expression``,`` "."``,`` "length"  
``|``  
Expression``,`` "."``,`` Identifier``,`` "("``,`` ``[`` Expression ``{`` ","``,`` Expression ``}`` ``],`` ")"  
``|``  
IntegerLiteral  
``|``  
"true"  
``|``  
"false"  
``|``  
Identifier  
``|``  
"this"  
``|``  
"new"``,`` "int"``,`` "["``,`` Expression``,`` "]"  
``|``  
"new"``,`` Identifier``,`` "("``,`` ")"  
``|``  
"!"``,`` Expression  
``|``  
"("``,`` Expression``,`` ")"  
**``;``**

---
Note: ``,`` delimits tokens in sequence, ``{...}`` means zero or more occurrences of‘...’, ``[...]`` means optional ‘...’.