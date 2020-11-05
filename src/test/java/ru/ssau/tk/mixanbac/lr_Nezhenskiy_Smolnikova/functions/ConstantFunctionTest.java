package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedDifferentialOperatorTest.DELTA;

public class ConstantFunctionTest {

    ConstantFunction testFunction = new ConstantFunction(Math.PI);

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(3.14), Math.PI, DELTA);
        assertNotEquals(testFunction.apply(3.14), 3, DELTA);
        assertNotEquals(testFunction.apply(3.14), 8, DELTA);
    }

    @Test
    public void testGetConstant() {
        assertEquals(testFunction.getConstant(), Math.PI, DELTA);
        assertNotEquals(testFunction.getConstant(), 3, DELTA);
        assertNotEquals(testFunction.getConstant(), 8, DELTA);
    }

}
