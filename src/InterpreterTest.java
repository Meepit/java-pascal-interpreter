public class InterpreterTest {
    public static void main(String args[]){
        Lexer lexer = new Lexer("--5+5");
        Parser parser = new Parser(lexer);
        Interpreter interpreter = new Interpreter(parser);
        int result = interpreter.interpret();
        System.out.println(result);
    }
}