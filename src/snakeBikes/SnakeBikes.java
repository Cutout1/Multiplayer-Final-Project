package snakeBikes;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import main.Client;

public class SnakeBikes extends Thread implements KeyListener {
	
	ArrayList<Point> points = new ArrayList();
	
	//angle in radians
	private double angle;
	private int speed = 5;
	
	private Client client;
	
	public SnakeBikes(Client theClient, boolean left) {
		client = theClient;
		if(left == true) {
			angle = 3.14;
		} else {
			angle = 0.0;
		}
	}
	
	public void run() {
		SBDraw SBD = new SBDraw();
        JFrame frame = new JFrame("SnakeBikes");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.add(SBD);
        frame.pack();
        frame.setVisible(true);
        
        frame.addKeyListener(this);
        if (angle == 3.14) {
        	points.add(new Point(100,100));
        } else {
        	points.add(new Point(600, 700));
        }
        
        boolean b = true;
        for(int c=0; b; c++) {
        	try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	if(c%2==1) {
        		points.remove(0);
        	}

        	Point lastPoint = points.get(points.size()-1);
        	int changeX, changeY;
        	changeX = (int) (Math.sin(angle)*speed);
        	changeY = (int) (Math.cos(angle)*speed);
        	Point newPoint = new Point(lastPoint.x-changeX, lastPoint.y-changeY);
        	
        	points.add(newPoint);
        	client.send("SB "+newPoint.x+" "+newPoint.y);
        	
        	int search = 2;
        	
        	//test collision
        	if(points.size()>5) {
	        	Point thePoint = points.get(points.size()-1);
	        	for(int i = points.size()-2; i >= 0; i--) {
	        		Point testPoint = points.get(i);
	        		if((thePoint.x > testPoint.x - search) && (thePoint.x < testPoint.x + search) && (thePoint.y > testPoint.y - search) && (thePoint.x < testPoint.x + search)){
	        		//	b = false;
	        		}
	        	}
        	}
        	//boarder
        	Point newest = points.get(points.size()-1);
        	if(newest.x < 0 || newest.x > 800 || newest.y < 0 || newest.y > 800 ) {
        		b = false;
        	}
        	
        	SBD.setPoints(points, client.opponentPoints, c, angle);
        	SBD.repaint();
        }
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		if(keycode==KeyEvent.VK_A) {
			angle = angle + (Math.PI*0.0625);
		}
		if(keycode==KeyEvent.VK_D) {
			angle = angle - (Math.PI*0.0625);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
