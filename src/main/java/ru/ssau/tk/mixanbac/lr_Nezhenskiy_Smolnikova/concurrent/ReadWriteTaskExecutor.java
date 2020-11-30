package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.concurrent;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.ZeroFunction;

import java.util.ArrayList;

public class ReadWriteTaskExecutor {public static void main(String[] args) throws InterruptedException {
    TabulatedFunction function = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
    ArrayList<Thread> list = new ArrayList<>();
    ReadWriteTask myTask = new ReadWriteTask(function);
    for (int i = 0; i < 20; i++){
        list.add(new Thread(myTask));
    }
    for (Thread thread: list){
        thread.start();
    }
    Thread.sleep(2_000);
    System.out.println(function.toString());
}
}
