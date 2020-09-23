package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {

    private final static double DELTA = 0.00001;

    ConstantFunction testFunction = new ConstantFunction(3.141592653589793238462643);

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(3.14), 3.141592653589793238462643, DELTA);
    }

    @Test
    public void testGetConstanta() {
        assertEquals(testFunction.getConstanta(), 3.141592653589793238462643, DELTA);
    }

}
