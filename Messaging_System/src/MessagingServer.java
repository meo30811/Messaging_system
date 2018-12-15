import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;

public class MessagingServer{

	private int portNummer= 1213;
	private ServerSocket  ss= null;
	private Socket client= null;
	
	public MessagingServer()
	{
		try {
			this.ss = new ServerSocket(portNummer);
			System.out.println("Server waiting for connection...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void runServer()
	{
		ExecutorService service = Executors.newFixedThreadPool(20);
		while(true)
		{
		try {
			client = ss.accept();
			System.out.println("Client connected!");
			RequestHandler handler = new RequestHandler(client);
			service.submit(handler);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
 }
	
	
}
