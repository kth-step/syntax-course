/**
 * En klass för att göra lexikal analys, konvertera indataströmmen
 * till en sekvens av tokens.  Den här klassen läser in hela
 * indatasträngen och konverterar den på en gång i konstruktorn.  Man
 * kan tänka sig en variant som läser indataströmmen allt eftersom
 * tokens efterfrågas av parsern, men det blir lite mer komplicerat.
 *
 * Författare: Per Austrin
 */
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lexer {
    private String input;
    private List<Token> tokens;
    private int currentToken;

    // Hjälpmetod som läser in innehållet i en inputstream till en
    // sträng
    private static String readInput(InputStream f) throws java.io.IOException {
        Reader stdin = new InputStreamReader(f);
        StringBuilder buf = new StringBuilder();
        char input[] = new char[1024];
        int read = 0;
        while ((read = stdin.read(input)) != -1) {
            buf.append(input, 0, read);
        }
        return buf.toString();
    }


    public Lexer(InputStream in) throws java.io.IOException {
        String input = Lexer.readInput(in);
        // Ett regex som beskriver hur ett token kan se ut, plus whitespace (som vi här vill ignorera helt)
        Pattern tokenPattern = Pattern.compile("[0-9]+|\\(|\\)|\\+|-|\\*|/|\\s+");
        Matcher m = tokenPattern.matcher(input);
        int inputPos = 0;
        tokens = new ArrayList<Token>();
        currentToken = 0;
        // Hitta förekomster av tokens/whitespace i indata
        while (m.find()) {
            // Om matchningen inte börjar där den borde har vi hoppat
            // över något skräp i indata, markera detta som ett
            // "Invalid"-token
            if (m.start() != inputPos) {
                tokens.add(new Token(TokenType.INVALID));
            }
            // Kolla vad det var som matchade
            if (m.group().equals("("))
                tokens.add(new Token(TokenType.LPAREN));
            else if (m.group().equals(")"))
                tokens.add(new Token(TokenType.RPAREN));
            else if (m.group().equals("+"))
                tokens.add(new Token(TokenType.PLUS));
            else if (m.group().equals("-"))
                tokens.add(new Token(TokenType.MINUS));
            else if (m.group().equals("*"))
                tokens.add(new Token(TokenType.TIMES));
            else if (m.group().equals("/"))
                tokens.add(new Token(TokenType.DIVIDE));
            else if (Character.isDigit(m.group().charAt(0)))
                tokens.add(new Token(TokenType.NUM, Integer.parseInt(m.group())));
            inputPos = m.end();
        }
        // Kolla om det fanns något kvar av indata som inte var ett token
        if (inputPos != input.length()) {
            tokens.add(new Token(TokenType.INVALID));
        }
        // Token som signalerar slut på indata
        tokens.add(new Token(TokenType.EOF));
        // Debug-kod för att skriva ut token-sekvensen
        //for (Token token: tokens)
        //   System.out.println(token.getType());
    }

    // Kika på nästa token i indata, utan att gå vidare
    public Token peekToken() throws SyntaxError {
        // Slut på indataströmmen
        if (!hasMoreTokens())
            throw new SyntaxError();
        return tokens.get(currentToken);
    }

    // Hämta nästa token i indata och gå framåt i indata
    public Token nextToken() throws SyntaxError {
        Token res = peekToken();
        ++currentToken;
        return res;
    }

    public boolean hasMoreTokens() {
        return currentToken < tokens.size();
    }
}
