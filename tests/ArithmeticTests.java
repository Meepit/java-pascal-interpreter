import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ArithmeticTests {

    private int interpretCode(String code){
        Lexer lexer = new Lexer(code);
        Interpreter interpreter = new Interpreter(lexer);
        return interpreter.expr();
    }

    // Addition tests
    @Test
    public void testSingleDigitAddition() {
        int result = interpretCode("5+1");
        assertEquals(6, result);
    }

    @Test
    public void testMultiDigitAddition(){
        int result = interpretCode("50+22");
        assertEquals(72, result);
    }

    @Test
    public void testMultiOperatorAddition(){
        int result = interpretCode("10+10+10+10+10+9");
        assertEquals(59, result);
    }

    // Subtraction tests
    @Test
    public void testSingleDigitSubtraction(){
        int result = interpretCode("9-2");
        assertEquals(7, result);
    }

    @Test
    public void testMultiDigitSubtraction(){
        int result = interpretCode("21-11");
        assertEquals(10, result);
    }

    @Test
    public void testMultiOperatorSubtraction(){
        int result = interpretCode("100-10-10-10-10-5");
        assertEquals(55, result);
    }


    // Misc tests
    @Test
    public void testValidWhiteSpace(){
        int result = interpretCode("10   + 10 -3+ 1");
        assertEquals(18, result);
    }

    @Test
    public void testSingleFactor(){
        int result = interpretCode("5");
        assertEquals(5, result);
    }

    // Multiplication tests
    @Test
    public void testSingleDigitMultiplication(){
        int result = interpretCode("5*5");
        assertEquals(25, result);
    }

    @Test
    public void testMultiDigitMultiplication(){
        int result = interpretCode("10*20");
        assertEquals(200, result);
    }

    @Test
    public void testMultiOperatorMultiplication(){
        int result = interpretCode("5*5*10");
        assertEquals(250, result);
    }

    //Division tests
    @Test
    public void testSingleDigitDivision(){
        int result = interpretCode("5/5");
        assertEquals(1, result);
    }

    @Test
    public void testMultiDigitDivision(){
        int result = interpretCode("100/50");
        assertEquals(2, result);
    }

    @Test
    public void testMultiOperatorDivision(){
        int result = interpretCode("50/10/5");
        assertEquals(1, result);
    }

    @Test
    public void testRemainderDivision(){
        int result = interpretCode("55/10");
        assertEquals(5, result);
    }

    // Mixed operator tests
    @Test
    public void testAdditionSubtraction(){
        int result = interpretCode("20+10-5+15");
        assertEquals(40, result);
    }

    //Bracket tests
    @Test
    public void testBrackets(){
        int result = interpretCode("(50+5)/11");
        assertEquals(5, result);
    }

    
}
