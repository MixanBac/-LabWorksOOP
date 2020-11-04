package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.DifferentLengthOfArraysException;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {
    @Test
    public void testInterpolate() {
        AbstractTabulatedFunction mockedInterpolate = new MockTabulatedFunction();

        final double delta = 0.0001;
        assertEquals(mockedInterpolate.interpolate(1.4, 1.0, 2.0, 3.0, 4.0), 3.4, delta);
        assertEquals(mockedInterpolate.interpolate(1.5, 1.0, 2.0, 3.0, 4.0), 3.5, delta);
        assertEquals(mockedInterpolate.interpolate(1.6, 1.0, 2.0, 3.0, 4.0), 3.6, delta);
        assertNotEquals(mockedInterpolate.interpolate(1.5, 1.0, 2.0, 3.0, 4.0), 8.1);
    }

    @Test
    public void testApply() {
        AbstractTabulatedFunction mockedApply = new MockTabulatedFunction();

        final double delta = 0.0001;
        assertEquals(mockedApply.apply(1.3), 4.0, delta);
        assertEquals(mockedApply.apply(4.0), 4.0, delta);
        assertNotEquals(mockedApply.apply(1.3), 5.2);
    }

    @Test
    public void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            double[] valuesX = new double[]{8, 78};
            double[] valuesY = new double[]{13, 14,-3};
            AbstractTabulatedFunction.checkLengthIsTheSame(valuesX, valuesY);
        });
    }

    @Test
    public void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] valuesX = new double[]{-80, -100, 5, 18, 0};
            AbstractTabulatedFunction.checkSorted(valuesX);
        });
    }
    @Test
    public void testTestToString() {
        double[] x = {0, 5, 10};
        double[] y = {10, 20, 30};
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(x, y);
        assertEquals(arrayFunction.toString(), "ArrayTabulatedFunction size = 3\n[0.0; 10.0]\n[5.0; 20.0]\n[10.0; 30.0]");

        TabulatedFunction listFunction = new LinkedListTabulatedFunction(x, y);
        assertEquals(listFunction.toString(), "LinkedListTabulatedFunction size = 3\n[0.0; 10.0]\n[5.0; 20.0]\n[10.0; 30.0]");
    }
}