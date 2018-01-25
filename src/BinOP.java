public class BinOP extends AST{
    private AST left;
    private Token op;
    private AST right;

    BinOP(AST left, Token op, AST right){
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public AST getLeft(){
        return this.left;
    }

    public Token getOp(){
        return this.op;
    }

    public AST getRight(){
        return this.right;
    }
}
