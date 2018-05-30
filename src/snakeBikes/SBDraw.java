package snakeBikes;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SBDraw extends Canvas {

	private JFrame frame;
	private Image image;
	private int time;
	private double rad;
	
	ArrayList<Point> points = new ArrayList();
	ArrayList<Point> oponentPoints = new ArrayList();
	
	   public SBDraw() {
		   
		   super();
		   
		   image = new ImageIcon("assets/snakeBikeSprite.jpg").getImage();
		   
		    setSize(800, 800);
		    setBackground(Color.white);
	   }
	   
	   public void setPoints(ArrayList<Point> thePoints, ArrayList<Point> otherPoints, int count, double radians) {
		   points = thePoints;
		   time = count;
		   oponentPoints = otherPoints;
		   rad = radians;
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
		   
		   //draw sprite
		   g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
		            RenderingHints.VALUE_ANTIALIAS_ON);

		   g2d.rotate(-rad, points.get(points.size()-1).x, points.get(points.size()-1).y);
		   g2d.drawImage(image, points.get(points.size()-1).x, points.get(points.size()-1).y, frame);
		   g2d.drawString(""+rad, 100, 100);
	   }
}
