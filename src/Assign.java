public class Assign extends AST{
    private Var left;
    private Token op;
    private AST right;
    private Token token;


    Assign(Var left, Token op, AST right){
        this.left = left;
        this.op = op;
        this.token = token;
        this.right = right;
    }

    public Var getLeft(){
        return this.left;
    }

    public AST getRight(){
        return this.right;
    }
}
