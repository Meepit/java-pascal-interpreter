public class Num extends AST{
    private Token token;
    private int value;

    Num(Token token){
        this.token = token;
        this.value = Integer.parseInt(token.getValue());
    }
}
