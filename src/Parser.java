import java.util.ArrayList;

public class Parser {
    private Lexer lexer;
    private Token currentToken;

    private ArrayList<TokenType> highPrecOperations = new ArrayList<TokenType>();
    private ArrayList<TokenType> lowPrecOperations = new ArrayList<TokenType>();

    Parser(Lexer lexer){
        this.lexer = lexer;
        this.currentToken = this.lexer.getNextToken();
        lowPrecOperations.add(TokenType.PLUS);
        lowPrecOperations.add(TokenType.MINUS);

        highPrecOperations.add(TokenType.MUL);
        highPrecOperations.add(TokenType.DIV);
    }

    private void error() throws RuntimeException{
        throw new RuntimeException("Error parsing input");
    }

    private void eat(TokenType tokenType){
        //Confirms the current token matches a token in a valid expression
        //If true increment the currentToken else throw error
        if(this.currentToken.getType() == tokenType){
            this.currentToken = this.lexer.getNextToken();
        } else {
            error();
        }
    }

    private AST term(){
        // term : factor((MUL | DIV) factor)*
        AST node = factor();

        while(this.highPrecOperations.contains(this.currentToken.getType())){
            Token token = currentToken;
            if(token.getType() == TokenType.MUL){
                eat(TokenType.MUL);
            } else if(token.getType() == TokenType.DIV){
                eat(TokenType.DIV);
            }

            node = new BinOP(node, token, factor());
        }
        return node;

    }

    private AST factor(){
        //factor : INTEGER | LPAREN expr RPAREN

        AST node = new AST();
        Token token = this.currentToken;
        if(token.getType() == TokenType.PLUS){
            eat(TokenType.PLUS);
            node = new UnaryOp(token, factor());
            return node;
        } else if(token.getType() == TokenType.MINUS){
            eat(TokenType.MINUS);
            node = new UnaryOp(token, factor());
            return node;
        } else if(token.getType() == TokenType.INTEGER){
            eat(TokenType.INTEGER);
            node = new Num(token);
            return node;
        } else if(token.getType() == TokenType.LPAREN){
            eat(TokenType.LPAREN);
            node = expr();
            eat(TokenType.RPAREN);
            return node;
        }
        error();
        return node;
    }

    private AST expr(){
        //Arithmetic expression parser / interpreter.
        // expr : term((PLUS | MINUS) term)*
        // term : factor((MUL | DIV) factor)*
        // factor : INTEGER | LPAREN expr RPAREN
        AST node = term();

        while(this.lowPrecOperations.contains(currentToken.getType())){
            Token token = currentToken;
            if(token.getType() == TokenType.PLUS){
                eat(TokenType.PLUS);
            } else if(token.getType() == TokenType.MINUS){
                eat(TokenType.MINUS);
            }

            node = new BinOP(node, token, term());
        }
        return node;
    }

    public AST parse(){
        return expr();
    }
}
