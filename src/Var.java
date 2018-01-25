public class Var extends AST {
    private Token token;
    private String value;

    Var(Token token){
        this.token = token;
        this.value = this.token.getValue();
    }

    public String getValue(){
        return this.value;
    }
}
