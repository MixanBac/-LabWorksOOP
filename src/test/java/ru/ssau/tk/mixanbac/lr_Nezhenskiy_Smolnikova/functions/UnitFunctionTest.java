package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UnitFunctionTest {
    UnitFunction testFunction = new UnitFunction();

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(3.14), 1, DELTA);
    }

    @Test
    public void testGetConstanta() {
        assertEquals(testFunction.getConstanta(), 1, DELTA);
    }
}
