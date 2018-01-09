package tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		String str = "Hello, TCP!";
		System.out.println(str);
		int port = 6789;
		Socket socket;
		
		try {
			socket = new Socket("localhost", port);
			BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream  toServer = new PrintStream(socket.getOutputStream());
			
			toServer.println(str);
			str = fromServer.readLine();
			System.out.println(str);
			
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
