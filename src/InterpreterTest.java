public class InterpreterTest {
    public static void main(String args[]){
        Lexer lexer = new Lexer("BEGIN a:= 5+10 END.");
        Parser parser = new Parser(lexer);
        Interpreter interpreter = new Interpreter(parser);
        interpreter.interpret();
        System.out.println(interpreter.GLOBAL_SCOPE);

//        System.out.println(lexer.getNextToken().getValue());
//        System.out.println(lexer.getNextToken().getValue());
//        System.out.println(lexer.getNextToken().getValue());
//        System.out.println(lexer.getNextToken().getValue());
//        System.out.println(lexer.getNextToken().getValue());
//        System.out.println(lexer.getNextToken().getValue());
//        System.out.println(lexer.getNextToken().getValue());
    }
}