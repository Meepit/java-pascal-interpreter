public class InterpreterTest {
    public static void main(String args[]){
        Lexer lexer = new Lexer("5+2+(5/2)");
        Interpreter interpreter = new Interpreter(lexer);
        int result = interpreter.expr();
        System.out.println(result);
    }
}
