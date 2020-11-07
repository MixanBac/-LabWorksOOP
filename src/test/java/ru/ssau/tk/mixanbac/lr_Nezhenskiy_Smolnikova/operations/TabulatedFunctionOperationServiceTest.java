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
    private final TabulatedFunctionOperationService testServiceArray = new TabulatedFunctionOperationService();
    private final TabulatedFunctionOperationService testServiceList = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
    private TabulatedFunction testArrayFunction = new ArrayTabulatedFunction(valuesX, valuesYForArray);
    private TabulatedFunction testListFunction = new LinkedListTabulatedFunction(valuesX, valuesYForList);

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
    public void testGetFactory() {
        assertTrue(testServiceArray.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(testServiceList.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

}
