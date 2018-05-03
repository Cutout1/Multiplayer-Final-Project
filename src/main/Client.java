package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import GUI.ChatGUI;
import GUI.LobbyGUI;
import GUI.RockPaperScissorsGUI;

public class Client extends Thread{

	public BufferedReader in;
	public PrintWriter out;
	
	public String address;
	public int port;
	public String name;
	
	private ChatGUI chat;
	
	private boolean waitingRPS;
	
	public Client(String serverAddress, int serverPort, String theName) {
		address = serverAddress;
		port = serverPort;
		name = theName;
		waitingRPS = false;
		
		chat = new ChatGUI(this);
		chat.start();
		
		try {
			Socket socket = new Socket(address, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println(name);
	    	send("CHAT " + "Hi, " + name + " here");
		}
		catch (Exception e) {
			System.out.println("Client Exception");
		}
	}

	public void run() {
		try {
			//Socket socket = new Socket(address, port);
			//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	//out = new PrintWriter(socket.getOutputStream(), true);
	    	//out.println(name);
	    	//send("CHAT " + "Hi, " + name + " here");
	    
	    	while(true) {
	    		Thread.sleep(10);
	    		String line = in.readLine();
	    		if(line==null) {
					return;
				}
	    		System.out.println(line);
	    		// process input
	    		StringTokenizer st = new StringTokenizer(line);
	    		if(st.hasMoreTokens()) {
	    			String otherUser = st.nextToken();
	    			if(st.hasMoreTokens()) {
	    				String command = st.nextToken();
	    				if (waitingRPS == true) {
	    					if (command.equals("SRPS") &&  !(otherUser.equals(name))) {
	    						send("CRPS");
	    						RockPaperScissorsGUI rpsGame = new RockPaperScissorsGUI(otherUser);
	    					}
	    					if (command.equals("CRPS")) {
	    						RockPaperScissorsGUI rpsGame = new RockPaperScissorsGUI(otherUser);
	    					}
	    				}
	    				if (command.equals("CHAT")) {
	    					String message = "";
	    					while(st.hasMoreTokens()) {
	    						message = message + " " + st.nextToken();
	    					}
	    					chat.print(otherUser + ": " + message);
	    				}
	    			}
	    		}
	    	}
		}
		catch (Exception e){
			System.out.println("Caught Exception");
		}
	}
	public void send(String str) {
		out.println(name + " " + str);
	}
	public void setWaitingRPS(boolean t) {
		waitingRPS = t;
	}
	
}