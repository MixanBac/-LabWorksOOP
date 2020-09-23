package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {
    UnitFunction pop = new UnitFunction();
    private final static double DELTA = 0.001;
    @Test
    public void testApply() {
        assertEquals(pop.apply(1.111), 1,DELTA);

    }
}

