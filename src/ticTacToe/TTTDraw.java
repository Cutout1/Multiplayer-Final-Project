package ticTacToe;


import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TTTDraw extends Canvas {

	private JFrame frame;
	private Image image;
	private int time;
	
	   public TTTDraw() {
		   
		   super();
		   
		/*   try {
			   image = ImageIO.read(new File("file path"));
		   }catch(Exception e) { e.printStackTrace(); }*/
		   
		    setSize(300, 300);
		    setBackground(Color.green);
	   }
	   
	   public void paint(Graphics g) {
		   Graphics2D g2d = (Graphics2D) g;
		   
		   //draw grid
		   g2d.setColor(Color.BLACK);
		   g2d.setStroke(new BasicStroke(10));
		   g2d.drawLine(100, 0, 100, 300);
		   g2d.drawLine(200, 0, 200, 300);
		   g2d.drawLine(0, 100, 300, 100);
		   g2d.drawLine(0, 200, 300, 200);
		   
	   }
}




