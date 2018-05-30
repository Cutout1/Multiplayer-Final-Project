package battleShips;

import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import main.Client;
import ticTacToe.TTTDraw;

public class BattleShips extends Thread implements MouseListener {
	
	//0 is blank, 1 is ship, 2 is ship that was hit, 3 is an enemy miss
	private int[][] myPoints = new int[10][10];
	
	//0 is blank, 1 is miss, 2 is hit
	private int[][] opponentPoints = new int[10][10];
	
	private BSDraw BSD;
	private JFrame frame;
	private Client client;
	public boolean myTurn;
	public boolean enemyShipsPlaced;
	
	//how many points you have left to place
	private int pointsLeft;
	
	public BattleShips(Client c1) {
		client = c1;
		myTurn = false;
		enemyShipsPlaced = false;
		pointsLeft = 17;
	}
	
	public void run() {
		BSD = new BSDraw();
        frame = new JFrame("Battle Ships");
        frame.add(BSD);
        frame.pack();
        frame.setVisible(true);
        
        BSD.addMouseListener(this);
        
        //myPoints[2][2] = 2;
        
        BSD.updatePoints(myPoints, opponentPoints, true);
        BSD.repaint();
	}
	
	private void attack(int x, int y) {
		//send coords of attack
		if(opponentPoints[x/30][y/30] == 0) {
			myTurn = false;
			client.send("BS " + (x/30) + " " + (y/30));
			
			BSD.updatePoints(myPoints, opponentPoints, false);
	        BSD.repaint();
		}
	}
	
	public void recieveAttack(int x, int y) {
		if(myPoints[x][y] == 0) {
			myPoints[x][y] = 3;
			client.send("BSMISS " + x + " " + y);
			myTurn = true;
			BSD.updatePoints(myPoints, opponentPoints, false);
	        BSD.repaint();
		}
		if(myPoints[x][y] == 1) {
			myPoints[x][y] = 2;
			client.send("BShipHit " + x + " " + y);
			myTurn = true;
			BSD.updatePoints(myPoints, opponentPoints, false);
	        BSD.repaint();
		}
	}
	
	public void recieveAnswer(boolean hit, int x, int y) {
		if(hit) {
			opponentPoints[x][y] = 2;
			BSD.updatePoints(myPoints, opponentPoints, false);
	        BSD.repaint();
			checkWinner();
		} else {
			opponentPoints[x][y] = 1;
			BSD.updatePoints(myPoints, opponentPoints, false);
	        BSD.repaint();
		}
	}
	
	public void checkWinner() {
		int hits = 0;
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 10; y++) {
				if(opponentPoints[x][y] == 2) {
					hits++;
				}
			}
		}
		if (hits == 17) {
			JOptionPane.showMessageDialog(null, "You sunk all of your enemy's ships! You Win!");
			client.sendWinMessage("Battle Ships");
			client.send("BSYOULOOSEHAHA");
			client.otherPlayer = null;
			client.showLobby();
			client.currentGame = null;
			frame.dispose();
		}
	}
	
	public void setTurn(boolean t) {
		myTurn = t;
		BSD.updatePoints(myPoints, opponentPoints, false);
        BSD.repaint();
	}
	
	public void loser() {
		JOptionPane.showMessageDialog(null, "All your ships werer sunk! You Loose!");
		client.otherPlayer = null;
		client.showLobby();
		client.currentGame = null;
		frame.dispose();
	}
	
	//place information received as the result of the attack on the opponent
	public void recieveInfo(int x, int y, boolean shipThere) {
		if(shipThere) {
			//draw hit
			opponentPoints[x][y] = 2;
			BSD.updatePoints(myPoints, opponentPoints, false);
	        BSD.repaint();
		} else {
			//draw miss
			opponentPoints[x][y] = 1;
			BSD.updatePoints(myPoints, opponentPoints, false);
	        BSD.repaint();
		}
	}
	
	private void place(int x, int y) {
		if(myPoints[x/30][y/30] == 0) {
			myPoints[x/30][y/30] = 1;	
			pointsLeft--;
			
			BSD.updatePoints(myPoints, opponentPoints, pointsLeft>0);
	        BSD.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(pointsLeft>0) {
			if(x>=450 && x<=750 && y>=450 && y<=750) {
				place(x-450, y-450);
				if(pointsLeft == 0) {
					client.send("BSDONE");
					if(enemyShipsPlaced) {
						myTurn = true;
						JOptionPane.showMessageDialog(null, "Your turn! Click in the upper left grid to attack!");
					}
				}
			}
		} else if (myTurn){
			if(x>=50 && x<=350 && y>=50 && y<=350) {
				attack(x-50, y-50);
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

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
