package sockets;
import java.util.*;
import java.net.*;
import java.io.*;

public class Client_two implements Runnable{
	Socket sock;
	Scanner input;
	Scanner send = new Scanner(System.in);
	PrintWriter out;
	
	public Client_two(Socket sock){
		this.sock = sock;
	}
	
	public void run(){
//		try{
//			try{
//				input = new Scanner(sock.getInputStream());
//				out = new PrintWriter(sock.getOutputStream());
//				out.flush();
//				checkStream();
//			}
//		}
	}
	
	public void disconnect() throws IOException{
		
	}
	
	public void checkStream(){
		
	}
}
