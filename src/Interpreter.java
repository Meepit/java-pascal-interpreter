import java.util.HashMap;

public class Interpreter extends NodeVisitor{
    private Parser parser;
    public HashMap<String, Integer> GLOBAL_SCOPE = new HashMap<String, Integer>();

    Interpreter(Parser parser){
        this.parser = parser;
    }

    public int interpret(){
        AST tree = this.parser.parse();
        return visit(tree);
    }
}