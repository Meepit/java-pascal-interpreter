public class BinOP extends AST{
    private Object left;
    private Object op;
    private Object right;

    BinOP(Object left, Object op, Object right){
        this.left = left;
        this.op = op;
        this.right = right;
    }
}
