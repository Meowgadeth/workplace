package sockets;
import java.util.*;
import java.net.*;
import java.io.*;

public class Server_two_return implements Runnable{
	Socket sock;
	private Scanner input;
	private PrintWriter out;
	String message = "";
	
	public Server_two_return(Socket x){
		this.sock = x;
	}

	public void checkConnection() throws IOException{
		if (!sock.isConnected()){
			for (int i = 1; i < Server_two.ConnectionArray.size(); i++){
				if (Server_two.ConnectionArray.get(i) == sock){
					Server_two.ConnectionArray.remove(i);
				}				
			}
			//made change to his code
			for (int i = 1; i < Server_two.ConnectionArray.size(); i++){
				Socket temp_sock = new Server_two().ConnectionArray.get(i-1);
				PrintWriter temp_out = new PrintWriter(temp_sock.getOutputStream());
				temp_out.println(sock.getLocalAddress().getHostName() + " disconnected");
				temp_out.flush();
				System.out.println(sock.getLocalAddress().getHostName() + " disconnected ");
			}
		}
	}
	
	public void run(){
		try{
			try{
				input = new Scanner(sock.getInputStream());
				out = new PrintWriter(sock.getOutputStream());
				
				while(true){
					checkConnection();
					
					if(!input.hasNext()) return;
					
					message = input.nextLine();
					System.out.println("client said: " + message);
					
					for (int i = 1; i <= Server_two.ConnectionArray.size(); i++){
						Socket temp_sock = Server_two.ConnectionArray.get(i-1);
						PrintWriter temp_out = new PrintWriter(temp_sock.getOutputStream());
						temp_out.println(message);
						temp_out.flush();
						System.out.println("Sent to: " + temp_sock.getLocalAddress().getHostName());
					}
				}
			}
			finally{
				sock.close();
			}
		}
		catch(Exception x){
			System.out.println(x);
		} 
	}
	
}
