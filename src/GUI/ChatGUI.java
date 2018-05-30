package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import main.Client;
import sound.Sound;

public class ChatGUI extends Thread implements ActionListener {
	
	private JFrame frame;
	private JPanel panel;
	
	private JTextArea messages;
	private JScrollPane scroll;
	
	private JButton send;
	private JTextField sendBox;
	
	private JButton rps;
	
	private JButton ttt;
	
	private JButton sb;
	
	private JButton bs;
	
	private Client client;
	
	public ChatGUI(Client theClient) {
		client = theClient;
	}
	
	public void run() {
		initialize();
	}
	
	private void initialize(){
		frame = new JFrame("Server Chat");
		frame.setBounds(0,0,500,670);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		addComponents();
		frame.setVisible(true);
		panel.setBackground(Color.CYAN);

	}
	
	private void addComponents() {
		scroll = new JScrollPane();
		scroll.setBounds(20, 20, 440, 480);
		scroll.setBackground(Color.GRAY);
		panel.add(scroll);
		messages = new JTextArea();
		messages.setEditable(false);
		scroll.setViewportView(messages);
		
		sendBox = new JTextField();
		sendBox.setBounds(20, 500, 350, 50);
		sendBox.setForeground(Color.GRAY);
		panel.add(sendBox);
		
		send = new JButton("Send");
		send.setBounds(370, 500, 90, 50);
		send.setForeground(Color.BLUE);
		send.setActionCommand("send");
		send.addActionListener(this);
		panel.add(send);
		
		rps = new JButton("Want to play RPS?");
		rps.setBounds(15, 550, 140, 40);
		rps.setForeground(Color.BLUE);
		rps.setActionCommand("rpsPlay");
		rps.addActionListener(this);
		panel.add(rps);
		
		ttt = new JButton("Want to play TTT?");
		ttt.setBounds(155, 550, 140, 40);
		ttt.setForeground(Color.BLUE);
		ttt.setActionCommand("tttPlay");
		ttt.addActionListener(this);
		panel.add(ttt);
		
		sb = new JButton("Want to play Snake Bikes?");
		sb.setBounds(295, 550, 170, 40);
		sb.setForeground(Color.BLUE);
		sb.setActionCommand("sbPlay");
		sb.addActionListener(this);
		panel.add(sb);
		
		bs = new JButton("Want to play Battleships?");
		bs.setBounds(15, 590, 195, 40);
		bs.setForeground(Color.BLUE);
		bs.setActionCommand("bsPlay");
		bs.addActionListener(this);
		panel.add(bs);
	}
	
	public void print(String msg) {
		messages.setText(messages.getText()+"\n"+msg);
		panel.setBackground(Color.GREEN);
		messages.updateUI();
		System.out.println(msg);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if(command.equals("send")) {
			//send message in sendBox field
			client.send("CHAT "+sendBox.getText());
			sendBox.setText("");
		}
		
		if(command.equals("rpsPlay")) {
			//send message in sendBox field
			client.send("CHAT "+ "Want to play RPS?");
		}
		
		if(command.equals("tttPlay")) {
			//send message in sendBox field
			client.send("CHAT "+ "Want to play TTT?");
		}
		
		if(command.equals("sbPlay")) {
			//send message in sendBox field
			client.send("CHAT "+ "Want to play Snake Bikes?");
		}
		
		if(command.equals("bsPlay")) {
			//send message in sendBox field
			client.send("CHAT "+ "Want to play Battleships?");
		}
	}
}
	
