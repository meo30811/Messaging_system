import java.net.*;
import java.io.*;
import java.util.*;

public class KeyboardListener implements Runnable {

	private Socket client;

	public KeyboardListener(Socket client )
	{
		this.client = client;
	}
	@Override
	public void run(){
		// TODO Auto-generated method stub
		try
		{
		OutputStream out = client.getOutputStream();
        PrintWriter writer = new PrintWriter(out);

        Scanner keyboard = new Scanner(System.in);
        String input = null;

        do {
            // wait for user to type a line...
            input = keyboard.nextLine(); // blocks until enter-key is pressed on keyboard
            // ...and sent via socket to other end
            writer.println(input);
            writer.flush();

        } while(input != null && !input.equalsIgnoreCase("BYE"));

        client.close();

	}catch(IOException e)
	{
		e.printStackTrace();
	}

 }
}
