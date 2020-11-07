package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.Point;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedFunctionOperationService;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    final double DELTA = 0.0001;
    private final double[] valuesX = new double[]{-81, -16, -1, 0, 1, 16, 81};
    private final double[] valuesYForArray = new double[]{-21, -14, -7, 0, 7, 14, 21};
    private final double[] valuesYForList = new double[]{1, 25, 50, 75, 100, 125, 150};
    private final TabulatedFunctionOperationService operationServiceArray = new TabulatedFunctionOperationService();
    private final TabulatedFunctionOperationService operationServiceList = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
    private final TabulatedFunction testArrayFunction = new ArrayTabulatedFunction(valuesX, valuesYForArray);
    private final TabulatedFunction testListFunction = new LinkedListTabulatedFunction(valuesX, valuesYForList);

    @Test
    public void testAsPoints() {
        Point[] Points = TabulatedFunctionOperationService.asPoints(testArrayFunction);
        int i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, testArrayFunction.getX(i), DELTA);
            assertEquals(myPoint.y, testArrayFunction.getY(i++), DELTA);
        }
        assertEquals(testArrayFunction.getCount(), i);

        Points = TabulatedFunctionOperationService.asPoints(testListFunction);
        i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, testListFunction.getX(i), DELTA);
            assertEquals(myPoint.y, testListFunction.getY(i++), DELTA);
        }
        assertEquals(testListFunction.getCount(), i);
    }

    @Test
    public void testGetFactory_testSetFactory() {
        assertTrue(operationServiceArray.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(operationServiceList.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSum() {
        final double[] errorX1 = new double[]{0, 1, 2};
        final double[] errorY1 = new double[]{0, 1, 2};
        TabulatedFunction errorTest1 = new ArrayTabulatedFunction(errorX1, errorY1);
        assertThrows(InconsistentFunctionsException.class, () -> operationServiceList.sum(testListFunction, errorTest1));
        final double[] errorX2 = new double[]{-15, -10, -1, 0, 1, 10, 15};
        TabulatedFunction errorTest2 = new ArrayTabulatedFunction(errorX2, valuesYForArray);
        assertThrows(InconsistentFunctionsException.class, () -> operationServiceList.sum(testListFunction, errorTest2));
        TabulatedFunction testSumOfArrays = operationServiceArray.sum(testArrayFunction, testArrayFunction);
        assertTrue(testSumOfArrays instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : testSumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForArray[i] + valuesYForArray[i++]);
        }
        TabulatedFunction testSumOfLists = operationServiceList.sum(testListFunction, testListFunction);
        assertTrue(testSumOfLists instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : testSumOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList [i] + valuesYForList [i++]);
        }
        TabulatedFunction testSumOfArrayAndList = operationServiceArray.sum(testArrayFunction, testListFunction);
        assertTrue(testSumOfArrayAndList instanceof ArrayTabulatedFunction);
        i = 0;
        for (Point point : testSumOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForArray[i] + valuesYForList[i++]);
        }
    }

    @Test
    public void testSubtract() {
        TabulatedFunction testSubtractOfArrays = operationServiceArray.sum(testArrayFunction, testArrayFunction);
        assertTrue(testSubtractOfArrays instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : testSubtractOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForArray[i] - valuesYForArray[i++]);
        }
        TabulatedFunction testSubtractOfList = operationServiceList.sum(testListFunction, testListFunction);
        assertTrue(testSubtractOfList instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : testSubtractOfList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList [i] - valuesYForList [i++]);
        }
        TabulatedFunction testSubtractOfArrayAndList = operationServiceArray.sum(testArrayFunction, testListFunction);
        assertTrue(testSubtractOfArrayAndList instanceof ArrayTabulatedFunction);
        i = 0;
        for (Point point : testSubtractOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForArray[i] - valuesYForList[i++]);
        }
    }

    @Test
    public void testMultiply() {
        TabulatedFunction testMultiplyOfArrays = operationServiceArray.sum(testArrayFunction, testArrayFunction);
        assertTrue(testMultiplyOfArrays instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : testMultiplyOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForArray[i] * valuesYForArray[i++]);
        }
        TabulatedFunction testMultiplyOfList = operationServiceList.sum(testListFunction, testListFunction);
        assertTrue(testMultiplyOfList instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : testMultiplyOfList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList [i] * valuesYForList [i++]);
        }
        TabulatedFunction testMultiplyOfArrayAndList = operationServiceArray.sum(testArrayFunction, testListFunction);
        assertTrue(testMultiplyOfArrayAndList instanceof ArrayTabulatedFunction);
        i = 0;
        for (Point point : testMultiplyOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForArray[i] * valuesYForList[i++]);
        }
    }

    @Test
    public void testDivide() {
        TabulatedFunction testDivideOfArrays = operationServiceArray.sum(testArrayFunction, testArrayFunction);
        assertTrue(testDivideOfArrays instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : testDivideOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForArray[i] - valuesYForArray[i++]);
        }
        TabulatedFunction testDivideOfList = operationServiceList.sum(testListFunction, testListFunction);
        assertTrue(testDivideOfList instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : testDivideOfList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList [i] - valuesYForList [i++]);
        }
        TabulatedFunction testDivideOfArrayAndList = operationServiceArray.sum(testArrayFunction, testListFunction);
        assertTrue(testDivideOfArrayAndList instanceof ArrayTabulatedFunction);
        i = 0;
        for (Point point : testDivideOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForArray[i] - valuesYForList[i++]);
        }
    }
}
