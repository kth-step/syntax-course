// JFlex-specifikation av tokens för grammatik för binära träd.
// Se README.txt för mer info
// Författare: Per Austrin
import java.lang.System;
import java_cup.runtime.Symbol;

%%
%cup
%class Lexer

%%

branch { return new Symbol(sym.BRANCH); }
leaf { return new Symbol(sym.LEAF); }
"(" { return new Symbol(sym.LPAREN); }
")" { return new Symbol(sym.RPAREN); }
, { return new Symbol(sym.COMMA); }
[0-9]+ { return new Symbol(sym.NUM, new Integer(yytext())); }
[ \t\n] { }
