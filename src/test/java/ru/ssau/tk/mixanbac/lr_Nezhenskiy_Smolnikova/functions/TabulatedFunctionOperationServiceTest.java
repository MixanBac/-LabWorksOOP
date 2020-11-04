package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.InconsistentFunctionsException;
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
    TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

    ArrayTabulatedFunction getTestArray() {
        return new ArrayTabulatedFunction(valuesX, valuesYForArray);
    }

    LinkedListTabulatedFunction getTestList() {
        return new LinkedListTabulatedFunction(valuesX, valuesYForList);
    }

    @Test
    public void testAsPoints() {
        ArrayTabulatedFunction testArrayFunction = getTestArray();
        Point[] Points = TabulatedFunctionOperationService.asPoints(testArrayFunction);
        int i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, testArrayFunction.getX(i), DELTA);
            assertEquals(myPoint.y, testArrayFunction.getY(i++), DELTA);
        }
        assertEquals(testArrayFunction.getCount(), i);

        LinkedListTabulatedFunction testListFunction = getTestList();
        Points = TabulatedFunctionOperationService.asPoints(testListFunction);
        i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, testListFunction.getX(i), DELTA);
            assertEquals(myPoint.y, testListFunction.getY(i++), DELTA);
        }
        assertEquals(testListFunction.getCount(), i);
    }

    @Test
    public void testGetFactory() {
        assertTrue(service.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory()).getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        service.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(service.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSum() {
        ArrayTabulatedFunction testArrayFunction = getTestArray();
        LinkedListTabulatedFunction testListFunction = getTestList();

        final double[] errorX1 = new double[]{0, 1, 2};
        final double[] errorY1 = new double[]{0, 1, 2};
        TabulatedFunction errorTest1 = new ArrayTabulatedFunction(errorX1, errorY1);
        assertThrows(InconsistentFunctionsException.class, () -> service.sum(testListFunction, errorTest1));

        final double[] errorX2 = new double[]{-81, -16, -1, 0, 1, 16, 81};
        final double[] errorY2 = new double[]{-21, -14, -7, 0, 7, 14, 21};
        TabulatedFunction errorTest2 = new ArrayTabulatedFunction(errorX2, errorY2);
        assertThrows(InconsistentFunctionsException.class, () -> service.sum(testListFunction, errorTest2));


        TabulatedFunction testSumOfArrays = service.sum(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testSumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForArray[i] + valuesYForArray[i++]);
        }

        TabulatedFunction testSumOfLists = service.sum(testListFunction, testListFunction);
        i = 0;
        for (Point point : testSumOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] + valuesYForList[i++]);
        }

        TabulatedFunction testSumOfArrayAndList = service.sum(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testSumOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForArray[i] + valuesYForList[i++]);
        }
    }

    @Test
    public void testSubtract() {
        ArrayTabulatedFunction testArrayFunction = getTestArray();
        LinkedListTabulatedFunction testListFunction = getTestList();

        TabulatedFunction testSubtractOfArrays = service.subtract(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testSubtractOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForArray[i] - valuesYForArray[i++]);
        }

        TabulatedFunction testSubtractOfLists = service.subtract(testListFunction, testListFunction);
        i = 0;
        for (Point point : testSubtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] - valuesYForList[i++]);
        }

        TabulatedFunction testSubtractOfArrayAndList = service.subtract(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testSubtractOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForArray[i] - valuesYForList[i++]);
        }
    }

}
