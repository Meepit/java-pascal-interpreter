public class Interpreter extends NodeVisitor{
    private Parser parser;

    Interpreter(Parser parser){
        this.parser = parser;
    }

    public int interpret(){
        AST tree = this.parser.parse();
        return visit(tree);
    }
}