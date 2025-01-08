# Calculator

Exempel på rekursiv medåkning: utvärdera aritmetiska uttryck med
heltal, de fyra grundläggande räknesätten (+, -, *, /), och
parenteser.

Författare: Per Austrin

## Grammatik

```
  Expr -> Term { PM Term }
  Term -> Factor { MD Factor }
Factor -> NUM | LPAREN Expr RPAREN
```

PM är `+' eller `-', MD är `*' eller '/'

Parsar uttrycket och skriver sedan ut värdet av det.

Provkörning från terminal på fil "test1.in"

```shell
javac *.java
java Main < test1.in
```

För att bättre förstå vad algoritmen gör kan flaggan "-v" ges, då
beskriver parsern vad den håller på med, exempel:

```shell
java Main -v < test1.in
```
