/**
 * En rekursiv medåknings-parser för aritmetiska uttryck.
 * Se README för mer info.
 *
 * Författare: Per Austrin
 */
public class Parser {
    private Lexer lexer;

    /** Variabler för att kunna ge pratig förklaring av vad som händer
     * i parsningen.  Om man inte har behov av denna feature kan koden
     * som relaterar till dessa variabler tas bort.
     */
    private boolean verbose;
    private int depth;

    /** Om verbose är satt till sann kommer Parsern att prata en massa
     * medans den gör sitt jobb.
     */
    public Parser(Lexer lexer, boolean verbose) {
        this.lexer = lexer;
        this.verbose = verbose;
    }

    private void talk(String s) {
        if (verbose)
            System.out.printf("%"+(3*depth+1)+"s%s\n", "", s);
    }

    public ParseTree parse() throws SyntaxError {
        // Startsymbol är Expr
        depth = 0;
        talk("Start parse()");
        ++depth;
        ParseTree result = Expr();
        // Borde inte finnas något kvar av indata när vi parsat ett uttryck
        if (lexer.nextToken().getType() != TokenType.EOF) {
            throw new SyntaxError();
        }
        return result;
    }

    private ParseTree Expr() throws SyntaxError {
        talk("Enter Expr()");
        ++depth;
        ParseTree result = Term();
        talk("[Expr()] Read term done");
        while (lexer.peekToken().getType() == TokenType.PLUS ||
               lexer.peekToken().getType() == TokenType.MINUS) {
            Token operator = lexer.nextToken();
            talk("[Expr()] Read operator " + operator);
            ParseTree next = Term();
            talk("[Expr()] Read term done");
            result = new BinaryOperation(operator.getType(), result, next);
        }
        --depth;
        talk("Leave Expr()");
        return result;
    }

    private ParseTree Term() throws SyntaxError  {
        talk("Enter Term()");
        ++depth;
        ParseTree result = Factor();
        talk("[Term()] Read factor done");
        while (lexer.peekToken().getType() == TokenType.TIMES ||
               lexer.peekToken().getType() == TokenType.DIVIDE) {
            Token operator = lexer.nextToken();
            talk("[Term()] Read operator " + operator);
            ParseTree next = Factor();
            talk("[Term()] Read factor done");
            result = new BinaryOperation(operator.getType(), result, next);
        }
        --depth;
        talk("Leave Term()");
        return result;
    }

    private ParseTree Factor() throws SyntaxError {
        talk("Enter Factor()");
        ++depth;
        Token t = lexer.nextToken();
        talk("[Factor()] Got token " + t);
        if (t.getType() == TokenType.NUM) {
            talk("[Factor()] Use rule Factor -> Num");
            --depth;
            talk("Leave Factor()");
            return new NumberNode((Integer)t.getData());
        } else if (t.getType() == TokenType.LPAREN) {
            talk("[Factor()] Use rule Factor -> (Expr)");
            ParseTree result = Expr();
            talk("[Factor()] Read expr done");
            t = lexer.nextToken();
            talk("[Factor()] Got token " + t);
            if (t.getType() != TokenType.RPAREN)
                throw new SyntaxError();
            --depth;
            talk("Leave Factor()");
            return result;
        }
        throw new SyntaxError();
    }

}
