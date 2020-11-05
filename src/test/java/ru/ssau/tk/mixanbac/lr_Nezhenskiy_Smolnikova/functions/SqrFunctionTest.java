package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedDifferentialOperatorTest.DELTA;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        MathFunction testFunction = new SqrFunction();
        assertEquals(testFunction.apply(1), 1.0, DELTA);
        assertNotEquals(testFunction.apply(1), 10.0, DELTA);
        assertEquals(testFunction.apply(2), 4.0, DELTA);
        assertNotEquals(testFunction.apply(2), 2.0, DELTA);
        assertEquals(testFunction.apply(-3), 9.0, DELTA);
        assertNotEquals(testFunction.apply(-3), 8.0, DELTA);
    }
}