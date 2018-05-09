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
	private int time;
	
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
		   for(int i = 0; i>10; i++) {
			   g2d.drawLine( 100 + i*20, 100, 100 + i*20, 300);
			   g2d.drawLine( 100, 100 + i*20, 300, 100 + i*20);
		   }
		   
		   //draw your grid
		   g2d.setColor(Color.blue);
		   g2d.setStroke(new BasicStroke(5));
		   for(int i = 0; i>10; i++) {
			   g2d.drawLine( 500 + i*20, 500, 500 + i*20, 700);
			   g2d.drawLine( 500, 500 + i*20, 700, 500 + i*20);
		   }
		   
	   }
}
