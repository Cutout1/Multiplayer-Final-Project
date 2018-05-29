package battleShips;

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

public class BSDraw extends Canvas {

	private JFrame frame;
	private Image image;
	private boolean[][] points;
	private int[][] opponentPoints;
	
	   public BSDraw() {
		   
		   super();
		   
		/*   try {
			   image = ImageIO.read(new File("file path"));
		   }catch(Exception e) { e.printStackTrace(); }*/
		   
		    setSize(800, 800);
		    setBackground(Color.white);
	   }
	   
	   public void setPoints() {
	   }
	   public void paint(Graphics g) {
		   Graphics2D g2d = (Graphics2D) g;
		   
		   //draw opponent grid
		   g2d.setColor(Color.GRAY);
		   g2d.setStroke(new BasicStroke(5));
		   
		   for(int i = 0; i<=10; i++) {
			   g2d.drawLine( 50 + i*30, 50, 50 + i*30, 350);
			   g2d.drawLine( 50, 50 + i*30, 350, 50 + i*30);
		   }
		   
		   //draw your grid
		   g2d.setColor(Color.black);
		   g2d.setStroke(new BasicStroke(5));
		   for(int i = 0; i<=10; i++) {
			   g2d.drawLine( 450 + i*30, 450, 450 + i*30, 750);
			   g2d.drawLine( 450, 450 + i*30, 750, 450 + i*30);
		   }
		   
		   //label grids
		   char ch = 'A';
		   int num = 1;
		   for(int i = 0; i<10; i++) {
			   g2d.drawString(""+num, 30, 60 + 30*i);
			   g2d.drawString(""+ch, 60 + 30*i, 30);
			   
			   g2d.drawString(""+num, 430, 460 + 30*i);
			   g2d.drawString(""+ch, 460 + 30*i, 430);
			   
			   num++;
			   ch++;
		   }
		   //draw my ships
		   g2d.setColor(Color.GRAY);
		   for(int r=0; r<10; r++) {
			   for(int c=0; c<10; c++) {
				   if(points[r][c]) {
					   g2d.drawOval(450 + r*30, 450 + c*30, 30, 30);
				   }
			   }
		   }
		   
		   //draw opponent points
		   for(int r=0; r<10; r++) {
			   for(int c=0; c<10; c++) {
				   if(opponentPoints[r][c]==1) {
					   //miss
					   g2d.setColor(Color.BLUE);
					   g2d.drawOval(50 + r*30, 50 + c*30, 30, 30);
				   }
				   else if(opponentPoints[r][c]==2) {
					   //hit
					   g2d.setColor(Color.RED);
					   g2d.drawOval(50 + r*30, 50 + c*30, 30, 30);
				   }
			   }
		   }
		   
	   }

	public void updatePoints(boolean[][] thePoints, int[][] theOpponentPoints) {
		points = thePoints;
		opponentPoints = theOpponentPoints;
	}
}
