import java.util.HashMap;

public class NodeVisitor {
    public HashMap<String, Integer> GLOBAL_SCOPE = new HashMap<String, Integer>();

    public int visit(AST node){
        //getattr(x,foobar) is equivalent to x.foobar

        String methodName = node.getClass().getSimpleName();
        if(methodName.equals("BinOP")){
            return visitBinOP((BinOP) node);
        } else if(methodName.equals("Num")){
            return visitNum((Num) node);
        } else if(methodName.equals("UnaryOp")){
            return visitUnaryOP((UnaryOp) node);
        } else if(methodName.equals("Compound")){
            return visitCompound((Compound) node);
        } else if(methodName.equals("NoOp")){
            ;
        } else if(methodName.equals("Assign")){
            return visitAssign((Assign) node);
        } else if(methodName.equals("Var")){
            return visitVar((Var) node);
        } else {
            throw new RuntimeException("No valid visit method implemented");
        }
        return 0;

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

    public int visitUnaryOP(UnaryOp node){
        TokenType op = node.getOp().getType();
        if(op == TokenType.PLUS){
            return +visit(node.getExpr());
        } else if(op == TokenType.MINUS){
            return -visit(node.getExpr());
        }
        return 0;
    }

    public int visitCompound(Compound node){
        for(AST child: node.getChildren()){
            visit(child);
        }
        return 0;
    }

    public void visitNoOp(NoOp node){

    }

    public int visitAssign(Assign node){
        String varName = node.getLeft().getValue();
        System.out.println("Adding " + varName + " to global scope");
        GLOBAL_SCOPE.put(varName, visit(node.getRight()));
        System.out.println(GLOBAL_SCOPE);
        return 0;
    }

    public int visitVar(Var node){
        String varName = node.getValue();
        int val = GLOBAL_SCOPE.get(varName);
        if(val == '\0'){
            throw new RuntimeException("Value not found in global scope");
        } else {
            return val;
        }
    }
}
