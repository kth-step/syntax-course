// Författare: Per Austrin

// Ett syntaxträd
abstract class ParseTree {
    abstract public int evaluate();
}

// Ett syntaxträd som representerar ett tal
class NumberNode extends ParseTree {
    Integer data;
    public NumberNode(Integer data) {
        this.data = data;
    }
    public int evaluate() {
        return (int)data;
    }
}

// Ett syntaxträd som representerar någon av de fyra binära operationerna
class BinaryOperation extends ParseTree {
    TokenType operation;
    ParseTree left, right;
    public BinaryOperation(TokenType operation, ParseTree left, ParseTree right) {
        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    public int evaluate() {
        switch (operation) {
        case PLUS: return left.evaluate() + right.evaluate();
        case MINUS: return left.evaluate() - right.evaluate();
        case TIMES: return left.evaluate() * right.evaluate();
        case DIVIDE: return left.evaluate() / right.evaluate();
        }
        assert false; // borde aldrig kunna hända
        return 0;
    }
}
