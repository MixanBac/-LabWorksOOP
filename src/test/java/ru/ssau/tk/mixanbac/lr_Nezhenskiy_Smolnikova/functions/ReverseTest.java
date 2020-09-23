package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ReverseTest {
    Reverse popy = new Reverse();
    private final static double DELTA = 0.001;
    @Test
    public void testApply() {
        assertEquals(popy.apply(5), 0.2,DELTA);
    }
}


