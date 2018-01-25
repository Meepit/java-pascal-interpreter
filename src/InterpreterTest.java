public class InterpreterTest {
    public static void main(String args[]){
        Lexer lexer = new Lexer("BEGIN a := 2; END.");
        System.out.println(lexer.getNextToken().getValue());
        System.out.println(lexer.getNextToken().getValue());
        System.out.println(lexer.getNextToken().getValue());
        System.out.println(lexer.getNextToken().getValue());
        System.out.println(lexer.getNextToken().getValue());
        System.out.println(lexer.getNextToken().getValue());
        System.out.println(lexer.getNextToken().getValue());
        System.out.println(lexer.getNextToken().getValue());
    }
}