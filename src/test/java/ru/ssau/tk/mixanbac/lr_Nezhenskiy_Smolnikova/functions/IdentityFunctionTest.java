package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertNotEquals;

public class IdentityFunctionTest {
    private final static double DELTA = 0.001;
    private final MathFunction identityFunction = new UnitFunction();
    @Test
    public void testApply() {
        assertEquals(identityFunction.apply(1.111), 1,DELTA);
        assertNotEquals(identityFunction.apply(1.111), 2,DELTA);
        assertNotEquals(identityFunction.apply(1.111), 3,DELTA);
    }
}

