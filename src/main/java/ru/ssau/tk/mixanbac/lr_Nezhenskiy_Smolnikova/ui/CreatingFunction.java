package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import javax.swing.*;

public class CreatingFunction extends JFrame {
    public static void main(String[] args) {

        JFrame startFrame = new JFrame("Наш интерфейс");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Установим константы по определению при нажатии крестика, для закрытия приложения
        startFrame.pack();//Фрейм по размерам будет занимать столько, сколько и его компоненты
        startFrame.setLocationRelativeTo(null);//Позиция по центру
        startFrame.setVisible(true);//Окно видимое
    }
}
