package sockets;
import java.io.*;
import java.net.*;

public class Client_one {

	public static void main(String[] args) throws Exception{
		
		
		Client_one client = new Client_one();
		client.run();
		
	}
	
	

	public void run() throws Exception{
		Socket sock = new Socket("localhost",8888);
		PrintStream PS = new PrintStream(sock.getOutputStream());
		PS.println("Hello to server from client");
		
		
		InputStreamReader IR = new InputStreamReader(sock.getInputStream());
		BufferedReader BR = new BufferedReader(IR);
		
		String message = BR.readLine();
		System.out.println(message);		
	}
}
