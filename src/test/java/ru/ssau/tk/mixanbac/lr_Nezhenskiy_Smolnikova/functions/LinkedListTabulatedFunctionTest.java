package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.InterpolationException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final static double DELTA = 0.001;
    private final MathFunction sqr = new SqrFunction();
    private final double[] xValues = new double[]{1.0, 1.1, 1.2, 1.3, 1.4};
    private final double[] yValues = new double[]{2.0, 2.1, 2.2, 2.3, 2.4};

    private LinkedListTabulatedFunction getListOfArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction getListOfMathFunction() {
        return new LinkedListTabulatedFunction(sqr, 0, 10, 101);
    }

    @Test
    public void testLinkedListTabulatedFunction() {
        double[] xValues = {1.1};
        double[] yValues = {5.2};
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(xValues, yValues));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(sqr, -10, -34, 2));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(sqr, -5, -15, -1));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(sqr, -4, -80, -2));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(sqr, 4, -8, 5));
    }

    @Test
    public void testIterator() {
        Iterator<Point> iterator = getListOfArray().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(point.x, getListOfArray().getX(i), DELTA);
            assertEquals(point.y, getListOfArray().getY(i++), DELTA);
        }
        System.out.println(i);
        i = 0;
        for (Point point : getListOfArray()) {
            assertEquals(point.x, getListOfArray().getX(i), DELTA);
            assertEquals(point.y, getListOfArray().getY(i++), DELTA);
        }
        System.out.println(i);
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testGetCount() {
        assertEquals(getListOfArray().getCount(), 5);
        assertNotEquals(getListOfArray().getCount(), 1);
        assertNotEquals(getListOfArray().getCount(), 14);

    }

    @Test
    public void testGetX() {
        assertEquals(getListOfArray().getX(1), 1.1, DELTA);
        assertEquals(getListOfArray().getX(2), 1.2, DELTA);
        assertNotEquals(getListOfArray().getX(1), 2, DELTA);
        assertEquals(getListOfMathFunction().getX(5), 0.5, DELTA);
        assertEquals(getListOfMathFunction().getX(1), 0.1, DELTA);
        assertNotEquals(getListOfMathFunction().getX(1), 1.6666, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getListOfArray().getX(-1));
        assertThrows(IllegalArgumentException.class, () -> getListOfMathFunction().getX(-1));

    }

    @Test
    public void testGetY() {
        assertEquals(getListOfArray().getY(1), 2.1, DELTA);
        assertEquals(getListOfArray().getY(2), 2.2, DELTA);
        assertNotEquals(getListOfArray().getY(1), 4, DELTA);
        assertEquals(getListOfMathFunction().getY(1), 0.01000, DELTA);
        assertNotEquals(getListOfMathFunction().getY(2), 2.7777, DELTA);
        assertEquals(getListOfMathFunction().getY(2), 0.040000, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getListOfArray().getY(-1));
        assertThrows(IllegalArgumentException.class, () -> getListOfMathFunction().getY(-1));
    }

    @Test
    public void testSetY() {

        LinkedListTabulatedFunction testListOfArray = getListOfArray();
        LinkedListTabulatedFunction testListOfMathFunction = getListOfMathFunction();
        testListOfArray.setY(1, 300.);
        testListOfArray.setY(2, 20.);
        testListOfArray.setY(3, 1);
        testListOfMathFunction.setY(4, 1001.);
        testListOfMathFunction.setY(34, 1003.);
        testListOfMathFunction.setY(54, 1003.);
        assertEquals(testListOfArray.getY(1), 300, DELTA);
        assertEquals(testListOfArray.getY(2), 20, DELTA);
        assertEquals(testListOfArray.getY(3), 1, DELTA);
        assertNotEquals(testListOfArray.getY(4), 25, DELTA);
        assertEquals(testListOfMathFunction.getY(4), 1001., DELTA);
        assertEquals(testListOfMathFunction.getY(54), 1003., DELTA);
        assertEquals(testListOfMathFunction.getY(50), 24.99999, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getListOfMathFunction().setY(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> getListOfArray().setY(-1, 0));
    }

    @Test
    public void testIndexOfX() {
        assertEquals(getListOfArray().indexOfX(3), -1., DELTA);
        assertEquals(getListOfArray().indexOfX(2), -1., DELTA);
        assertEquals(getListOfMathFunction().indexOfX(0), 0, DELTA);
        assertEquals(getListOfMathFunction().indexOfX(5), -1, DELTA);
        assertEquals(getListOfMathFunction().indexOfX(-5), -1, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(getListOfArray().indexOfY(25), -1, DELTA);
        assertEquals(getListOfArray().indexOfY(23), -1, DELTA);
        assertEquals(getListOfMathFunction().indexOfY(1), -1, DELTA);
        assertEquals(getListOfMathFunction().indexOfY(2), -1, DELTA);
        assertEquals(getListOfMathFunction().indexOfY(-2), -1, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(getListOfArray().leftBound(), 1, DELTA);
        assertEquals(getListOfArray().leftBound(), 1, DELTA);
        assertEquals(getListOfMathFunction().leftBound(), 0, DELTA);
        assertEquals(getListOfMathFunction().leftBound(), 0, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(getListOfArray().rightBound(), 1.4, DELTA);
        assertNotEquals(getListOfArray().rightBound(), 1., DELTA);
        assertNotEquals(getListOfMathFunction().rightBound(), Double.NaN, DELTA);
        assertEquals(getListOfMathFunction().rightBound(), 9.9999, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(getListOfArray().floorIndexOfX(2), 5, DELTA);
        assertNotEquals(getListOfArray().floorIndexOfX(11), 10, DELTA);
        assertEquals(getListOfMathFunction().floorIndexOfX(5), 50, DELTA);
        assertNotEquals(getListOfMathFunction().floorIndexOfX(2), 0, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getListOfArray().floorIndexOfX(-10));
        assertThrows(IllegalArgumentException.class, () -> getListOfMathFunction().floorIndexOfX(-10));
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getListOfArray().extrapolateLeft(0), 1, DELTA);
        assertNotEquals(getListOfArray().extrapolateLeft(0), -2, DELTA);
        assertEquals(getListOfMathFunction().extrapolateLeft(0), 0, DELTA);
        assertNotEquals(getListOfMathFunction().extrapolateLeft(0), 9, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(getListOfArray().extrapolateRight(4), 5., DELTA);
        assertNotEquals(getListOfArray().extrapolateRight(4), 50., DELTA);
        assertEquals(getListOfMathFunction().extrapolateRight(40), 697., DELTA);
        assertNotEquals(getListOfMathFunction().extrapolateRight(40), 767., DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(getListOfArray().apply(0.9), 1.9, DELTA);
        assertEquals(getListOfMathFunction().apply(-3.2), -0.3200, DELTA);
        assertEquals(getListOfArray().apply(1.56), 2.5600, DELTA);
        assertEquals(getListOfMathFunction().apply(21), 318.90, DELTA);
        assertEquals(getListOfArray().apply(1.22), 2.22, DELTA);
        assertEquals(getListOfMathFunction().apply(7.25), 52.565, DELTA);
    }

    @Test
    public void testInterpolate() {

        assertEquals(getListOfArray().interpolate(1.23, getListOfArray().floorIndexOfX(1.23)), 2.23, DELTA);
        assertEquals(getListOfArray().interpolate(1.15, getListOfArray().floorIndexOfX(1.15)), 2.15, DELTA);
        assertNotEquals(getListOfArray().interpolate(1.33, getListOfArray().floorIndexOfX(1.33)), 8.43, DELTA);
        assertEquals(getListOfMathFunction().interpolate(1.41, getListOfMathFunction().floorIndexOfX(1.41)), 1.988, DELTA);
        assertEquals(getListOfMathFunction().interpolate(1.35, getListOfMathFunction().floorIndexOfX(1.35)), 1.825, DELTA);
        assertNotEquals(getListOfMathFunction().interpolate(1.33, getListOfMathFunction().floorIndexOfX(1.33)), 8.43, DELTA);
        assertThrows(InterpolationException.class, () -> getListOfArray().interpolate(0.5, 2));
        assertThrows(InterpolationException.class, () -> getListOfMathFunction().interpolate(7.5, 3));
    }

}


