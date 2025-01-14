/**
 * En rekursiv medåknings-parser för binära träd
 *
 * Författare: Per Austrin
 */
public class Parser {
	private Lexer lexer;

	public Parser(Lexer lexer) {
		this.lexer = lexer;
	}

	public ParseTree parse() throws SyntaxError {
		// Startsymbol är BinTree
		ParseTree result = BinTree();
		// Borde inte finnas något kvar av indata när vi parsat ett bintree
		if (lexer.hasMoreTokens())
			throw new SyntaxError();
		return result;
	}

	private ParseTree BinTree() throws SyntaxError {
		// Kika på nästa indata-token för att välja produktionsregel
		Token t = lexer.peekToken();
		if (t.getType() == TokenType.Leaf) {
			// regeln BinTree -> Leaf(Number) ska användas
			lexer.nextToken(); // ät upp t
			// sedan borde det vara en parentes
			if (lexer.nextToken().getType() != TokenType.LParen) throw new SyntaxError();
			// nästa token borde vara ett Number
			Token param = lexer.nextToken();
			if (param.getType() != TokenType.Number) throw new SyntaxError();
			// sedan en högerparentes
			if (lexer.nextToken().getType() != TokenType.RParen) throw new SyntaxError();
			// returnera parseträd som bara består av en löv-nod med
			// rätt data
			return new LeafNode((Integer)param.getData());
		} else if (t.getType() == TokenType.Branch) {
			// regeln BinTree -> Branch(BinTree,BinTree) ska användas
			// ät upp t
			lexer.nextToken();
			// sedan borde det vara en vänsterparentes
			if (lexer.nextToken().getType() != TokenType.LParen) throw new SyntaxError();
			// sedan borde det komma ett bintree, rekursivt anrop för att parsa detta
			ParseTree left = BinTree();
			// sedan ett komma
			if (lexer.nextToken().getType() != TokenType.Comma) throw new SyntaxError();
			// sedan ett till bintree
			ParseTree right = BinTree();
			// sedan en högerparentes
			if (lexer.nextToken().getType() != TokenType.RParen) throw new SyntaxError();
			return new BranchNode(left, right);
		} else {
			throw new SyntaxError();
		}	
	}
}
