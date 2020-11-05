package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedDifferentialOperatorTest.DELTA;

public class UnitFunctionTest {
    private final UnitFunction testFunction = new UnitFunction();

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(Math.PI), 1, DELTA);
        assertNotEquals(testFunction.apply(Math.PI), 10, DELTA);
        assertNotEquals(testFunction.apply(Math.PI), -2, DELTA);
    }

    @Test
    public void testGetConstant() {
        assertEquals(testFunction.getConstant(), 1, DELTA);
        assertNotEquals(testFunction.getConstant(), 10, DELTA);
        assertNotEquals(testFunction.getConstant(), -2, DELTA);
    }
}
