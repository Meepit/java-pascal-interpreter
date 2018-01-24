import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ArithmeticTests {

    private int interpretCode(String code){
        Interpreter interpreter = new Interpreter(code);
        return interpreter.expr();
    }

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

    @Test
    public void testAdditionSubtraction(){
        int result = interpretCode("20+10-5+15");
        assertEquals(40, result);
    }

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
}
