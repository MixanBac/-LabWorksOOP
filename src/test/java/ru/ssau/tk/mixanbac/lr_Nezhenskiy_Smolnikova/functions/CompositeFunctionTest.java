package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction functionH = new IdentityFunction();
        MathFunction functionG = new Reverse();
        MathFunction functionF = new CompositeFunction(functionH, functionG);
        assertEquals(functionF.apply(36), 0.02777777777, DELTA);
        MathFunction cosh = new CoshFunction();
        MathFunction rev = new Reverse();
        MathFunction sqr = new SqrFunction();
        MathFunction composite = cosh.andThen(rev).andThen(sqr);
        double result = rev.andThen(cosh).andThen(sqr).apply(3.14);
    }
}
