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
public class HelpGUI implements ActionListener {
	
	private JFrame frame;
	private JPanel panel;
	
	private JButton back;
	
	private boolean helpExists;
	
	public HelpGUI(boolean he) {
		helpExists = he;
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("Game Help");
		frame.setBounds(0,0,700,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		addButtons();
		frame.setVisible(true);
		panel.setBackground(Color.WHITE);
	}
	
	private void addButtons() {
		back = new JButton("Return");
		back.setBounds(600, 3, 100, 50);
		back.setForeground(Color.BLUE);
		back.setActionCommand("goBackfHelp");
		back.addActionListener(this);
		panel.add(back);
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if (command.equals("goBackfHelp")) {
			if (helpExists) {
				helpExists = false;
				frame = null; panel = null;
		}
		}
	}
}