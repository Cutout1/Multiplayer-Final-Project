package lobby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.Client;
import main.Server;
import snakeBikes.SnakeBikes;
import ticTacToe.TTTDraw;
import ticTacToe.TicTacToe;
import battleShips.BattleShips;

public class Lobby extends Thread implements MouseListener, ActionListener {

	private JFrame frame;
	private LobbyDraw LD;
	
	private JButton RPS;
	private JButton TTT;
	private JButton SB;
	private JButton BS;
	
	private ImageIcon background;
	
	private Client client;
	
	public SnakeBikes snakeBikes;
	public BattleShips battleShips;
	
	public Lobby(Client client1) throws IOException {
		client = client1;
	}

	public void run() {
		
		LD = new LobbyDraw();
		frame = new JFrame("Game Lobby");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//addButtons();
		frame.add(LD);
		//frame.add(panel);
		
		frame.pack();
		
		LD.addMouseListener(this);
		
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
	/*
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
	}*/
	
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("mouse entered "+x+" "+y);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int x = e.getX();
		System.out.println("mouse pressed x = "+x+" y = "+e.getY());
		
		if(x<=250) {
			//battle ships
			battleShips = new BattleShips();
			battleShips.start();
		}
		else if(x<=500) {
			//tic tac toe
			client.send("STTT");
			client.setWaitingTTT(true);
		}
		else if(x<=750) {
			//rock paper scissors
			client.send("SRPS");
			client.setWaitingRPS(true);
		}
		else if(x<=1000) {
			//snake bikes
			snakeBikes = new SnakeBikes(client);
			snakeBikes.start();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
