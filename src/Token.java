public class Token {
    public TokenType type;
    private Object value;

    Token(TokenType type, Object value){
        this.type = type;
        this.value = value;
    }

    public TokenType getType(){
        return this.type;
    }

    public String getValue(){
        return this.value.toString();
    }

}


