package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {
    public static final double DELTA = 0.0001;
    private final double[] xValues = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues = new double[]{6, 7, 8, 9, 10};

    @Test
    public void testDerive() {
        TabulatedFunction testfunction = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction diffFunction = differentialOperator.derive(testfunction);
        assertEquals(diffFunction.getX(0), 1, DELTA);
        assertEquals(diffFunction.getX(1), 2, DELTA);
        assertEquals(diffFunction.getX(2), 3, DELTA);
        assertEquals(diffFunction.getY(1), 1, DELTA);
        assertEquals(diffFunction.getY(2), 1, DELTA);
        assertEquals(diffFunction.getY(3), 1, DELTA);
    }
}