
## Symbol Table
* global: inclui info de imports e a classe declarada ![alt text](check.png)
* classe-specific: inclui info de extends, fields e methods ![alt text](check.png)
* method-specific: inclui info dos arguments e local variables ![alt text](check.png)
    * sub topics:
       + tem de permitir method overload ![alt text](check.png)
       + tem de permitir consulta da tabela por parte da análise semantica (e geração de código) ![alt text](check.png)
       + tem de permitir ligar e desligar a sua impressão para fins de debug ![alt text](check.png)
## Type Verification
* verificar se operações são efetuadas com o mesmo tipo ![alt text](check.png)
* não é possível utilizar arrays diretamente para operações aritmeticas ![alt text](check.png)
* verificar se um array access é de facto feito sobre um array ![alt text](check.png)
* verificar se o indice do array access é um inteiro ![alt text](check.png)
* verificar se valor do assignee é igual ao do assigned ![alt text](check.png)
* verificar se operação booleana é efetuada só com booleanos ![alt text](check.png)
* verificar se conditional expressions (if e while) resulta num booleano ![alt text](check.png)
* verificar se variáveis são inicializadas, dando um WARNING em vez de ERRO (CP3)

			
## Function Verification
* verificar se o "target" do método existe, e se este contém o método ![alt text](check.png)
    + caso seja do tipo da classe declarada , verificar se é método do extends ![alt text](check.png)
* caso o método não seja da classe declarada, isto é importada, verificar se método foi importado ![alt text](check.png)
* verificar se o número de argumentos na invocação é igual ao número de parâmetros da declaração ![alt text](check.png)
* verificar se o tipo dos parâmetros coincide com o tipo dos argumentos ![alt text](check.png)

## Code Generation
* estrutura básica de classe ![alt text](check.png)
* estrutura básica de fields ![alt text](check.png)
* estrutura básica de métodos ![alt text](check.png)
* assignments ![alt text](check.png)
* operações aritméticas ![alt text](check.png)
* invocação de métodos ![alt text](check.png)
