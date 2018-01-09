package tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket mySocket;

	public ServerThread(Socket socket) {
		super();
		mySocket = socket;
	}
	
	public void run() {
		try {
			System.out.println("------Thread started!------");
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
			PrintStream toClient = new PrintStream(mySocket.getOutputStream());
			String str;
			
			str = fromClient.readLine();
			System.out.println(str);
			
			str = str + " succesfully send!";
			toClient.println(str);
			
			mySocket.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
