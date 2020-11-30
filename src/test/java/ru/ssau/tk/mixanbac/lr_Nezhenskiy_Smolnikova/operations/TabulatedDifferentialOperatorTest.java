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
        assertTrue(diffFunction instanceof LinkedListTabulatedFunction);
        assertEquals(diffFunction.getX(0), 1, DELTA);
        assertEquals(diffFunction.getX(1), 2, DELTA);
        assertEquals(diffFunction.getX(2), 3, DELTA);
        assertEquals(diffFunction.getY(1), 1, DELTA);
        assertEquals(diffFunction.getY(2), 1, DELTA);
        assertEquals(diffFunction.getY(3), 1, DELTA);

        TabulatedFunction testArray = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedDifferentialOperator differentialArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction diffFunctionA = differentialArrayOperator.derive(testArray);
        assertTrue(diffFunctionA instanceof ArrayTabulatedFunction);
        assertEquals(diffFunctionA.getX(3), 4, DELTA);
        assertEquals(diffFunctionA.getX(4), 5, DELTA);
        assertEquals(diffFunctionA.getX(2), 3, DELTA);
        assertEquals(diffFunctionA.getY(0), 1, DELTA);
        assertEquals(diffFunctionA.getY(1), 1, DELTA);
        assertEquals(diffFunctionA.getY(2), 1, DELTA);

    }

    @Test
    public void testDeriveSynchronously() {
        TabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new double[]{5, 6, 7, 8, 9}, new double[]{25, 36, 49, 64, 81});
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction diffFunctionList = differentialOperator.deriveSynchronously(linkedListTabulatedFunction);

        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new double[]{5, 6, 7, 8, 9}, new double[]{5, 6, 7, 8, 9});
        TabulatedDifferentialOperator differentialOperator1 = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction diffFunctionArray = differentialOperator1.deriveSynchronously(arrayTabulatedFunction);

        assertEquals(diffFunctionList.getX(0), 5);
        assertEquals(diffFunctionList.getX(1), 6);
        assertEquals(diffFunctionList.getX(2), 7);
        assertEquals(diffFunctionList.getX(3), 8);
        assertEquals(diffFunctionList.getX(4), 9);

        assertEquals(diffFunctionList.getY(0), 11);
        assertEquals(diffFunctionList.getY(1), 13);
        assertEquals(diffFunctionList.getY(2), 15);
        assertEquals(diffFunctionList.getY(3), 17);
        assertEquals(diffFunctionList.getY(4), 17);
        assertTrue(diffFunctionList instanceof LinkedListTabulatedFunction);

        assertEquals(diffFunctionArray.getX(0), 5);
        assertEquals(diffFunctionArray.getX(1), 6);
        assertEquals(diffFunctionArray.getX(2), 7);
        assertEquals(diffFunctionArray.getX(3), 8);
        assertEquals(diffFunctionArray.getX(4), 9);

        assertEquals(diffFunctionArray.getY(0), 1);
        assertEquals(diffFunctionArray.getY(1), 1);
        assertEquals(diffFunctionArray.getY(2), 1);
        assertEquals(diffFunctionArray.getY(3), 1);
        assertEquals(diffFunctionArray.getY(4), 1);
        assertTrue(diffFunctionArray instanceof ArrayTabulatedFunction);
    }
}