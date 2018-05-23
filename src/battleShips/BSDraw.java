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
		   g2d.setColor(Color.red);
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
		   char c = 'A';
		   int num = 1;
		   for(int i = 0; i<10; i++) {
			   g2d.drawString(""+num, 30, 60 + 30*i);
			   g2d.drawString(""+c, 60 + 30*i, 30);
			   
			   g2d.drawString(""+num, 430, 460 + 30*i);
			   g2d.drawString(""+c, 460 + 30*i, 430);
			   
			   num++;
			   c++;
		   }
		   
	   }

	public void updatePoints(int[][] myPoints) {
		// TODO Auto-generated method stub
		
	}
}
