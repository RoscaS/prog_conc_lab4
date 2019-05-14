package com.ch.smartBike;

import jdk.nashorn.internal.ir.IfNode;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Set;

/**
 * Entry point.
 */
public class UseMyCity {

    public static void main(String[] args) throws IOException {
        Settings.init(args);
        setFrame();
    }

    /*------------------------------------------------------------------*\
   	|*							Private Methods							*|
   	\*------------------------------------------------------------------*/

    private static void setFrame() throws IOException {
        JFrame frame = new JFrame("Bike Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyCity city = new MyCity();
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(city);
        frame.pack();
        frame.setVisible(true);

    }
}
