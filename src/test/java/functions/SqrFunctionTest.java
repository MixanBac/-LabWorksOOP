package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction function = new SqrFunction(1);
        assertEquals( 1.0E-5, DELTA);
    }
}