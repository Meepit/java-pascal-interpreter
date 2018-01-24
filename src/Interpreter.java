import java.util.ArrayList;

public class Interpreter {
    private String text;
    private int pos;
    private Token currentToken;
    private char currentChar;
    // Define validTokens to be used in expr checking
    private ArrayList<TokenType> validTokens = new ArrayList<TokenType>();

    Interpreter(String text){
        this.text = text;
        currentChar = text.charAt(pos);
        validTokens.add(TokenType.PLUS);
        validTokens.add(TokenType.MINUS);
    }

    private void error() throws RuntimeException{
        throw new RuntimeException("Error parsing input");
    }

    private void advance(){
        // Advance the 'pointer' one character in the text, set it to null if EOF
        // Set currentChar
        this.pos++;
        if(this.pos > this.text.length() - 1){
            this.currentChar = '\0';
        } else {
            this.currentChar = this.text.charAt(this.pos);
        }
    }

    private void skipWhitespace(){
        while(this.currentChar != '\0' && this.currentChar == ' '){
            advance();
        }
    }

    private int buildInteger(){
        // Used to construct multi-digit integers.
        // Keep concating the integer until EOF or space
        StringBuilder result = new StringBuilder("");
        while(this.currentChar != '\0' && Character.isDigit(this.currentChar)){
            result.append(this.currentChar);
            advance();
        }
        return Integer.parseInt(result.toString());
    }

    private Token getNextToken(){
        //Tokenizer, Lexer.
        //While the current token is not EOF, find the token type and instantiate an instance
        //With a value of the current character
        Token token = new Token(TokenType.EOF, null);
        while(this.currentChar != '\0'){
            if(this.currentChar == ' '){
                skipWhitespace();
                continue;
            } else if(Character.isDigit(this.currentChar)){
                token = new Token(TokenType.INTEGER, buildInteger());
                return token;
            } else if(this.currentChar == '+'){
                advance();
                token = new Token(TokenType.PLUS, '+');
                return token;
            } else if(this.currentChar == '-'){
                advance();
                token = new Token(TokenType.MINUS, '-');
                return token;
            } else {
                error();
            }
        }
        return token;
    }

    private void eat(TokenType tokenType){
        //Confirms the current token matches a token in a valid expression
        //If true increment the currentToken else throw error
        if(this.currentToken.getType() == tokenType){
            this.currentToken = getNextToken();
        } else {
            error();
        }
    }

    private int term(){
        // Returns the int value of an INTEGER token.
        Token token = this.currentToken;
        eat(TokenType.INTEGER);
        return Integer.parseInt(token.getValue());
    }

    public int expr(){
        // Evaluates inputted code against valid grammars.
        this.currentToken = this.getNextToken();
        int result = term();
        while(this.validTokens.contains(this.currentToken.getType())){
            Token token = this.currentToken;
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