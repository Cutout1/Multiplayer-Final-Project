package ticTacToe;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class TicTacToe extends Thread implements MouseListener {
	
	//0 is blank, 1 is X, 2 is O
	private int[][] points = new int[3][3];
	
	private double angle = 0.0;
	private int speed = 5;
	
	public void run() {
		TTTDraw TTTD = new TTTDraw();
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.add(TTTD);
        frame.pack();
        frame.setVisible(true);
        
        frame.addMouseListener(this);
        
        TTTD.repaint();
        
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		
		//first column
		if(0 < x && x < 100) {
			if(0 < y && y < 100) {
				//top left
			}
			if(100 < y && y < 200) {
				//mid left
			}
			if(200 < y && y < 300) {
				//bottom left
			}
		}	
		//second column
		if(100 < x && x < 200) {
			if(0 < y && y < 100) {
				//mid top
			}
			if(100 < y && y < 200) {
				//mid mid
			}
			if(200 < y && y < 300) {
				//mid bottom
			}
		}
		//third column
		if(200 < x && x < 300) {
			if(0 < y && y < 100) {
				//right
			}
			if(100 < y && y < 200) {
				
			}
			if(200 < y && y < 300) {
				
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
