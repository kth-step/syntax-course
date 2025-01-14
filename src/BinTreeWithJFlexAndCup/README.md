# BinTreeWithJFlexAndCup

Exempel på användning av lexikanalysatorn [JFlex](https://jflex.de) och
parsergeneratorn Cup för att skapa en parser från en språkspecifikation.

Författare: Per Austrin

## Grammatik

Rekursiv medåkning görs enligt följande grammatik i BNF:
```
 <BinTree> ::= LEAF LPAR NUM RPAR | BRANCH LPAR <BinTree> COMMA <BinTree> RPAR
```
där: 
- `LEAF` är `leaf`
- `BRANCH` är `branch`
- `NUM` är heltal
- `LPAR` är `(` och `RPAR` är `)`
- `COMMA` är `,`

## Filer

- Filerna `Main.java` och `ParseTree.java` är väsentligen desamma som
i [BinTreeParser](../BinTreeParser) (mindre ändringar i `Main.java` pga att
gränssnittet för den genererade parsern är lite annorlunda än den egenkonstruerade).

- Filen `Lexer.java` från BinTreeParser är nu ersatt av filen Lexer.lex,
som innehåller lexikal specifikation till JFlex.

- Filen `Parser.java` från BinTreeParser är nu ersatt av filen `Parser.cup`,
som innehåller specifikation av grammatiken till Cup.

- Filerna `Token.java` och `SyntaxError.java` med klasser för att
representera tokens och syntaxfel från BinTreeParser behövs
inte längre, eftersom dessa saker tas hand om av JFlex och Cup.

## Kompilering och körning

Ladda hem [JFlex 1.5.1](https://sourceforge.net/projects/jflex/files/jflex/1.5.1/jflex-1.5.1.tar.gz/download)
och packa upp arkivet på lämpligt ställe. Kör sedan följande kommandon:

```shell
path/to/jflex-1.5.1/bin/jflex Lexer.lex
java -jar path/to/jflex-1.5.1/lib/java-cup-11a.jar -parser Parser Parser.cup
javac -cp path/to/jflex-1.5.1/lib/java-cup-11a.jar:. *.java
java -cp path/to/jflex-1.5.1/lib/java-cup-11a.jar:. Main < test.in
```
