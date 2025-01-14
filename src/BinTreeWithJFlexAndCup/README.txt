Syntax-Föreläsning 4: Exempel på användning av JFlex och Cup för att
generera en parser från specifikation.

Grammatiken är samma grammatik för binära träd som vi konstruerade en
rekursiv medåknings-parser för förra veckan:

BinTree --> leaf ( Number ) | branch ( BinTree , BinTree )

Filerna Main.java och ParseTree.java är väsentligen desamma som förra
veckan (mindre ändringar i Main pga att gränssnittet för den
genererade parsern är lite annorlunda än den egenkonstruerade).

Filen Lexer.java från förra veckan är nu ersatt av filen Lexer.lex,
som innehåller lexikal specifikation till JFlex.

Filen Parser.java från förra veckan är nu ersatt av filen Parser.cup,
som innehåller specifikation av grammatiken till Cup.

Filerna Token.java och SyntaxError.java med klasser för att
representera Tokens och Syntax-fel från förra veckans exempel behövs
inte längre, eftersom dessa saker tas hand om av JFlex och Cup.

https://sourceforge.net/projects/jflex/files/jflex/1.5.1/jflex-1.5.1.tar.gz/download

```shell
$ path/to/jflex-1.5.1/bin/jflex Lexer.lex
$ java -jar path/to/jflex-1.5.1/lib/java-cup-11a.jar -parser Parser Parser.cup
$ javac -cp path/to/jflex-1.5.1/lib/java-cup-11a.jar:. *.java
$ java -cp path/to/jflex-1.5.1/lib/java-cup-11a.jar:. Main < test.in
```
