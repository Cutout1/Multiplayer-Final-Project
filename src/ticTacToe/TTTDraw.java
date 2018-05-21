package ticTacToe;


import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
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
	
	private int[][] points;
	
	   public TTTDraw() {
		   
		   super();
		   
		/*   try {
			   image = ImageIO.read(new File("file path"));
		   }catch(Exception e) { e.printStackTrace(); }*/
		   
		    setSize(300, 300);
		    setBackground(Color.green);
	   }
	   public void updatePoints(int[][] thePoints) {
		   points = thePoints;
		   System.out.println(points);
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
		   
		   //draw Xs and Os
		   g2d.setStroke(new BasicStroke(30));
		   
		   //numbers
		   int count = 1;
		   for(int x = 0; x < 3; x++) {
			   for(int y = 0; y < 3; y++) {
				   g2d.drawString(""+count, (x*100)+10, (y*100)+15);
				   count++;
			   }
		   }
		   
		   for(int x = 0; x < 3; x++) {
			   for(int y = 0; y < 3; y++) {
				   if(points[x][y] == 1) {
					   //X
					   g2d.drawString("X", (x*100)+50, (y*100)+50);
				   }
				   else if(points[x][y] == 2) {
					   //O
					   g2d.drawString("O", (x*100)+50, (y*100)+50);
				   }
			   }
		   }
	   }
}




