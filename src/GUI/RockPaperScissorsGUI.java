package GUI;

/*
 * The GUI with buttons for the Rock Paper Scissors Game
 */

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.Client;
import main.Server;

public class RockPaperScissorsGUI implements ActionListener{
	private JFrame frame;
	private JPanel panel;
	
	private JButton rock;
	private JButton paper;
	private JButton scissor;
	private JButton quit;
	private Client client;
	private String myChoice;
	private String otherPlayer;
	private boolean otherPlayerHasMoved;
	private String otherPlayerMove;
	
	BufferedReader in;
	PrintWriter out;
	
	public RockPaperScissorsGUI(String Player2, Client client1) {
		client = client1;
		otherPlayer = Player2;
		otherPlayerHasMoved = false;
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("Rock Paper Scissors");
		frame.setBounds(0,0,750,300);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		addButtons();
		frame.setVisible(true);
		panel.setBackground(Color.ORANGE);
	}
	private void addButtons() {
		//rock button
		rock = new JButton("Rock");
		rock.setBounds(100, 100, 150, 100);
		rock.setForeground(Color.BLUE);
		rock.setActionCommand("Rock");
		rock.addActionListener(this);
		panel.add(rock);
		
		//paper button
		paper = new JButton("Paper");
		paper.setBounds(300, 100, 150, 100);
		paper.setForeground(Color.BLUE);
		paper.setActionCommand("Paper");
		paper.addActionListener(this);
		panel.add(paper);
		
		//scissors button
		scissor = new JButton("Scissors");
		scissor.setBounds(500, 100, 150, 100);
		scissor.setForeground(Color.BLUE);
		scissor.setActionCommand("Scissors");
		scissor.addActionListener(this);
		panel.add(scissor);
		
		//quit button
		quit = new JButton("X");
		quit.setBounds(680, 5, 50, 50);
		quit.setForeground(Color.BLUE);
		quit.setActionCommand("Quit");
		quit.addActionListener(this);
		panel.add(quit);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if(command.equals("Rock")) {
			client.send("ROCK");
			myChoice = "ROCK";
			if (otherPlayerHasMoved) {
				check(otherPlayerMove);
			}
		}
		if(command.equals("Paper")) {
			client.send("PAPER");
			myChoice = "PAPER";
			if (otherPlayerHasMoved) {
				check(otherPlayerMove);
			}
		}
		if(command.equals("Scissors")) {
			client.send("SCISSORS");
			myChoice = "SCISSORS";
			if (otherPlayerHasMoved) {
				check(otherPlayerMove);
			}
		}
		if(command.equals("Quit")) {
			client.otherPlayer = null;
			client.send("RPSQUIT");
			client.showLobby();
			frame.dispose();
		}
	}
	
	public void close() {
		//close game
		client.otherPlayer = null;
		client.showLobby();
		client.currentGame = null;
		frame.dispose();
		JOptionPane.showMessageDialog(null, otherPlayer + " quit, so you win!");
	}
	
	public void check(String otherChoice) {
		if (myChoice == null) {
			//time for your turn, other player has chosen
			JOptionPane.showMessageDialog(null, otherPlayer + " has chosen his move, please pick");
			otherPlayerHasMoved = true;
			otherPlayerMove = otherChoice;
		} else if(myChoice.equals(otherChoice)) {
			//same choice so redo
			JOptionPane.showMessageDialog(null, "You and " + otherPlayer + " have chosen the same move, pick again");
			myChoice = null;
			otherPlayerHasMoved = false;
			otherPlayerMove = null;
		} else if(myChoice.equals("ROCK") && otherChoice.equals("PAPER")) {
			//your rock looses to opponent's paper.
			JOptionPane.showMessageDialog(null, otherPlayer + " chose paper, and you chose rock, so you lose :(");
			client.otherPlayer = null;
			client.showLobby();
			client.currentGame = null;
			frame.dispose();
		} else if(myChoice.equals("ROCK") && otherChoice.equals("SCISSORS")) {
			//your rock beats opponent's scissors.
			JOptionPane.showMessageDialog(null, otherPlayer + " chose scissors, and you chose rock, so you win! :)");
			client.sendWinMessage("Rock Paper Scissors");
			client.otherPlayer = null;
			client.showLobby();
			client.currentGame = null;
			frame.dispose();
		} else if(myChoice.equals("PAPER") && otherChoice.equals("SCISSORS")) {
			//your paper looses to opponent's scissors.
			JOptionPane.showMessageDialog(null, otherPlayer + " chose scissors, and you chose paper, so you lose :(");
			client.otherPlayer = null;
			client.showLobby();
			client.currentGame = null;
			frame.dispose();
		} else if(myChoice.equals("PAPER") && otherChoice.equals("ROCK")) {
			//your paper beats opponent's rock.
			JOptionPane.showMessageDialog(null, otherPlayer + " chose rock, and you chose paper, so you win! :)");
			client.sendWinMessage("Rock Paper Scissors");
			client.otherPlayer = null;
			client.showLobby();
			client.currentGame = null;
			frame.dispose();
		} else if(myChoice.equals("SCISSORS") && otherChoice.equals("PAPER")) {
			//your scissors beats opponent's paper.
			JOptionPane.showMessageDialog(null, otherPlayer + " chose paper, and you chose scissors, so you win! :)");
			client.sendWinMessage("Rock Paper Scissors");
			client.otherPlayer = null;
			client.showLobby();
			client.currentGame = null;
			frame.dispose();
		} else if(myChoice.equals("SCISSORS") && otherChoice.equals("ROCK")) {
			//your scissors looses to opponent's rock
			JOptionPane.showMessageDialog(null, otherPlayer + " chose rock, and you chose scissors, so you lose :(");
			client.otherPlayer = null;
			client.showLobby();
			client.currentGame = null;
			frame.dispose();
		}
	}
}