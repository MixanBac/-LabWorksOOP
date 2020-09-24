package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ZeroFunctionTest {
    private final ZeroFunction testFunction = new ZeroFunction();

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(Math.PI), 0, DELTA);
        assertNotEquals(testFunction.apply(8), 30, DELTA);
        assertNotEquals(testFunction.apply(1), -1, DELTA);
    }


    @Test
    public void testGetConstant() {
        assertEquals(testFunction.getConstant(), 0, DELTA);
        assertNotEquals(testFunction.getConstant(), 10, DELTA);
        assertNotEquals(testFunction.getConstant(), -5, DELTA);
    }
}
