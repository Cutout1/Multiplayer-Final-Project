package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerGUI implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	public ServerGUI() {
		initialize();
	}
	
	private void initialize(){
		frame = new JFrame("Cool Game Server");
		frame.setBounds(0,0,500,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		addComponents();
		frame.setVisible(true);
		panel.setBackground(Color.CYAN);
	}
	
	private void addComponents() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 460, 660);
		scrollPane.setBackground(Color.GRAY);
		panel.add(scrollPane);
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}
	public void print(String msg) {
		textArea.setText(textArea.getText()+"\n"+msg);
		panel.setBackground(Color.RED);
		textArea.updateUI();
		System.out.println(msg);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
