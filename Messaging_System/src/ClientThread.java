
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ClientThread implements Runnable {

	private Socket client = null;
	private int portNumber= 1213;
	public ClientThread()
	{
	    try {
			this.client = new Socket("localhost",portNumber);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		PrintWriter writer = new PrintWriter(client.getOutputStream());
		Scanner serverMessage = new Scanner(System.in);
		
		System.out.println("Register as: REG username.");
		System.out.println("send-----as: SND sender#receiver#message.");
		System.out.println("Receive--as: RCV username.");
		
	    String message="" , send="";
		while(!message.equalsIgnoreCase("Exit"))
		{
			send = serverMessage.nextLine();
			writer.println(send);
			//writer.flush();
			message = reader.readLine();
			System.out.println("from Server: "+message);
			
		}
		
		reader.close();
		serverMessage.close();
		writer.close();
		client.close();
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
