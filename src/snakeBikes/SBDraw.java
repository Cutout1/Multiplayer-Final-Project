package snakeBikes;

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

public class SBDraw extends Canvas {

	private JFrame frame;
	private Image image;
	private int time;
	
	ArrayList<Point> points = new ArrayList();
	ArrayList<Point> oponentPoints = new ArrayList();
	
	   public SBDraw() {
		   
		   super();
		   
		/*   try {
			   image = ImageIO.read(new File("file path"));
		   }catch(Exception e) { e.printStackTrace(); }*/
		   
		    setSize(800, 800);
		    setBackground(Color.white);
	   }
	   
	   public void setPoints(ArrayList<Point> thePoints, ArrayList<Point> otherPoints, int count) {
		   points = thePoints;
		   time = count;
		   oponentPoints = otherPoints;
	   }
	   public void paint(Graphics g) {
		   Graphics2D g2d = (Graphics2D) g;
		   
		   g2d.setColor(Color.blue);
		   g2d.setStroke(new BasicStroke(5));
		   for(int i=0; i<points.size()-1; i++) {
			   g2d.drawLine(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y);
		   }
		   g2d.setColor(Color.red);
		   for(int i=0; i<oponentPoints.size()-1; i++) {
			   g2d.drawLine(oponentPoints.get(i).x, oponentPoints.get(i).y, oponentPoints.get(i+1).x, oponentPoints.get(i+1).y);
		   }
		   g2d.setColor(Color.MAGENTA);
		   g2d.drawString("Time: "+time+" SB seconds", 10, 10);
	   }
}
