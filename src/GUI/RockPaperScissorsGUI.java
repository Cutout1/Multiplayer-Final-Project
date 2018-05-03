package GUI;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.Server;

public class RockPaperScissorsGUI implements ActionListener{
	private JFrame frame;
	private JPanel panel;
	
	private JButton rock;
	private JButton paper;
	private JButton scissor;
	
	BufferedReader in;
	PrintWriter out;
	
	public RockPaperScissorsGUI(String otherPlayer) {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("Rock Paper Scissors");
		frame.setBounds(0,0,750,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		addButtons();
		frame.setVisible(true);
		panel.setBackground(Color.ORANGE);
	}
	private void addButtons() {
		rock = new JButton("Rock");
		rock.setBounds(100, 100, 150, 100);
		rock.setForeground(Color.BLUE);
		rock.setActionCommand("Rock");
		rock.addActionListener(this);
		panel.add(rock);
		
		paper = new JButton("Paper");
		paper.setBounds(300, 100, 150, 100);
		paper.setForeground(Color.BLUE);
		paper.setActionCommand("Paper");
		paper.addActionListener(this);
		panel.add(paper);
		
		scissor = new JButton("Scissors");
		scissor.setBounds(500, 100, 150, 100);
		scissor.setForeground(Color.BLUE);
		scissor.setActionCommand("Scissors");
		scissor.addActionListener(this);
		panel.add(scissor);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if(command.equals("Rock")) {
			
			
		}
		if(command.equals("Paper")) {
			
			
		}
		if(command.equals("Paper")) {
			
			
		}
	}
}