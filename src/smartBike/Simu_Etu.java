package smartBike;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;


public class Simu_Etu {

	public static void main(String[] args) throws IOException
	{
		JFrame frame = new JFrame("Bike Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Config config = new Config();
        MyCity city = new MyCity(config);

        frame.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(city);
        frame.pack();
        frame.setVisible(true);
	}
}
