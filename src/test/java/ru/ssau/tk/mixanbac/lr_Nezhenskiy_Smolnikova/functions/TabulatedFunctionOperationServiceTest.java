package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedFunctionOperationService;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    final double DELTA = 0.0001;
    private final double[] valuesX = new double[]{-81, -16, -1, 0, 1, 16, 81};
    private final double[] valuesY = new double[]{-21, -14, -7, 0, 7, 14, 21};

    ArrayTabulatedFunction getTestArray() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    LinkedListTabulatedFunction getTestList() {
        return new LinkedListTabulatedFunction(valuesX, valuesY);
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
}
