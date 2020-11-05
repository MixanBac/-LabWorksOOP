package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.InterpolationException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;
import static ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedDifferentialOperatorTest.DELTA;

public class ArrayTabulatedFunctionTest {
    double[] xValues = new double[]{1.0, 1.1, 1.2, 1.3, 1.4};
    double[] yValues = new double[]{2.0, 2.1, 2.2, 2.3, 2.4};
    private final MathFunction source = new SqrFunction();


    private ArrayTabulatedFunction testingArrayFunction() {
        return new ArrayTabulatedFunction(source, 1, 16, 6);
    }


    @Test
    public void testArrayTabulatedFunction() {
        double[] xValues = {4.1};
        double[] yValues = {6.2};
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(xValues, yValues));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(source, -37, -100, 2));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(source, -5, -15, -1));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(source, -4, -80, -2));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(source, 4, -8, 1));
    }

    @Test
    public void testGetCount() {

        assertEquals(testingArrayFunction().getCount(), 6);
        assertNotEquals(testingArrayFunction().getCount(), 7);
        assertNotEquals(testingArrayFunction().getCount(), 5);

    }

    @Test
    public void testApply() {
        ArrayTabulatedFunction testingApply = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingApply.apply(2.0), 3.0, DELTA);
        assertEquals(testingApply.apply(8.84), 9.84, DELTA);
        assertEquals(testingApply.apply(7.82), 8.82, DELTA);
        assertNotEquals(testingApply.apply(7.82), 1.23, DELTA);
        assertEquals(testingArrayFunction().apply(-8.97), -48.85, DELTA);
        assertEquals(testingArrayFunction().apply(27), 575.0, DELTA);
        assertEquals(testingArrayFunction().apply(8.46), 73.82, DELTA);
        assertNotEquals(testingArrayFunction().apply(8.46), 59.25, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        ArrayTabulatedFunction testingFloorIndexOfX = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.35), 3, DELTA);
        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.43), 5, DELTA);
        assertNotEquals(testingFloorIndexOfX.floorIndexOfX(1.43), 4);
        assertEquals(testingArrayFunction().floorIndexOfX(8.93), 2);
        assertEquals(testingArrayFunction().floorIndexOfX(66.67), 6);
    }

    @Test
    public void testExtrapolateLeft() {
        ArrayTabulatedFunction testingExtrapolateLeft = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingExtrapolateLeft.extrapolateLeft(1.1), 2.1, DELTA);
        assertEquals(testingExtrapolateLeft.extrapolateLeft(-1.5), -0.5, DELTA);
        assertNotEquals(testingExtrapolateLeft.extrapolateLeft(1.5), 40.0);
        assertEquals(testingArrayFunction().extrapolateLeft(-80), -404, DELTA);
        assertEquals(testingArrayFunction().extrapolateLeft(-2.1), -14.5, DELTA);
        assertNotEquals(testingArrayFunction().extrapolateLeft(-2), 70, DELTA);

    }

    @Test
    public void testExtrapolateRight() {
        ArrayTabulatedFunction testingExtrapolateRight = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingExtrapolateRight.extrapolateRight(7.82), 8.82, DELTA);
        assertEquals(testingExtrapolateRight.extrapolateRight(3.56), 4.56, DELTA);
        assertNotEquals(testingExtrapolateRight.extrapolateRight(2.45), 2.18);
        assertEquals(testingArrayFunction().extrapolateRight(25), 517, DELTA);
        assertEquals(testingArrayFunction().extrapolateRight(69), 1793, DELTA);
        assertNotEquals(testingArrayFunction().extrapolateRight(69), 89, DELTA);

    }

    @Test
    public void testInterpolate() {
        ArrayTabulatedFunction testingInterpolate = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingInterpolate.interpolate(1.23, testingInterpolate.floorIndexOfX(1.23)), 2.23, DELTA);
        assertEquals(testingInterpolate.interpolate(1.15, testingInterpolate.floorIndexOfX(1.15)), 2.15, DELTA);
        assertNotEquals(testingInterpolate.interpolate(1.33, testingInterpolate.floorIndexOfX(1.33)), 8.43, DELTA);
        assertEquals(testingArrayFunction().interpolate(1.41, testingArrayFunction().floorIndexOfX(1.41)), 3.0499, DELTA);
        assertEquals(testingArrayFunction().interpolate(1.35, testingArrayFunction().floorIndexOfX(1.35)), 2.75, DELTA);
        assertNotEquals(testingArrayFunction().interpolate(1.33, testingArrayFunction().floorIndexOfX(1.33)), 8.43, DELTA);
        assertThrows(InterpolationException.class, () -> testingInterpolate.interpolate(0.5, 2));
        assertThrows(InterpolationException.class, () -> testingArrayFunction().interpolate(7.5, 3));
    }

    @Test
    public void testGetX() {
        ArrayTabulatedFunction testingGetX = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingGetX.getX(1), 1.1, DELTA);
        assertEquals(testingGetX.getX(2), 1.2, DELTA);
        assertNotEquals(testingGetX.getX(1), 2.2, DELTA);
        assertEquals(testingArrayFunction().getX(1), 4, DELTA);
        assertEquals(testingArrayFunction().getX(3), 10, DELTA);
        assertNotEquals(testingArrayFunction().getX(3), 16, DELTA);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testingGetX.getX(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testingArrayFunction().getX(-1));

    }

    @Test
    public void testGetY() {
        ArrayTabulatedFunction testingGetY = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingGetY.getY(1), 2.1, DELTA);
        assertEquals(testingGetY.getY(2), 2.2, DELTA);
        assertNotEquals(testingGetY.getY(1), 3.2, DELTA);
        assertEquals(testingArrayFunction().getY(1), 16, DELTA);
        assertEquals(testingArrayFunction().getY(3), 100, DELTA);
        assertNotEquals(testingArrayFunction().getY(3), 162, DELTA);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testingGetY.getY(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testingArrayFunction().getY(-1));

    }

    @Test
    public void testSetY() {
        ArrayTabulatedFunction testingSetY = new ArrayTabulatedFunction(xValues, yValues);
        ArrayTabulatedFunction testingArrayFunction = new ArrayTabulatedFunction(source, 1, 16, 6);
        testingSetY.setY(1, 2.28);
        assertEquals(testingSetY.getY(1), 2.28, DELTA);
        testingSetY.setY(2, 2.35);
        assertEquals(testingSetY.getY(2), 2.35, DELTA);
        assertNotEquals(testingSetY.getY(2), 1.45, DELTA);
        testingArrayFunction.setY(2, 93);
        assertEquals(testingArrayFunction.getY(2), 93, DELTA);
        assertNotEquals(testingArrayFunction.getY(2), 0, DELTA);
        testingArrayFunction.setY(4, 23);
        assertEquals(testingArrayFunction.getY(4), 23, DELTA);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testingSetY.setY(-1, 0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testingArrayFunction.setY(-1, 0));

    }

    @Test
    public void testIndexOfX() {
        ArrayTabulatedFunction testingIndexOfX = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingIndexOfX.indexOfX(1.3), 3, DELTA);
        assertEquals(testingIndexOfX.indexOfX(1.4), 4, DELTA);
        assertNotEquals(testingIndexOfX.indexOfX(1.5), 1, DELTA);
        assertEquals(testingArrayFunction().indexOfX(13), 4);
        assertEquals(testingArrayFunction().indexOfX(16), 5);
        assertNotEquals(testingArrayFunction().indexOfX(13), 1);

    }

    @Test
    public void testIndexOfY() {
        ArrayTabulatedFunction testingIndexOfY = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(testingIndexOfY.indexOfY(2.5), -1, DELTA);
        assertEquals(testingIndexOfY.indexOfY(2.4), 4, DELTA);
        assertNotEquals(testingIndexOfY.indexOfY(2.1), 4, DELTA);
        assertEquals(testingArrayFunction().indexOfY(49), 2, DELTA);
        assertEquals(testingArrayFunction().indexOfY(169), 4, DELTA);
        assertNotEquals(testingArrayFunction().indexOfY(49), 6, DELTA);

    }

    @Test
    public void testLeftBound() {

        ArrayTabulatedFunction testingLeftBound = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingLeftBound.leftBound(), 1.0, 0.0001);
        assertNotEquals(testingLeftBound.leftBound(), 1.2, 0.0001);
        assertNotEquals(testingLeftBound.leftBound(), 1.5, 0.0001);

        assertEquals(testingLeftBound.leftBound(), 1.0, DELTA);
        assertNotEquals(testingLeftBound.leftBound(), 1.2, DELTA);
        assertNotEquals(testingLeftBound.leftBound(), 1.5, DELTA);
        assertEquals(testingArrayFunction().leftBound(), 1, DELTA);
        assertNotEquals(testingArrayFunction().leftBound(), 2, DELTA);
        assertNotEquals(testingArrayFunction().leftBound(), 5, DELTA);

    }

    @Test
    public void testRightBound() {

        ArrayTabulatedFunction testingRightBound = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingRightBound.rightBound(), 1.4, 0.0001);
        assertNotEquals(testingRightBound.rightBound(), 1.6, 0.0001);
        assertNotEquals(testingRightBound.rightBound(), 1.3, 0.0001);

        assertEquals(testingRightBound.rightBound(), 1.4, DELTA);
        assertNotEquals(testingRightBound.rightBound(), 1.6, DELTA);
        assertNotEquals(testingRightBound.rightBound(), 1.3, DELTA);
        assertEquals(testingArrayFunction().rightBound(), 16, DELTA);
        assertNotEquals(testingArrayFunction().rightBound(), 19, DELTA);
        assertNotEquals(testingArrayFunction().rightBound(), 27, DELTA);
    }

    @Test
    public void testIteratorWhile() {

        ArrayTabulatedFunction testingIteratorWhileA = new ArrayTabulatedFunction(xValues, yValues);
        Iterator<Point> myIterator = testingIteratorWhileA.iterator();
        int i = 0;
        while (myIterator.hasNext()) {
            Point myPoint = myIterator.next();
            assertEquals(testingIteratorWhileA.getX(i), myPoint.x, DELTA);
            assertEquals(testingIteratorWhileA.getY(i++), myPoint.y, DELTA);
        }
        assertEquals(testingIteratorWhileA.getCount(), i);

        ArrayTabulatedFunction testingIteratorWhileB = testingArrayFunction();
        myIterator = testingIteratorWhileB.iterator();
        i = 0;
        while (myIterator.hasNext()) {
            Point myPoint = myIterator.next();
            assertEquals(testingIteratorWhileB.getX(i), myPoint.x, DELTA);
            assertEquals(testingIteratorWhileB.getY(i++), myPoint.y, DELTA);
        }
        assertEquals(testingIteratorWhileB.getCount(), i);
    }

    @Test
    public void testIteratorForEach() {

        ArrayTabulatedFunction testingIteratorWhileA = new ArrayTabulatedFunction(xValues, yValues);
        int i = 0;
        for (Point myPoint : testingIteratorWhileA) {
            assertEquals(myPoint.x, testingIteratorWhileA.getX(i), DELTA);
            assertEquals(myPoint.y, testingIteratorWhileA.getY(i++), DELTA);
        }
        assertEquals(testingIteratorWhileA.getCount(), i);

        ArrayTabulatedFunction testingIteratorWhileB = testingArrayFunction();
        i = 0;
        for (Point myPoint : testingIteratorWhileB) {
            assertEquals(myPoint.x, testingIteratorWhileB.getX(i), DELTA);
            assertEquals(myPoint.y, testingIteratorWhileB.getY(i++), DELTA);
        }
        assertEquals(testingIteratorWhileB.getCount(), i);
    }

    @Test
    public void testIterator() {
        final double DELTA = 0.001;
        Iterator<Point> iterator = testingArrayFunction().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(point.x, testingArrayFunction().getX(i), DELTA);
            assertEquals(point.y, testingArrayFunction().getY(i++), DELTA);
        }
        System.out.println(i);
        i = 0;
        for (Point point : testingArrayFunction()) {
            assertEquals(point.x, testingArrayFunction().getX(i), DELTA);
            assertEquals(point.y, testingArrayFunction().getY(i++), DELTA);
        }
        System.out.println(i);
        assertThrows(NoSuchElementException.class, iterator::next);
    }


}