
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class SocketThread implements Runnable {

	private Socket client ;

	public SocketThread(Socket client)
	{
	  this.client = client;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
		/*while(!message.equalsIgnoreCase("Exit"))
		{
			send = serverMessage.nextLine();
			writer.println(send);
			//writer.flush();
			message = reader.readLine();
			System.out.println("from Server: "+message);

		}*/
	    
	    KeyboardListener keyboard = new KeyboardListener(client);
	    new Thread(keyboard).start();
	    InputStreamListener input = new InputStreamListener(client);
	    new Thread(input).start();
	    
		/*while((message= reader.readLine())!=null)
		{
			System.out.println("from Server: "+message);
			if(message.equalsIgnoreCase("bye"))
				break;
			//----------------------------------
			send = serverMessage.nextLine();
			if(send!= null)
		   System.out.println("Client: "+message);
			writer.println(send);
			writer.flush();
		}*/


	} catch (Exception e) {
		// TODO Auto-generated catch block
	 e.printStackTrace();
	}
	}
}
