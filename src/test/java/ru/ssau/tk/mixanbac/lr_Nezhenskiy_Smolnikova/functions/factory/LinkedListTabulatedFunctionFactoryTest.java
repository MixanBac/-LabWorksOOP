package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.MathFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.SqrFunction;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionFactoryTest {
    private final double[] valuesX = new double[]{1., 2., 3., 4., 5., 6.};
    private final double[] valuesY = new double[]{1., 4., 9., 16., 25., 36.};
    private final MathFunction sqrFunction = new SqrFunction();

    @Test
    public void testCreate() {
        var testListFunction1 = new LinkedListTabulatedFunctionFactory().create(valuesX, valuesY);
        var testListFunction2 = new LinkedListTabulatedFunctionFactory().create(sqrFunction, 25, 50, 25);
        assertTrue(testListFunction1 instanceof LinkedListTabulatedFunction);
        assertTrue(testListFunction2 instanceof LinkedListTabulatedFunction);
    }

}