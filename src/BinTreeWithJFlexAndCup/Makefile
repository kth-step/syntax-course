build: Lexer.java Parser.java sym.java
	javac -cp /usr/share/java/cup.jar *.java

Lexer.java: Lexer.lex
	jflex $<

Parser.java: Parser.cup
	cup -parser Parser $<

sym.java: Parser.java

clean:
	rm -f sym.java Parser.java Lexer.java *.class

