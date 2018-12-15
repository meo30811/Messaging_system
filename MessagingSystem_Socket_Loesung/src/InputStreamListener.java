import java.net.*;
import java.io.*;
import java.util.*;

public class InputStreamListener implements Runnable {

	private Socket client;

	public InputStreamListener(Socket client)
	{
		this.client = client;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			InputStream in = client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String messageFromServer = null;
			do
			{
				messageFromServer = reader.readLine();
				System.out.println(messageFromServer);

			}while(messageFromServer!= null && !messageFromServer.equalsIgnoreCase("BYE"));
			
			client .close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}

	}

}
