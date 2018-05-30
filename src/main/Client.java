package main;

//Class Description: The Client class is the heart of our code. It processes all incoming messages from the server
//		     and is used by the games to send messages to the server. It sends messages to the games from the
//                   server as well. There is also some processing, such as the chat filter.

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import GUI.ChatGUI;
import GUI.RockPaperScissorsGUI;
import battleShips.BattleShips;
import lobby.Lobby;
import snakeBikes.SnakeBikes;
import ticTacToe.TicTacToe;

public class Client extends Thread{

	public BufferedReader in;
	public PrintWriter out;
	
	public String address;
	public final int PORT = 2256;
	public String name;
	
	private ChatGUI chat;
	public Object currentGame;
	private Lobby lobby;
	public String otherPlayer;
	
	private boolean waitingRPS;
	private boolean waitingTTT;
	private boolean waitingSB;
	private boolean waitingBS;
	public ArrayList<Point> opponentPoints = new ArrayList();
	
	//Constructor takes the address of the server and the name of the user to connect
	//to the server and instantialize everything.
	public Client(String serverAddress, String theName) {
		address = serverAddress;
		name = theName;
		waitingRPS = false;
		waitingTTT = false;
		waitingSB = false;
		waitingBS = false;
		//create the chat window
		chat = new ChatGUI(this);
		chat.start();
		//connect to the server
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
	//A new thread that loops forever until the game ends, processing inputs from the server
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
					//Starts Rock Paper Scissors if someone else clicks the button and you've clicked it
	    				if (waitingRPS == true && !(otherUser.equals(name))) {
	    					if (command.equals("SRPS")) {
	    						send("CRPS");
	    						currentGame = new RockPaperScissorsGUI(otherUser, this);
	    						setWaitingFalse();
	    						lobby.hide();
	    						otherPlayer = otherUser;
	    						JOptionPane.showMessageDialog(null, "Game of Rock Paper Scissors has been started with " + otherPlayer);
	    					}
	    					if (command.equals("CRPS")) {
	    						currentGame = new RockPaperScissorsGUI(otherUser, this);
	    						setWaitingFalse();
	    						lobby.hide();
	    						otherPlayer = otherUser;
	    						JOptionPane.showMessageDialog(null, "Game of Rock Paper Scissors has been started with " + otherPlayer);
	    					}
	    				}
					//Starts Tic Tac Toe if someone else clicks the button and you've clicked it
	    				if (waitingTTT == true && !(otherUser.equals(name))) {
	    					if (command.equals("STTT")) {
	    						send("CTTT");
	    						currentGame = new TicTacToe(true, otherUser, this);
	    						((TicTacToe)currentGame).start();
	    						setWaitingFalse();
	    						lobby.hide();
	    						otherPlayer = otherUser;
	    						JOptionPane.showMessageDialog(null, "Game of Tic Tac Toe has been started with " + otherPlayer + ". You're X, you go first");
	    					}
	    					if (command.equals("CTTT")) {
	    						currentGame = new TicTacToe(false, otherUser, this);
	    						((TicTacToe)currentGame).start();
	    						setWaitingFalse();
	    						lobby.hide();
	    						otherPlayer = otherUser;
	    						JOptionPane.showMessageDialog(null, "Game of Tic Tac Toe has been started with " + otherPlayer + ". You're O, you go second");
	    					}
	    				}
					//Starts Snake Bikes if someone else clicks the button and you've clicked it
	    				if (waitingSB == true && !(otherUser.equals(name))) {
	    					if (command.equals("SSB")) {
	    						send("CSB");
	    						currentGame = new SnakeBikes(this, false);
	    						((SnakeBikes)currentGame).start();
	    						setWaitingFalse();
	    						lobby.hide();
	    						otherPlayer = otherUser;
	    						//JOptionPane.showMessageDialog(null, "Game of Snake Bikes has been started with " + otherPlayer);
	    					}
	    					if (command.equals("CSB")) {
	    						currentGame = new SnakeBikes(this, true);
	    						((SnakeBikes)currentGame).start();
	    						setWaitingFalse();
	    						lobby.hide();
	    						otherPlayer = otherUser;
	    						//JOptionPane.showMessageDialog(null, "Game of Snake Bikes has been started with " + otherPlayer);
	    					}
	    				}
					//Starts Battleships if someone else clicks the button and you've clicked it
	    				if (waitingBS == true && !(otherUser.equals(name))) {
	    					if (command.equals("SBS")) {
	    						send("CBS");
	    						currentGame = new BattleShips(this);
	    						((BattleShips)currentGame).start();
	    						setWaitingFalse();
	    						lobby.hide();
	    						otherPlayer = otherUser;
	    						JOptionPane.showMessageDialog(null, "Game of Battle Ships has been started with " + otherPlayer);
	    					}
	    					if (command.equals("CBS")) {
	    						currentGame = new BattleShips(this);
	    						((BattleShips)currentGame).start();
	    						setWaitingFalse();
	    						lobby.hide();
	    						otherPlayer = otherUser;
	    						JOptionPane.showMessageDialog(null, "Game of Battle Ships has been started with " + otherPlayer);
	    					}
	    				}
					//The following are checking for game related commands and calling methods in the games
					//based on inputs
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
	    						opponentPoints.add(new Point (Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
	    					}
	    				}
	    				if(command.equals("BS")) {
	    					if(otherUser.equals(otherPlayer)) {
	    						((BattleShips) currentGame).recieveAttack(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	    					}
	    				}
	    				if(command.equals("BSMISS")) {
	    					if(otherUser.equals(otherPlayer)) {
	    						((BattleShips) currentGame).recieveAnswer(false, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	    					}
	    				}
	    				if(command.equals("BShipHit")) {
	    					if(otherUser.equals(otherPlayer)) {
	    						((BattleShips) currentGame).recieveAnswer(true, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	    					}
	    				}
	    				if(command.equals("BSDONE")) {
	    					if(otherUser.equals(otherPlayer)) {
	    						((BattleShips) currentGame).enemyShipsPlaced = true;
	    					}
	    				}
	    				if(command.equals("BSYOULOOSEHAHA")) {
	    					if(otherUser.equals(otherPlayer)) {
	    						((BattleShips) currentGame).loser();
	    					}
	    				}
					//Processes chat to censor it and then print it to the chat GUI
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
	public void setLobby(Lobby newLobby) {
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
	public void setWaitingSB(boolean t) {
		waitingSB = t;
	}
	public void setWaitingBS(boolean t) {
		waitingBS = t;
	}
	public void setWaitingFalse() {
		waitingRPS = false;
		waitingTTT = false;
		waitingSB = false;
		waitingBS = false;
	}
	
	//Stops people from saying rude or inappropriate things in chat
	public String censor(String str) {
		if (str.toLowerCase().indexOf("atheism") != -1 || str.toLowerCase().indexOf("atheist") != -1 || 
		    str.toLowerCase().indexOf("god is dead") != -1 || 
		    str.toLowerCase().indexOf("fuck") != -1 || 
		    str.toLowerCase().indexOf("shit") != -1 || 
		    str.toLowerCase().indexOf("ass ") != -1 || 
		    str.toLowerCase().indexOf("negro") != -1 || 
		    str.toLowerCase().indexOf("nig") != -1 || 
		    str.toLowerCase().indexOf("chink") != -1 || 
		    str.toLowerCase().indexOf("Jap ") != -1) {
			int r = (int)(Math.Random() * 5);
			if (r == 0) {
				str = "I love God and God loves me";
			}
			if (r == 1) {
				str = "Time to go to church";
			}
			if (r == 2) {
				str = "Amen";
			}
			if (r == 3) {
				str = "I was going to swear, but then I realized that I cannot swear on this CHRISTIAN SERVER";
			}
			if (r == 4) {
				JPanel panel = new JPanel();
				 JOptionPane.showMessageDialog(panel, "Sorry sir, this is a Christian server; NO SWEARING", "Warning",
        JOptionPane.WARNING_MESSAGE);
			}
		}
		return str;
	}
	
}
