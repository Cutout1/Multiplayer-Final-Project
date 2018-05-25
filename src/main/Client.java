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
import ticTacToe.TicTacToe;

public class Client extends Thread{

	public BufferedReader in;
	public PrintWriter out;
	
	public String address;
	public final int PORT = 2256;
	public String name;
	
	private ChatGUI chat;
	private Object currentGame;
	private LobbyGUI lobby;
	public String otherPlayer;
	
	private boolean waitingRPS;
	private boolean waitingTTT;
	
	public Client(String serverAddress, String theName) {
		address = serverAddress;
		name = theName;
		waitingRPS = false;
		waitingTTT = false;
		
		chat = new ChatGUI(this);
		chat.start();
		
		try {
			Socket socket = new Socket(address, PORT);
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
	    				if (waitingRPS == true && !(otherUser.equals(name))) {
	    					if (command.equals("SRPS")) {
	    						send("CRPS");
	    						currentGame = new RockPaperScissorsGUI(otherUser, this);
	    						waitingRPS = false;
	    						lobby.hide();
	    						otherPlayer = otherUser;
	    						JOptionPane.showMessageDialog(null, "Game of Rock Paper Scissors has been started with " + otherPlayer);
	    					}
	    					if (command.equals("CRPS")) {
	    						currentGame = new RockPaperScissorsGUI(otherUser, this);
	    						waitingRPS = false;
	    						lobby.hide();
	    						otherPlayer = otherUser;
	    						JOptionPane.showMessageDialog(null, "Game of Rock Paper Scissors has been started with " + otherPlayer);
	    					}
	    				}
	    				if (waitingTTT == true && !(otherUser.equals(name))) {
	    					if (command.equals("STTT")) {
	    						send("CTTT");
	    						currentGame = new TicTacToe(true, otherUser, this);
	    						((TicTacToe)currentGame).start();
	    						waitingTTT = false;
	    						lobby.hide();
	    						otherPlayer = otherUser;
	    						JOptionPane.showMessageDialog(null, "Game of Tic Tac Toe has been started with " + otherPlayer);
	    					}
	    					if (command.equals("CTTT")) {
	    						currentGame = new TicTacToe(false, otherUser, this);
	    						((TicTacToe)currentGame).start();
	    						waitingTTT = false;
	    						lobby.hide();
	    						otherPlayer = otherUser;
	    						JOptionPane.showMessageDialog(null, "Game of Tic Tac Toe has been started with " + otherPlayer);
	    					}
	    				}
	    				if ((command.equals("ROCK") || command.equals("PAPER") || command.equals("SCISSORS")) && otherUser.equals(otherPlayer)) {
	    					((RockPaperScissorsGUI) currentGame).check(command);
	    				}
	    				if (command.equals("RPSQUIT") && otherUser.equals(otherPlayer)) {
	    					((RockPaperScissorsGUI) currentGame).close();
	    				}
	    				if (command.equals("TTT") && otherUser.equals(otherPlayer)) {
	    					((TicTacToe) currentGame).otherPlayerClick(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	    				}
	    				if(command.equals("SB")) {
	    					if(otherUser.equals(otherPlayer)) {
	    						lobby.snakeBikes.updateOpponent(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	    					}
	    				}
	    				if (command.equals("CHAT")) {
	    					String message = "";
	    					while(st.hasMoreTokens()) {
	    						message = message + " " + st.nextToken();
	    					}
	    					chat.print(otherUser + ": " + censor(message));
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
	public void sendWinMessage(String game) {
		out.println("Server " + "CHAT " + name + " just beat " + otherPlayer + " in a game of " + game);
	}
	public void setLobby(LobbyGUI newLobby) {
		lobby = newLobby;
	}
	public void showLobby() {
		lobby.show();
	}
	public void setWaitingRPS(boolean t) {
		waitingRPS = t;
	}
	public void setWaitingTTT(boolean t) {
		waitingTTT = t;
	}
	
	//Stops people from saying rude or inappropriate things in chat
	public String censor(String str) {
		if (str.toLowerCase().indexOf("atheism") != -1 || str.toLowerCase().indexOf("atheist") != -1) {
			str = "I love God and God loves me";
		}
		if (str.toLowerCase().indexOf("fuck") != -1 || str.toLowerCase().indexOf("shit") != -1) {
			str = "I tried to swear, but I realized swearing is not allowed on this CHRISTIAN SERVER";
		}
		return str;
	}
	
}