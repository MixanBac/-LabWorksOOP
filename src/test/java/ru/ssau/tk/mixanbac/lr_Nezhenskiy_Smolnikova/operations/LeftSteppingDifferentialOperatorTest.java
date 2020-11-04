package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.SqrFunction;

import static org.testng.Assert.*;
import static ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedDifferentialOperatorTest.DELTA;

public class LeftSteppingDifferentialOperatorTest {

    private final static double STEP=0.01;
    @Test
    public void testDerive() {
        SteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(STEP);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 1.990, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 5.98999, DELTA);
    }
}