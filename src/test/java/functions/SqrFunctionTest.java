package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        assertEquals(java.lang.Math.pow(1, 2),1.0 ,DELTA);
        assertNotEquals(java.lang.Math.pow(1, 2),10.0, DELTA);
        assertEquals(java.lang.Math.pow(2, 2),4.0 ,DELTA);
        assertNotEquals(java.lang.Math.pow(2, 2),2.0, DELTA);
        assertEquals(java.lang.Math.pow(3, 2),9.0 ,DELTA);
        assertNotEquals(java.lang.Math.pow(3, 2),8.0 ,DELTA);
    }
}