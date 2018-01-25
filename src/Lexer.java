import java.util.HashMap;

public class Lexer {
    private String text;
    private int pos;
    private char currentChar;
    private final HashMap<String, Token> RESERVED_KEYWORDS = new HashMap<String, Token>();

    Lexer(String text){
        this.text = text;
        this.pos = 0;
        this.currentChar = this.text.charAt(this.pos);

        // Populate reserved keywords
        RESERVED_KEYWORDS.put("BEGIN", new Token(TokenType.BEGIN, "BEGIN"));
        RESERVED_KEYWORDS.put("END", new Token(TokenType.END, "END"));


    }

    public String getText(){
        return this.text;
    }

    private Token id(){
        String result = "";
        while(this.currentChar != '\0' && (Character.isLetter(this.currentChar)) || Character.isDigit(this.currentChar)){
            result = result + this.currentChar;
            advance();
        }

        return RESERVED_KEYWORDS.getOrDefault(result, new Token(TokenType.ID, result));
    }

    public Character peek(){
        int peekPos = this.pos + 1;
        if(peekPos > this.text.length() - 1){
            return '\0';
        } else {
            return this.text.charAt(peekPos);
        }
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
            } else if(this.currentChar == '('){
                advance();
                token = new Token(TokenType.LPAREN, '(');
                return token;
            } else if(this.currentChar == ')'){
                advance();
                token = new Token(TokenType.RPAREN, ')');
                return token;
            } else if(Character.isLetter(this.currentChar)){
                return id();
            } else if(currentChar == ':' && peek() == '='){
                advance();
                advance();
                return new Token(TokenType.ASSIGN, ":=");
            } else if(this.currentChar == ';'){
                advance();
                return new Token(TokenType.SEMI, ';');
            } else if(this.currentChar == '.'){
                advance();
                return new Token(TokenType.DOT, '.');
            } else {
                error();
            }
        }
        return token;
    }
}
