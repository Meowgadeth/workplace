package sockets;
import java.util.*;
import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

public class Server_two {

	public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
	public static ArrayList<String> CurrentUsers = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException{
		try{
			final int PORT = 8888;
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Waiting for clients...");
			
			while(true){
				Socket sock = server.accept();
				ConnectionArray.add(sock);
				
				System.out.println("Client connected from: " + sock.getLocalAddress().getHostName());
				AddUserName(sock);
				
				Server_two_return chat = new Server_two_return(sock);
				Thread x = new Thread(chat); //to fix
				x.start();
			}
			
		}
		catch(Exception x) {System.out.print(x);}
	}

	private static void AddUserName(Socket sock) throws IOException{
		Scanner input = new Scanner(sock.getInputStream());
		String userName = input.nextLine();
		CurrentUsers.add(userName);
		
		for (int i = 1; i <= ConnectionArray.size(); i++){
			Socket temp_sock = ConnectionArray.get(i-1);
			PrintWriter out = new PrintWriter(temp_sock.getOutputStream());
			out.println("#?1" + CurrentUsers);
			out.flush();	
		}
		
	}

}
