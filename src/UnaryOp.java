public class UnaryOp extends AST{
    private Token op; // UnaryOp token
    private AST expr; // AST node

    UnaryOp(Token op, AST expr){
        this.op = op;
        this.expr = expr;
    }

    public Token getOp(){
        return this.op;
    }

    public AST getExpr(){
        return this.expr;
    }
}
