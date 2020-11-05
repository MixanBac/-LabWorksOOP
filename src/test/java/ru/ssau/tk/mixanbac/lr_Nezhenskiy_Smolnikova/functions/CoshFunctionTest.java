package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedDifferentialOperatorTest.DELTA;

public class CoshFunctionTest {

    @Test
    public void testApply() {
        MathFunction testFunction = new CoshFunction();
        assertEquals(testFunction.apply(0), 1.0, DELTA);
        assertNotEquals(testFunction.apply(1), 10.0, DELTA);
        assertNotEquals(testFunction.apply(1), 17.0, DELTA);
    }

}
