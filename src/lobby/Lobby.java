package lobby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.Client;
import main.Server;
import snakeBikes.SnakeBikes;
import ticTacToe.TTTDraw;
import ticTacToe.TicTacToe;
import battleShips.BattleShips;

public class Lobby extends Thread implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	
	private JButton RPS;
	private JButton TTT;
	private JButton SB;
	private JButton BS;
	
	private ImageIcon background;
	
	private Client client;
	
	public SnakeBikes snakeBikes;
	public BattleShips battleShips;
	
	private LobbyDraw LD;
	
	public Lobby(Client client1) throws IOException {
		client = client1;
	}

	public void run() {
		
		LD = new LobbyDraw();
		frame = new JFrame("Game Lobby");
		panel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addButtons();
		panel.add(LD);
		//frame.add(panel);
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.pack();
		
		client.setLobby(this);
		
		frame.setVisible(true);

		LD.repaint();
		
		/*
		frame = new JFrame("Game Lobby");
		frame.setBounds(0,0,900,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		background = new ImageIcon("assets/titlescreenAPCS.png");
		
		addButtons();
		
		client.setLobby(this);
		frame.setVisible(true);
		*/
		
		
	}
	
	private void addButtons() {
		RPS = new JButton("Rock Paper Scissors");
		RPS.setBounds(100, 100, 150, 100);
		RPS.setForeground(Color.BLUE);
		RPS.setActionCommand("RPS");
		RPS.addActionListener(this);
		panel.add(RPS);
		
		TTT = new JButton("Tic Tac Toe");
		TTT.setBounds(300, 100, 150, 100);
		TTT.setForeground(Color.BLUE);
		TTT.setActionCommand("TTT");
		TTT.addActionListener(this);
		panel.add(TTT);
		
		SB = new JButton("Snake Bikes");
		SB.setBounds(500, 100, 150, 100);
		SB.setForeground(Color.BLUE);
		SB.setActionCommand("SB");
		SB.addActionListener(this);
		panel.add(SB);
		
		BS = new JButton("Battle Ships");
		BS.setBounds(700, 100, 150, 100);
		BS.setForeground(Color.BLUE);
		BS.setActionCommand("BS");
		BS.addActionListener(this);
		panel.add(BS);
	}
	
	public void hide() {
		frame.hide();
	}
	
	public void show() {
		frame.show();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if(command.equals("RPS")) {
			client.send("SRPS");
			client.setWaitingRPS(true);
		}
		if(command.equals("SB")) {
			snakeBikes = new SnakeBikes(client);
			snakeBikes.start();
		}
		if(command.equals("TTT")) {
			client.send("STTT");
			client.setWaitingTTT(true);
		}
		if(command.equals("BS")) {
			battleShips = new BattleShips();
			battleShips.start();
		}
	}
}