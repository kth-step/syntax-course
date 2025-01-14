/**
 * Exempel på rekursiv medåkning: en parser för binära träd enligt grammatiken
 *
 * BinTree --> leaf ( Number ) | branch ( BinTree , BinTree )
 *
 * Parsar trädet och skriver sedan ut det i ett lite annorlunda format
 * samt byter plats på vänster och höger i alla noder (i brist på
 * något roligare att göra)
 *
 * Provkörning från terminal på fil "test.in"
 *
 * javac *.java
 * java Main < test.in
 *
 *
 * (Det här exempelprogrammet skrevs av en person som normalt inte
 * använder Java, så ha överseende om delar av koden inte är så
 * vacker.)
 *
 * Författare: Per Austrin
 */
public class Main {
	public static void main(String args[]) throws java.io.IOException, SyntaxError {
		Lexer lexer = new Lexer(System.in);
		Parser parser = new Parser(lexer);
		ParseTree result = parser.parse();
		// Parsning klar, gör vad vi nu vill göra med syntaxträdet
		System.out.println(result.process());
	}
}
