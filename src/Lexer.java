public class Lexer {
    private String text;
    private int pos;
    private char currentChar;

    Lexer(String text){
        this.text = text;
        this.pos = 0;
        this.currentChar = this.text.charAt(this.pos);
    }

    public String getText(){
        return this.text;
    }

    private void error() throws RuntimeException{
        throw new RuntimeException("Error parsing input");
    }

    private void advance(){
        //Advance the 'pointer' one character in the text, set it to null if EOF
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

    public Token getNextToken(){
        //Tokenizer, Lexer,
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
                token = new Token(TokenType.PLUS,'+');
                return token;
            } else if(this.currentChar == '-'){
                advance();
                token = new Token(TokenType.MINUS, '-');
                return token;
            } else if(this.currentChar == '*'){
                advance();
                token = new Token(TokenType.MUL, '*');
                return token;
            } else if(this.currentChar == '/'){
                advance();
                token = new Token(TokenType.DIV, '/');
                return token;
            } else {
                error();
            }
        }
        return token;
    }
}
