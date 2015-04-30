package sockets;
import java.io.*;
import java.net.*;

public class Server_one {
	
	public static void main(String[] args) throws Exception{
		
		Server_one server = new Server_one();
		server.run();
	}
	
	public void run() throws Exception{
		ServerSocket serverSock = new ServerSocket(8888);
		Socket sock = serverSock.accept();
		InputStreamReader IR = new InputStreamReader(sock.getInputStream());
		BufferedReader BR = new BufferedReader(IR);
		
		String message = BR.readLine();
		System.out.println(message);
		
		if (message != null){
			PrintStream PS = new PrintStream(sock.getOutputStream());
			PS.println("message received");
		}
		serverSock.close();
	}
}
