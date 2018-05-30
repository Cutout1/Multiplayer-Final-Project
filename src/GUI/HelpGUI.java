//Class Description: Basic GUI with a textArea to show the game instructions

package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lobby.Lobby;
import main.Client;
import main.Server;
public class HelpGUI {
	
	private JFrame frame;
	private JPanel panel;
	
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	public HelpGUI() {
		initialize();
	}
	//initializes everything
	private void initialize() {
		frame = new JFrame("Game Help");
		frame.setBounds(0,0,700,600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 700, 600);
		scrollPane.setBackground(Color.GRAY);
		panel.add(scrollPane);
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		//Adds the instructions to the textArea
		textArea.append("Starting and joining your server:\n" + 
				" - Open the game\n" + 
				" - The host (1 person) should select “Launch Server”\n" + 
				" - All people joining the server, including the host should press “Launch Client”\n" + 
				" - The program will prompt you for an IP, the host’s server program (red) should provide it\n" + 
				" - Once everyone has connected to the server, you may use our chat service to communicate with others.\n" + 
				" - The game lobby will also open, all games require 2 players to play. \n" + 
				" - To join a game with another player, all you have to do is have both players connected to the same server, \nthen have both players click on the arcade game of the game they want to play.\n" + 
				"\n" + 
				"Battleships:\n" + 
				"\n" + 
				"Instructions for battleship appear in the game.\n" + 
				"\n" + 
				"Tic tac toe:\n" + 
				"\n" + 
				"A message will come up telling you if you are first or second when the game begins. Simply click on the square\n you want to put your letter in on your turn then wait for the other player to take his or her turn.\n" + 
				"\n" + 
				"Rock paper scissors:\n" + 
				"\n" + 
				"You and your opponent will have the choice of rock, paper or scissors. Rock beats scissors. \nPaper beats rock. Scissors beat paper.\n" + 
				"\n" + 
				"Snake bikes:\n" + 
				"\n" + 
				"Not Yet Available\n" + 
				"\n" +
				"You can leave these instructions open while playing the game, it won't affect anything");
		frame.setVisible(true);
		panel.setBackground(Color.WHITE);
	}
}
	