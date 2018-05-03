package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.Server;

public class TicTacToeGUI implements ActionListener {
	private JFrame frame;
	private JPanel panel;
	
	private JButton serverButton;
	private JButton clientButton;
	
	public TicTacToeGUI() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("Tic Tac Toe");
		frame.setBounds(0,0,1000,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		addButtons();
		frame.setVisible(true);
		panel.setBackground(Color.CYAN);
	}
	
	private void addButtons() {
		serverButton = new JButton("Launch Server");
		serverButton.setBounds(100, 100, 150, 100);
		serverButton.setForeground(Color.BLUE);
		serverButton.setActionCommand("ServerButton");
		serverButton.addActionListener(this);
		panel.add(serverButton);
		
		clientButton = new JButton("Launch Client");
		clientButton.setBounds(300, 100, 150, 100);
		clientButton.setForeground(Color.BLUE);
		clientButton.setActionCommand("ClientButton");
		clientButton.addActionListener(this);
		panel.add(clientButton);
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if(command.equals("ServerButton")) {
			System.out.println("Asking for Server Port...");
			//launch server
			 String port = JOptionPane.showInputDialog(
		            frame,
		            "Enter port for the Server:",
		            "Launching Server",
		            JOptionPane.QUESTION_MESSAGE);
			
			try {
			System.out.println("Port entered: "+port);
		//	Server.startServer(Integer.parseInt(port));
			}catch(Exception e) {
				//catch for non numbers entered
				JOptionPane.showMessageDialog(frame, "Plese enter an actual number", "Error", JOptionPane.ERROR_MESSAGE);	
			}
		}
		if(command.equals("ClientButton")) {
			System.out.println("Launching Client...");
			//launch client
			ClientConnectGUI clientGUI = new ClientConnectGUI();
			
		}
		
	}

}

