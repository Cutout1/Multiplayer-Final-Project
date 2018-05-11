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
	
	private TTTDraw TTTD;
	private JFrame frame;

	private boolean isX;
	
	public TicTacToe(boolean X) {
		isX = X;
		//put in 0s
		for(int i=0; i<3; i++) {
			for(int n=0; n<3; n++) {
				points[i][n] = 0;
			}
		}
	}
	
	public void run() {
		TTTD = new TTTDraw();
        frame = new JFrame("Tic Tac Toe");
        frame.add(TTTD);
        frame.pack();
        frame.setVisible(true);
        
        frame.addMouseListener(this);
        
        points[0][0] = 1;
        points[1][1] = 2;
        
        TTTD.updatePoints(points);
        TTTD.repaint();

	}
	
	private void click(int x, int y) {
		if(isX) {
			points[x][y] = 1;
		} else {
			points[x][y] = 2;
		}
		TTTD.updatePoints(points);
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
				click(0, 0);
			}
			if(100 < y && y < 200) {
				//mid left
				click(0, 1);
			}
			if(200 < y && y < 300) {
				//bottom left
				click(0, 2);
			}
		}	
		//second column
		if(100 < x && x < 200) {
			if(0 < y && y < 100) {
				//mid top
				click(1, 0);
			}
			if(100 < y && y < 200) {
				//mid mid
				click(1, 1);
			}
			if(200 < y && y < 300) {
				//mid bottom
				click(1, 2);
			}
		}
		//third column
		if(200 < x && x < 300) {
			if(0 < y && y < 100) {
				//right top
				click(2, 0);
			}
			if(100 < y && y < 200) {
				//right mid
				click(2, 1);
			}
			if(200 < y && y < 300) {
				//right bottom
				click(2, 2);
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
