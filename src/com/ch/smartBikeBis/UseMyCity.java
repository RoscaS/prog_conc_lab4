package com.ch.smartBikeBis;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class UseMyCity {

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Bike Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyCity city = new MyCity();

        frame.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(city);
        frame.pack();
        frame.setVisible(true);
    }
}
