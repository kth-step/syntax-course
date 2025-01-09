# Calculator

Exempel på rekursiv medåkning: utvärdera aritmetiska uttryck med
heltal, de fyra grundläggande räknesätten (`+`, `-`, `*`, `/`) och
parenteser.

Författare: Per Austrin

## Grammatik

Rekursiv medåkning görs enligt följande grammatik i matematisk notation:
```
  Expr -> Term { PM Term }
  Term -> Factor { MD Factor }
Factor -> NUM | LPAREN Expr RPAREN
```
där
- det som innesluts av `{` och `}` upprepas noll eller fler gånger
- `PM` är `+` eller `-`
- `MD` är `*` eller `/`
- `NUM` är heltal
- `LPAREN` är `(` och `RPAREN` är `)`

Språket som parsas kan därför beskrivas med följande LL-grammatik i BNF:
```
       <Expr> ::= <Term> <TermList>
   <TermList> ::= PM <Term> <TermList> | EPS
       <Term> ::= <Factor> <FactorList>
 <FactorList> ::= MD <Factor> <FactorList> | EPS
     <Factor> ::= NUM | LPAREN <Expr> RPAREN
```
där `EPS` är tomma strängen.

## Kompilering och körning

Parsar uttrycket och skriver sedan ut värdet av det.

Provkörning från terminal på fil `test1.in`:
```shell
javac *.java
java Main < test1.in
```

För att bättre förstå vad algoritmen gör kan flaggan `-v` ges, då
beskriver parsern vad den håller på med, exempel:
```shell
java Main -v < test1.in
```
