package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedDifferentialOperatorTest.DELTA;

public class ReverseFunctionTest {

    private final ReverseFunction reverseFunction = new ReverseFunction();

    @Test
    public void testApply() {
        assertEquals(reverseFunction.apply(5), 0.2, DELTA);
        assertNotEquals(reverseFunction.apply(5), 0.6, DELTA);
        assertNotEquals(reverseFunction.apply(4), 0.28, DELTA);

    }
}


