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
import sound.Sound;
import ticTacToe.TTTDraw;

public class BattleShips extends Thread implements MouseListener, KeyListener {
	
	//0 is blank, 1 is ship, 2 is ship that was hit, 3 is an enemy miss
	private int[][] myPoints = new int[10][10];
	
	//0 is blank, 1 is miss, 2 is hit
	private int[][] opponentPoints = new int[10][10];
	
	private BSDraw BSD;
	private JFrame frame;
	private Client client;
	public boolean myTurn;
	public boolean enemyShipsPlaced;
	
	private boolean isVertical;
	private int shipBeingPlaced;
	private boolean preGame;
	private boolean b;
	
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
        BSD.addKeyListener(this);
        
        preGame = true;
        isVertical = true;
        shipBeingPlaced = 5;
        
        BSD.updatePoints(myPoints, opponentPoints, preGame, isVertical, shipBeingPlaced);
        BSD.repaint();
        
        b = true;
        while(b) {
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	BSD.updatePoints(myPoints, opponentPoints, preGame, isVertical, shipBeingPlaced);
        	BSD.repaint();
        }
	}
	
	private void attack(int x, int y) {
		//send coords of attack
		if(opponentPoints[x/30][y/30] == 0) {
			myTurn = false;
			client.send("BS " + (x/30) + " " + (y/30));
			
			BSD.updatePoints(myPoints, opponentPoints, false, isVertical, shipBeingPlaced);
	        BSD.repaint();
		}
	}
	
	public void recieveAttack(int x, int y) {
		if(myPoints[x][y] == 0) {
			myPoints[x][y] = 3;
			client.send("BSMISS " + x + " " + y);
			myTurn = true;
			BSD.updatePoints(myPoints, opponentPoints, false, isVertical, shipBeingPlaced);
	        BSD.repaint();
	        Sound.MISS.play();
		}
		if(myPoints[x][y] == 1) {
			myPoints[x][y] = 2;
			client.send("BShipHit " + x + " " + y);
			myTurn = true;
			BSD.updatePoints(myPoints, opponentPoints, false, isVertical, shipBeingPlaced);
	        BSD.repaint();
	        Sound.HIT.play();
		}
	}
	
	public void recieveAnswer(boolean hit, int x, int y) {
		if(hit) {
			opponentPoints[x][y] = 2;
			BSD.updatePoints(myPoints, opponentPoints, false, isVertical, shipBeingPlaced);
	        BSD.repaint();
			checkWinner();
			Sound.HIT.play();
		} else {
			opponentPoints[x][y] = 1;
			BSD.updatePoints(myPoints, opponentPoints, false, isVertical, shipBeingPlaced);
	        BSD.repaint();
	        Sound.MISS.play();
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
		BSD.updatePoints(myPoints, opponentPoints, false, isVertical, shipBeingPlaced);
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
			BSD.updatePoints(myPoints, opponentPoints, false, isVertical, shipBeingPlaced);
	        BSD.repaint();
		} else {
			//draw miss
			opponentPoints[x][y] = 1;
			BSD.updatePoints(myPoints, opponentPoints, false, isVertical, shipBeingPlaced);
	        BSD.repaint();
		}
	}
	
	private void place(int theX, int theY) {
		int x = theX/30;
		int y = theY/30;
		try {
			   if(shipBeingPlaced>2) {
				   if(isVertical) {
					   if(y+shipBeingPlaced-1<10) {
						   boolean a = true;
						   for(int i=0; i<shipBeingPlaced; i++) {
							   if(myPoints[x][y+i] == 1) {
								   a = false;
							   }
						   }
						   if(a) {
							   for(int i=0; i<shipBeingPlaced; i++) {
								   myPoints[x][y+i] = 1;
							   }
							   shipBeingPlaced--;
						   }
					   }   
				   } else {
					   if(x+shipBeingPlaced-1<10) {
						   boolean a = true;
						   for(int i=0; i<shipBeingPlaced; i++) {
							   if(myPoints[x+i][y] == 1) {
								   a = false;
							   }
						   }
						   if(a) {
							   for(int i=0; i<shipBeingPlaced; i++) {
								   myPoints[x+i][y] = 1;
							   }
							   shipBeingPlaced--;
						   }
					   }
				   }
			   } else if(shipBeingPlaced>0) {
				   if(isVertical) {
					   if(y+shipBeingPlaced<10) {
						   boolean a = true;
						   for(int i=0; i<shipBeingPlaced+1; i++) {
							   if(myPoints[x][y+i] == 1) {
								   a = false;
							   }
						   }
						   if(a) {
							   for(int i=0; i<shipBeingPlaced+1; i++) {
								   myPoints[x][y+i] = 1;
							   }
							   shipBeingPlaced--;
						   }
					   }
				   } else {
					   if(x+shipBeingPlaced<10) {
						   boolean a = true;
						   for(int i=0; i<shipBeingPlaced+1; i++) {
							   if(myPoints[x+i][y] == 1) {
								   a = false;
							   }
						   }
						   if(a) {
							   for(int i=0; i<shipBeingPlaced+1; i++) {
								   myPoints[x+i][y] = 1;
							   }
							   shipBeingPlaced--;
						   }
					   }
				   }
			   }
	   }catch(ArrayIndexOutOfBoundsException e) {}
	   if(shipBeingPlaced==0) {
		   preGame = false;
		   b = false;
		   pointsLeft = 0;
	   }
	   BSD.updatePoints(myPoints, opponentPoints, preGame, isVertical, shipBeingPlaced);
	   BSD.repaint();
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

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		System.out.println("key pressed");
		if(code==KeyEvent.VK_R) {
			System.out.println("rotated");
			if(isVertical) {
				isVertical = false;
			} else {
				isVertical = true;
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
