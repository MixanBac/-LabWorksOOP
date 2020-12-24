package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class BgPanel extends JPanel {
    public void paintComponent(Graphics g) {
        Image im = null;
        try {
            im = ImageIO.read(new File("C:\\Users\\1\\IdeaProjects\\LR_Nezhenskiy_Smolnikova\\Image\\Розовый.png"));
        } catch (IOException ignored) {
        }
        g.drawImage(im, 0, 0, null);
    }
}
