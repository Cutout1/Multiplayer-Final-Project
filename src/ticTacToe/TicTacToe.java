package ticTacToe;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Client;

public class TicTacToe extends Thread implements MouseListener, KeyListener {

	// 0 is blank, 1 is X, 2 is O
	private int[][] points = new int[3][3];

	private TTTDraw TTTD;
	private JFrame frame;

	private boolean isX;
	private Client client;
	private String otherUser;
	private boolean myTurn;

	public TicTacToe(boolean X, String otherPlayer, Client client1) {
		client = client1;
		otherUser = otherPlayer;
		isX = X;
		myTurn = X;
		// put in 0s
		for (int i = 0; i < 3; i++) {
			for (int n = 0; n < 3; n++) {
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

		TTTD.addMouseListener(this);
		frame.addKeyListener(this);

		TTTD.updatePoints(points);
		TTTD.repaint();
	}
	
	//when you click a cell:
	private void click(int x, int y) {
		//cell is empty and you are X
		if (isX && points[x][y] == 0) {
			points[x][y] = 1;
			myTurn = false;
			client.send("TTT " + x + " " + y);
			if (checkWinner(1)) {
				TTTD.updatePoints(points);
				TTTD.repaint();
				JOptionPane.showMessageDialog(null, "You made a row of 3, so you win! :)");
				client.sendWinMessage("Tic Tac Toe");
				client.otherPlayer = null;
				client.currentGame = null;
				client.showLobby();
				frame.dispose();
			}
		//cell is empty and you are O
		} else if (points[x][y] == 0) {
			points[x][y] = 2;
			myTurn = false;
			client.send("TTT " + x + " " + y);
			if (checkWinner(2)) {
				TTTD.updatePoints(points);
				TTTD.repaint();
				JOptionPane.showMessageDialog(null, "You made a row of 3, so you win! :)");
				client.sendWinMessage("Tic Tac Toe");
				client.otherPlayer = null;
				client.currentGame = null;
				client.showLobby();
				frame.dispose();
			}
		}
		TTTD.updatePoints(points);
		TTTD.repaint();

	}

	//when other player makes a move:
	public void otherPlayerClick(int x, int y) {
		//other player is O
		if (isX) {
			points[x][y] = 2;
			myTurn = true;
			if (checkWinner(2)) {
				TTTD.updatePoints(points);
				TTTD.repaint();
				JOptionPane.showMessageDialog(null, otherUser + " made a row of 3, so you lose");
				client.otherPlayer = null;
				client.showLobby();
				client.currentGame = null;
				frame.dispose();
			}
		//other player is X
		} else {
			points[x][y] = 1;
			myTurn = true;
			if (checkWinner(1)) {
				TTTD.updatePoints(points);
				TTTD.repaint();
				JOptionPane.showMessageDialog(null, otherUser + " made a row of 3, so you lose");
				client.otherPlayer = null;
				client.showLobby();
				client.currentGame = null;
				frame.dispose();
			}
		}
		TTTD.updatePoints(points);
		TTTD.repaint();
	}

	public boolean checkWinner(int xOrO) {
		// checks rows and columns
		for (int x = 0; x < 3; x++) {
			if (points[x][0] == xOrO && points[x][1] == xOrO && points[x][2] == xOrO) {
				return true;
			}
			if (points[0][x] == xOrO && points[1][x] == xOrO && points[2][x] == xOrO) {
				return true;
			}
		}
		// checks diagonals
		if ((points[0][0] == xOrO && points[1][1] == xOrO && points[2][2] == xOrO)
				|| (points[2][0] == xOrO && points[1][1] == xOrO && points[0][2] == xOrO)) {
			return true;
		}
		boolean fullBoard = true;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (points[x][y] == 0) {
					fullBoard = false;
				}
			}
		}
		if (fullBoard == true) {
			JOptionPane.showMessageDialog(null, "The board is full and there was no winner, so it's a tie!");
			client.otherPlayer = null;
			client.showLobby();
			client.currentGame = null;
			frame.dispose();
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

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

		System.out.println("clicked");
		int x = arg0.getX();
		int y = arg0.getY();

		// first column
		if (myTurn == true) {
			if (0 < x && x < 100) {
				if (0 < y && y < 100) {
					// top left
					click(0, 0);
				}
				if (100 < y && y < 200) {
					// mid left
					click(0, 1);
				}
				if (200 < y && y < 300) {
					// bottom left
					click(0, 2);
				}
			}
			// second column
			if (100 < x && x < 200) {
				if (0 < y && y < 100) {
					// mid top
					click(1, 0);
				}
				if (100 < y && y < 200) {
					// mid mid
					click(1, 1);
				}
				if (200 < y && y < 300) {
					// mid bottom
					click(1, 2);
				}
			}
			// third column
			if (200 < x && x < 300) {
				if (0 < y && y < 100) {
					// right top
					click(2, 0);
				}
				if (100 < y && y < 200) {
					// right mid
					click(2, 1);
				}
				if (200 < y && y < 300) {
					// right bottom
					click(2, 2);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent key) {
		int keycode = key.getKeyCode();
		if (myTurn == true) {
			if (keycode == KeyEvent.VK_1) {
				click(0, 0);
			}
			if (keycode == KeyEvent.VK_2) {
				click(1, 0);
			}
			if (keycode == KeyEvent.VK_3) {
				click(2, 0);
			}
			if (keycode == KeyEvent.VK_4) {
				click(0, 1);
			}
			if (keycode == KeyEvent.VK_5) {
				click(1, 1);
			}
			if (keycode == KeyEvent.VK_6) {
				click(2, 1);
			}
			if (keycode == KeyEvent.VK_7) {
				click(0, 2);
			}
			if (keycode == KeyEvent.VK_8) {
				click(1, 2);
			}
			if (keycode == KeyEvent.VK_9) {
				click(2, 2);
			}
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
