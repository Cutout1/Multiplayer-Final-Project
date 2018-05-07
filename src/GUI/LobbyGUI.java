package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.Client;
import main.Server;
import snakeBikes.SnakeBikes;

public class LobbyGUI extends Thread implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	
	private JButton RPS;
	private JButton TTT;
	private JButton SB;
	
	private Client client;
	
	public SnakeBikes snakeBikes;
	
	public LobbyGUI(Client client1) throws IOException {
		client = client1;
	}

	public void run() {
		frame = new JFrame("Game Lobby");
		frame.setBounds(0,0,700,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		addButtons();
		frame.setVisible(true);
		panel.setBackground(Color.CYAN);
	}
	
	private void addButtons() {
		RPS = new JButton("Rock Paper Scissors");
		RPS.setBounds(100, 100, 150, 100);
		RPS.setForeground(Color.BLUE);
		RPS.setActionCommand("RPS");
		RPS.addActionListener(this);
		panel.add(RPS);
		
		TTT = new JButton("Other Game");
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
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if(command.equals("RPS")) {
			client.send("SRPS");
			client.setWaitingRPS(true);
		}
		if(command.equals("SB")) {
			snakeBikes = new SnakeBikes();
			snakeBikes.start();
		}
	}
}
