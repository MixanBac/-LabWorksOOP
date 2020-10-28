package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.Point;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point myPoint : tabulatedFunction) {
            points[i++] = myPoint;
        }
        return points;
    }

}
