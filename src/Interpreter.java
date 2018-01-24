import java.util.ArrayList;

public class Interpreter {
    private Token currentToken;
    private Lexer lexer;
    // Define validTokens to be used in expr checking
    private ArrayList<TokenType> highPrecOperations = new ArrayList<TokenType>();
    private ArrayList<TokenType> lowPrecOperations = new ArrayList<TokenType>();

    Interpreter(Lexer lexer){
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

    private int term(){
        // term : factor((MUL | DIV) factor)*
        int result = factor();

        while(this.highPrecOperations.contains(this.currentToken.getType())){
            Token token = currentToken;
            if(token.getType() == TokenType.MUL){
                eat(TokenType.MUL);
                result = result * factor();
            } else if(token.getType() == TokenType.DIV){
                eat(TokenType.DIV);
                result = result / factor();
            }
        }
        return result;
    }

    private int factor(){
        //factor : INTEGER
        Token token;
        token = this.currentToken;
        eat(TokenType.INTEGER);
        return Integer.parseInt(token.getValue());
    }

    public int expr(){
        //Arithmetic expression parser / interpreter.
        // expr : term((PLUS | MINUS) term)*
        // term : factor((MUL | DIV) factor)*
        // factor : INTEGER
        int result = term();
        while(this.lowPrecOperations.contains(currentToken.getType())){
            Token token = currentToken;
            if(token.getType() == TokenType.PLUS){
                eat(TokenType.PLUS);
                result = result + term();
            } else if(token.getType() == TokenType.MINUS){
                eat(TokenType.MINUS);
                result = result - term();
            }
        }
        return result;
    }
}