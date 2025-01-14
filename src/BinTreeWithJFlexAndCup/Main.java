/**
 * Exempel på JFlex och Cup: parser för binära träd
 * Se README.txt för mer info
 *
 * Författare: Per Austrin
 */
import java_cup.runtime.Symbol;

public class Main {
	public static void main(String args[]) throws Exception {
		Lexer lexer = new Lexer(System.in);
		Parser parser = new Parser(lexer);
		Symbol parse_result = parser.parse();
		ParseTree result = (ParseTree)parse_result.value;
		// Parsning klar, gör vad vi nu vill göra med syntaxträdet
		System.out.println(result.process());
	}
}
