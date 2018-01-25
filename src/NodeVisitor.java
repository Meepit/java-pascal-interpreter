public class NodeVisitor {
    public int visit(AST node){
        //getattr(x,foobar) is equivalent to x.foobar

        String methodName = node.getClass().getSimpleName();
        if(methodName.equals("BinOP")){
            return visitBinOP((BinOP) node);
        } else if(methodName.equals("Num")){
            return visitNum((Num) node);
        } else {
            throw new RuntimeException("No valid visit method implemented");
        }

    }

    public int visitBinOP(BinOP node){
        if(node.getOp().getType() == TokenType.PLUS){
            return visit(node.getLeft()) + visit(node.getRight());
        } else if(node.getOp().getType() == TokenType.MINUS){
            return visit(node.getLeft()) - visit(node.getRight());
        } else if(node.getOp().getType() == TokenType.MUL){
            return visit(node.getLeft()) * visit(node.getRight());
        } else if(node.getOp().getType() == TokenType.DIV){
            return visit(node.getLeft()) / visit(node.getRight());
        }
        return 0;
    }

    public int visitNum(Num node){
        return node.getValue();
    }
}
