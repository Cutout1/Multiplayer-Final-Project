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

public class LobbyGUI extends Thread implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	
	private JButton serverButton;
	private JButton clientButton;
	
	private Client client;
	
	public LobbyGUI(Client client1) throws IOException {
		client = client1;
	}

	public void run() {
		frame = new JFrame("Game Lobby");
		frame.setBounds(0,0,500,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		addButtons();
		frame.setVisible(true);
		panel.setBackground(Color.CYAN);
	}
	
	private void addButtons() {
		serverButton = new JButton("Rock Paper Scissors");
		serverButton.setBounds(100, 100, 150, 100);
		serverButton.setForeground(Color.BLUE);
		serverButton.setActionCommand("RPS");
		serverButton.addActionListener(this);
		panel.add(serverButton);
		
		clientButton = new JButton("Other Game");
		clientButton.setBounds(300, 100, 150, 100);
		clientButton.setForeground(Color.BLUE);
		clientButton.setActionCommand("ClientButton");
		clientButton.addActionListener(this);
		panel.add(clientButton);
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if(command.equals("RPS")) {
			client.send("SRPS");
			client.setWaitingRPS(true);
		}
	}
}
