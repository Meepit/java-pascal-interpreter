public class InterpreterTest {
    public static void main(String args[]){
        Interpreter interpreter = new Interpreter("5");
        int result = interpreter.expr();
        System.out.println(result);
    }
}
