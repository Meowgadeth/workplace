package sockets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

class FtpClient{
	public static void main(String args[]) throws Exception{
		Socket sock = new Socket("127.0.0.1",3824);
		TransferClient transfer = new TransferClient(sock);
		transfer.run();
	}
}
class TransferClient{
	Socket client;

	DataInputStream inStream;
	DataOutputStream outStream;
	BufferedReader br;
	TransferClient(Socket sock){
		try{
			client=sock;
			inStream=new DataInputStream(client.getInputStream());
			outStream=new DataOutputStream(client.getOutputStream());
			br=new BufferedReader(new InputStreamReader(System.in));
		}
		catch(Exception e){
			System.out.println(e);
		}		
	}
	void send() throws Exception{		
		
		String filename;
		System.out.print("Enter File Name: ");
		filename=br.readLine();
			
		File file=new File(filename);
		if(!file.exists()){
			System.out.println("File not Exists");
			outStream.writeUTF("Cancel");
			return;
		}	
		outStream.writeUTF(filename);
		
		//existence check
		String serverMessage = inStream.readUTF();
		if(serverMessage.compareTo("File Already Exists")==0){
			String option;
			System.out.println("File Already Exists. OverWrite (Y/N) ?");
			while(true){
				option = br.readLine();
				if(option.compareTo("Y") == 0){
					outStream.writeUTF("Y");
					break;
				}
				else if (option.compareTo("N") == 0){
					outStream.writeUTF("N");
					return;
				}
				System.out.println("Invalud option, enter Y/N");
			}						
		}
		
		System.out.println("Sending File ...");
		FileInputStream fileInput = new FileInputStream(file);
		int encode;
		do{
			encode = fileInput.read();
			outStream.writeUTF(String.valueOf(encode));
		}		
		while(encode!=-1);
		fileInput.close();
		System.out.println(inStream.readUTF());		
	}
	
	void receive() throws Exception{
		String fileName;
		System.out.print("Enter File Name: ");
		fileName=br.readLine();
		outStream.writeUTF(fileName);
		String serverMessage=inStream.readUTF();
		
		if(serverMessage.compareTo("File Not Found") == 0){
			System.out.println("File not found on Server");
			return;
		}
		else if(serverMessage.compareTo("READY") == 0){
			System.out.println("Receiving File");
			File file=new File(fileName);
			
			//file existence check
			if(file.exists()){
				String option;
				System.out.println("File Already Exists. Want to OverWrite (Y/N) ?");	
				while(true){
					option = br.readLine();
					if(option.compareTo("Y") == 0)	
					{
						outStream.writeUTF("Y");
						break;
					}
					else if (option.compareTo("N") == 0)
					{
						outStream.writeUTF("N");
						return;
					}
					System.out.println("Invalid option, enter Y/N");
				}				
			}
			else{
				outStream.writeUTF("DAMN RIGHT");
			}
			
			FileOutputStream fileOutput=new FileOutputStream(file);
			int encode;
			String temp;
			do{
				temp=inStream.readUTF();
				encode=Integer.parseInt(temp);
				if(encode!=-1)
				{
					fileOutput.write(encode);					
				}
			}while(encode!=-1);
			
			fileOutput.close();
			System.out.println(inStream.readUTF());			
		}
		
		
	}

	public void run() throws Exception{
		//Authentication phase
		String username = "";
		String password = "";
		String response = "";
		while(true){
			System.out.println("username: ");
			username = br.readLine();
			System.out.println("password: ");
			password = br.readLine();		
			outStream.writeUTF(username);
			outStream.writeUTF(password);
			
			response = inStream.readUTF();
			if (response.compareTo("GOOD") == 0){
				System.out.println("Connection accepted by server");
				break;
			}
			System.out.println("invalide username or password, try again");
		}
		
		//Execution phase
		while(true){	
			System.out.println("CHOOSE ONE: UPLOAD/DOWNLOAD/LIST/QUIT");			
			//variable initializations
			String choice;
			choice= br.readLine();
			String cwd = System.getProperty("user.dir");
			File file = new File(cwd);
			String filenames = "";
			
			if (choice.compareTo("UPLOAD") == 0){
				outStream.writeUTF("UPLOAD");
				send();
			}
			else if (choice.compareTo("DOWNLOAD") == 0){
				outStream.writeUTF("DOWNLOAD");
				receive();
			}
			else if (choice.compareTo("QUIT") == 0){
				outStream.writeUTF("QUIT");
				System.exit(1);
			}
			else if (choice.compareTo("LIST") == 0){
				for (String f : file.list()){
					filenames += f + " ";
				}
				System.out.print("FILES: ");
				System.out.println(filenames);
			}
			else{
				System.out.println("Invalid choice, try again");
			}
		}
	}
}
