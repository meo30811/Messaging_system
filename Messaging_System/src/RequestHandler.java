import java.net.*;
import java.util.List;
import java.io.*;

public class RequestHandler implements Runnable{

	private MessageDatenbank db ;
	private Socket client;
	private InputStream in ;
	private PrintWriter writer;
	private OutputStream out;
	private BufferedReader reader;

	public RequestHandler(Socket client)
	{
			this.client = client;
			this.db = new MessageDatenbank();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try
		{
			in = client.getInputStream();
			out= client.getOutputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			writer = new PrintWriter(out,true);
			
			String message = reader.readLine();
			String command = getCommand(message).toLowerCase();
			System.out.println(command);
			while(true)
			{
			switch(command)
			{
			case "snd":
				String sender = getSender(message);
				String receiver= getReceiver(message);								
				try
				{
				  db.senden(receiver,getMessageToSend(message));
				  Thread.sleep(100);
				}catch(InterruptedException e){}
				break;
			case "reg":
				String user = getUser(message).trim();
				System.out.println(user);
				try
				{
				while(true)
				{
				boolean ret= db.register(user);
				System.out.println(ret);
				if(ret== true)
				{
				   System.out.println("OK_"+user);
				   writer.println("OK_"+user);
				   writer.flush();
				   break;
				}else
				{
					System.out.println("input not valid!");
					 writer.println("please input a valid Username:");
					 writer.flush();
				}
				}
				 Thread.sleep(100);
				}catch(InterruptedException e){}
				break;
			case "rcv":
				String recv = getUser(message); 
				try
				{
					List<Message> msgReceived= db.abrufen(recv);
					for(Message temp: msgReceived)
					{
						writer.println(temp.getNachricht());
						writer.flush();
					}
					Thread.sleep(1000);
				}catch(InterruptedException e){}
				break;
			}
		}
		}catch(IOException e){
			e.printStackTrace();
		}finally
		{
			try
			{
			client.close();
			in.close();
			reader.close();
			writer.close();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	public String getCommand(String message)
	{
		String[] mail = message.split(" ");
		String result = mail[0];
		return result;
	}
	public String getUser(String message)
	{
		String[] result = message.split(" ");
		return result[1];
	}
    public String getSender(String message)
    {
    	String[] mail = message.split(" ");
		String[] result = mail[1].split("#");
		return result[0];
    }
    public String getReceiver(String message)
    {    	
        String[] mail = message.split(" ");
        String[] result = mail[1].split("#");
	    return result[1];
    }
    public String getMessageToSend(String message)
    {
    	String[] mail = message.split(" ");
        String[] result = mail[1].split("#");
    	return result[2];
    }
}
