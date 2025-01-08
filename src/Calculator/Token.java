// Författare: Per Austrin

// De olika token-typer vi har i grammatiken
enum TokenType {
    NUM, LPAREN, RPAREN, PLUS, MINUS, TIMES, DIVIDE, EOF, INVALID
}

/** Klass för att representera en token
 * I praktiken vill man nog även spara info om vilken rad/position i
 * indata som varje token kommer ifrån, för att kunna ge bättre
 * felmeddelanden
 */
class Token {
    private TokenType type;
    private Object data;

    public Token(TokenType type) {
        this.type = type;
        this.data = null;
    }

    public Token(TokenType type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String toString() {
        if (data == null)
            return type.toString();
        else
            return type.toString() + "(" + data.toString() + ")";
    }

    public TokenType getType() { return type; }
    public Object getData() { return data; }
}
