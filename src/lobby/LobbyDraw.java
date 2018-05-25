package lobby;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class LobbyDraw extends Canvas {

	private JFrame frame;
	private Image image;
	private int time;
	
	private int[][] points;
	
	   public LobbyDraw() {
		   
		   super();
		   
		   try {
			   image = ImageIO.read(new File("assets/titlescreenAPCS.png"));
		   }catch(Exception e) { e.printStackTrace(); }
		   
		    setSize(1000, 600);
		    setBackground(Color.green);
	   }
	   public void updatePoints(int[][] thePoints) {
		   points = thePoints;
		   System.out.println(points);
	   }
	   
	   public void paint(Graphics g) {
		   Graphics2D g2d = (Graphics2D) g;
		   
		   g2d.drawImage(image, 0, 0, this);
	   }
}




