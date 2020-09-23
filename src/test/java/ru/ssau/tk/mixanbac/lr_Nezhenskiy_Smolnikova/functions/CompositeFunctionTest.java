package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    private final static double DELTA = 0.01;
    @Test
    public void testApply() {
        MathFunction x = new Reverse();
        MathFunction y = new IdentityFunction();
        MathFunction a = new CompositeFunction(y,x);
        MathFunction b = new CompositeFunction(a,x);
        assertEquals(b.apply(8),8 , DELTA);

    }
}