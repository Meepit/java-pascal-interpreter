public class InterpreterTest {
    public static void main(String args[]){
        Lexer lexer = new Lexer("20+10/2");
        Interpreter interpreter = new Interpreter(lexer);
        int result = interpreter.expr();
        System.out.println(result);
    }
}
