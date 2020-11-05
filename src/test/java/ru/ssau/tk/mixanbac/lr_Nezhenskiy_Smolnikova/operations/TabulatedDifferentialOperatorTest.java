package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {
    public static final double DELTA = 0.0001;
    private final double[] xValues = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues = new double[]{6, 7, 8, 9, 10};

    @Test
    public void testDerive() {
        TabulatedFunction testFunction = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedDifferentialOperator differentialListOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction diffFunction = differentialListOperator.derive(testFunction);
        assertEquals(diffFunction.getX(0), 1, DELTA);
        assertEquals(diffFunction.getX(1), 2, DELTA);
        assertEquals(diffFunction.getX(2), 3, DELTA);
        assertEquals(diffFunction.getY(1), 1, DELTA);
        assertEquals(diffFunction.getY(2), 1, DELTA);
        assertEquals(diffFunction.getY(3), 1, DELTA);

        TabulatedFunction testArray = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedDifferentialOperator differentialArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction diffFunctionA = differentialArrayOperator.derive(testArray);
        assertEquals(diffFunctionA.getX(3), 4, DELTA);
        assertEquals(diffFunctionA.getX(4), 5, DELTA);
        assertEquals(diffFunctionA.getX(2), 3, DELTA);
        assertEquals(diffFunctionA.getY(0), 1, DELTA);
        assertEquals(diffFunctionA.getY(1), 1, DELTA);
        assertEquals(diffFunctionA.getY(2), 1, DELTA);

    }
}