import java.util.ArrayList;

public class Compound extends AST {
    //Represents a 'BEGIN ... END' block
    private ArrayList<AST> children;

    Compound(){
        this.children = new ArrayList<AST>();
    }

    public ArrayList<AST> getChildren(){
        return this.children;
    }

}
