package battleShips;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BSDraw extends Canvas {

	private JFrame frame;
	private Image image;
	private int[][] points;
	private int[][] opponentPoints;
	private boolean preGame;
	private boolean isVertical;
	private int shipBeingPlaced;
	
	   public BSDraw() {
		   
		   super();
		   
		   image = new ImageIcon("src/images/ships.png").getImage();
		   
		    setSize(800, 800);
		    setBackground(Color.white);
	   }
	   
	   public void paint(Graphics g) {
		   Graphics2D g2d = (Graphics2D) g;
		   
		   //draw opponent grid
		   g2d.setColor(Color.GRAY);
		   g2d.setStroke(new BasicStroke(5));
		   
		   //draw ships info
		   g2d.drawImage(image, 400, 50, frame);
		   
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
		   for(int r=0; r<10; r++) {
			   for(int c=0; c<10; c++) {
				   if(points[r][c]==1) {
					   g2d.setColor(Color.GRAY);
					   g2d.drawOval(450 + r*30, 450 + c*30, 30, 30);
				   }
				   else if(points[r][c]==2){
					   g2d.setColor(Color.RED);
					   g2d.drawOval(450 + r*30, 450 + c*30, 30, 30);
				   }
				   else if(points[r][c]==3){
					   g2d.setColor(Color.BLUE);
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
		   
		   g2d.setFont(new Font("Helvetica", Font.PLAIN, 14));
		   g2d.setColor(Color.BLACK);
		   
		   //in game help
		   if(preGame){
			   
			   g2d.drawString("Place your ships by clicking on your grid where you want", 50, 400);
			   g2d.drawString("to place them. To rotate the orientation you want to", 50, 425);
			   g2d.drawString("place the ship press R. There are five ships you will", 50, 450);
			   g2d.drawString("place of lengths 5, 4, 3, 3, and 2.", 50, 475);
		   } else {
			   g2d.setColor(Color.GREEN);
			   g2d.drawString("Click on opponents grid to launch an attack if it becomes", 50, 400);
			   g2d.drawString("a red circle you have sucessfully hit their ship. If it", 50, 425);
			   g2d.drawString("becomes a blue circle you have missed. Last player with", 50, 450);
			   g2d.drawString("ships left wins.", 50, 475);
		   }
		   
		   //labels for grids
		   g2d.setFont(new Font("Helvetica", Font.BOLD, 18));
		   g2d.setColor(Color.RED);
		   g2d.drawString("Opponent's waters:", 50, 15);
		   g2d.setColor(Color.BLUE);
		   g2d.drawString("Your waters:", 450, 415);
		   
		 //hover
		   g2d.setColor(Color.LIGHT_GRAY);
		   if(preGame) {
			   int mouseX = MouseInfo.getPointerInfo().getLocation().x - this.getLocationOnScreen().x;
			   int mouseY = MouseInfo.getPointerInfo().getLocation().y - this.getLocationOnScreen().y;
			   int x = (mouseX-450)/30;
			   int y = (mouseY-450)/30;
			   if(x>=0 && x <=10 && y>=0 && y<=10) {
				   try {
					   if(shipBeingPlaced>2) {
						   if(isVertical) {
							   for(int i=0; i<shipBeingPlaced; i++) {
								   g2d.drawOval(450 + 30*(x), 450 + 30*(y+i), 30, 30);
							   }
						   } else {
							   for(int i=0; i<shipBeingPlaced; i++) {
								   g2d.drawOval(450 + 30*(x+i), 450 + 30*y, 30, 30);
							   }
						   }
					   } else if(shipBeingPlaced>0) {
						   if(isVertical) {
							   for(int i=0; i<shipBeingPlaced+1; i++) {
								   g2d.drawOval(450 + 30*(x), 450 + 30*(y+i), 30, 30);
							   }
						   } else {
							   for(int i=0; i<shipBeingPlaced+1; i++) {
								   g2d.drawOval(450 + 30*(x+i), 450 + 30*y, 30, 30);
							   }
						   }
					   }
			   }catch(ArrayIndexOutOfBoundsException e) {}
			   }
		   }
	   }

	public void updatePoints(int[][] thePoints, int[][] theOpponentPoints, boolean isPreGame, boolean vertical, int shipPlaced) {
		points = thePoints;
		opponentPoints = theOpponentPoints;
		preGame = isPreGame;
		isVertical = vertical;
		shipBeingPlaced = shipPlaced;
	}
}
