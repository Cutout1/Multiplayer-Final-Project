package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import GUI.ServerGUI;

public class Server extends Thread{
	
	private final int PORT = 2256;
	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<PrintWriter> writers = new ArrayList<PrintWriter>();
	private ServerGUI gui;
	
	public Server(ServerGUI serverGUI) {
		gui = serverGUI;
	}
	
	public void run() {
		try {
			System.out.println("startServer");
			print("Server started on port "+PORT + "\nClients should connect to the IP: " + Inet4Address.getLocalHost().getHostAddress());
			ServerSocket listener = new ServerSocket(PORT);
			try {
				while(true) {
					new Receiver(listener.accept(), gui).start();
				}
			} finally {
				listener.close();
			}
		}
		catch (Exception e) {}
	}
	
	public void print(String str) {
		gui.print(str);
	}
	
	//Receiver class are spawned from the loop and are responsible for dealing with a client
	private static class Receiver extends Thread{
		
		private User user;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		private ServerGUI gui;
		
		public Receiver(Socket socket, ServerGUI serverGUI) {
			this.socket = socket;
			gui = serverGUI;
		}
		
		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				
				String name = in.readLine();
				out.println("accepted");
				writers.add(out);
				user = new User(name);
				users.add(user);
				for(PrintWriter writer : writers) {
					writer.println("Server CHAT " + user.getName() + " connected. There are now " + users.size() + " users connected to the server");
				}	

				while(true) {
					String input = in.readLine();
					if(input==null) {
						return;
					}
					gui.print(input);
					for(PrintWriter writer : writers) {
						writer.println(input);
					}			
				}
			}catch(IOException e) {
				System.out.println(e.getMessage());
			} finally {
				//client is leaving
				for(PrintWriter writer : writers) {
					writer.println("Server CHAT " + user.getName() + " has just disconnected. There are now " + (users.size() - 1) + " users connected");
				}	
				if(user.getName() != null) {
					users.remove(0);
				}
				if(out != null) {
					writers.remove(out);
				}
				try {
					socket.close();
				} catch(IOException e) {
				}
			}
		}
	}
	
}
