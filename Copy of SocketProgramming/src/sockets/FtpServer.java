package sockets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class FtpServer{
	public static void main(String args[]) throws Exception{
		//start operation listener
		OperationListener listener = new OperationListener();
		Thread listenerThread = new Thread(listener);
		listenerThread.start();
		
		//Listen for client
		int counter = 0;
		ServerSocket server = new ServerSocket(3824);
		System.out.println("[==MAIN==]FTP Server Started on Port Number 3824");
		while(true){
			System.out.println("[==MAIN==]Listening for new connection ...");
			Transfer trans = new Transfer(server.accept());
			counter++;
			Thread t = new Thread(trans,"CLIENT #"+counter);
			t.start();
		}
	}
}

class OperationListener implements Runnable{
	public void run(){
		while(true){
			String op;
			Scanner reader = new Scanner(System.in);
			op = reader.nextLine();
			if (op.compareTo("QUIT")==0){
				System.out.println("[==MAIN==]FTP Server shutting down");
				System.exit(0);
			}
			
			String cwd = System.getProperty("user.dir");
			File file = new File(cwd);
			String filenames = "";
			
			if (op.compareTo("LIST")==0){
				for (String f : file.list()){
					filenames += f + " ";
				}
				System.out.println("[==MAIN==]FILES: ");
				System.out.println(filenames);
			}
			else{
				System.out.println("[==MAIN==]Invalid command, please try again");
			}
		}
	}
}

class Transfer implements Runnable{
	Socket client;
	DataInputStream inStream;
	DataOutputStream outStream;
	
	Transfer(Socket sock){
		try{
			this.client	= sock;						
			this.inStream=new DataInputStream(client.getInputStream());
			this.outStream=new DataOutputStream(client.getOutputStream());
			System.out.println("[==MAIN==]FTP Client Connected...");			
		}
		catch(Exception e){
			System.out.println(e);
		}		
	}
	
	void send() throws Exception{		
		String filename=inStream.readUTF();
		File file=new File(filename);
		String option;
		if(!file.exists()){
			outStream.writeUTF("File Not Found");
			return;
		}
		else{
			outStream.writeUTF("READY");
			//file existence check
			option = inStream.readUTF();
			if (option.compareTo("N") == 0){
				return;
			}
			
			FileInputStream fileInput = new FileInputStream(file);
			int encode;
			do{
				encode=fileInput.read();
				outStream.writeUTF(String.valueOf(encode));
			}
			while(encode!=-1);	
			
			fileInput.close();	
			outStream.writeUTF("File Receive Successfully");							
		}
	}
	
	void receive() throws Exception{
		String filename = inStream.readUTF();
		
		if(filename.compareTo("Cancel")==0){
			System.out.println("["+Thread.currentThread().getName()+"]"+"Cancel Command Received");
			return;
		}
		
		File file= new File(filename);
		String option;		
		
		//check if file exists
		if(file.exists()){
			outStream.writeUTF("File Already Exists");
			option=inStream.readUTF();
		}
		else{
			outStream.writeUTF("send");
			option = "Y";
		}
		
		if(option.compareToIgnoreCase("Y")==0){                                                   	
			FileOutputStream fileOutput = new FileOutputStream(file);	
			int encode;                                         
			String temp;                                    
			do{                                               
				temp = inStream.readUTF();                         
				encode = Integer.parseInt(temp);                  
				if(encode!=-1)                                  
				{                                           
					fileOutput.write(encode);					        
				}                                           
			}while(encode!=-1);                                 
			                                                
			fileOutput.close();                                   
			outStream.writeUTF("File Send Successfully");        
		}                                               
		return;	
	}


	public void run(){
		//Authentication phase
		try{
			String username = "";
			String password = "";
			String temp1 = "";
			String temp2 = "";
			
			BufferedReader br;
			int ok = 0;

			while(ok == 0){
				username = inStream.readUTF();
				password = inStream.readUTF();
				br = new BufferedReader(new FileReader("authentication.txt"));

				while((temp1 = br.readLine())!=null && (temp2 = br.readLine())!= null)
				{
					if (temp1.equals(username) && temp2.equals(password)){
						outStream.writeUTF("GOOD");
						System.out.println("["+Thread.currentThread().getName()+"]"+
						"authentication successful");
						ok = 1;
						break;
					}
				}
				if (ok == 0){
					outStream.writeUTF("BAD");
				}		
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		//Execution phase
		while(true){
			try{
			    System.out.println("["+Thread.currentThread().getName()+"]"+"Waiting for Command");
			    String Command=inStream.readUTF();
			    if(Command.compareTo("DOWNLOAD")==0){
			    	System.out.println("["+Thread.currentThread().getName()+"]"+"DOWNLOAD Command Received");
			    	send();
			    	continue;
			    }
			    else if(Command.compareTo("UPLOAD")==0){
			    	System.out.println("["+Thread.currentThread().getName()+"]"+"UPLOAD Command Receiced");				
			    	receive();
			    	continue;
			    }
			    else if(Command.compareTo("QUIT")==0){
			    	System.out.println("["+Thread.currentThread().getName()+"]"+"QUIT Command Received");
			    	return;
			    }
			}
			catch(Exception e){
				System.out.println("[==MAIN==]"+e);
				System.out.println("[==MAIN==]FTP Server shutting down");
				System.exit(0);
			}
		}
	}
}