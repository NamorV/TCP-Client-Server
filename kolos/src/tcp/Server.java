package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	public static void main(String args[]) {
		int port = 6789;
		ServerSocket serverSocket = null;
		Socket socket;
		
		try {
			serverSocket = new ServerSocket(port); 
			System.out.println("------Server started!------");
			
			while (true) {
				socket = serverSocket.accept();
				(new ServerThread(socket)).start();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if(serverSocket != null)
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
