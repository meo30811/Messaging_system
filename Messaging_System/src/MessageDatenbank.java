import java.util.Map;
import java.util.concurrent.locks.*;
import java.util.regex.Pattern;
import java.util.*;
import java.net.*;
import java.io.*;

public class MessageDatenbank {
// passive Klasse
	private Socket client;
	public Map<String,List<Message>> db = new HashMap<>();
	private List<Message> messages;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
	
	public boolean register(String username)
	{
		writeLock.lock();;
		boolean ret= false;
		try
		{
	      ret = Pattern.matches("{A-Z}{a-zA-Z}",username);
        if(ret== true)
		{
          messages = new ArrayList<Message>();
		  db.put(username, messages);
		  
		}        
		}finally
		{
		   writeLock.unlock();
		}
	    return ret;	
	}
	public void senden(String receiver ,String message)
	{
		writeLock.lock();
		String key="";
	    for(Map.Entry<String , List<Message>> entry: db.entrySet())
		{
	   	   if(entry.getKey().equals(receiver))
			{
				 key = entry.getKey();
				Message m = new Message(message);
				entry.getValue().add(m);
				break;
			}
			
		}
	    if(key.equals(""))
	    {
	    	Message m = new Message(message);
	    	messages = new ArrayList<Message>();
	    	messages.add(m);
	    	db.put(receiver, messages);
	    }
	    writeLock.unlock();
	    		
	}
	public boolean receiverInDb(String receiver)
	{
		readLock.lock();
		boolean ret = false; 
		for(Map.Entry<String , List<Message>> entry: db.entrySet())
		{
			if(entry.getKey().equals(receiver))
			{
				ret = true;
			}
		}
		readLock.unlock();
		return ret;
	}
	public List<Message> abrufen(String username)
	{
		readLock.lock();
		List<Message> message= null;
		try
		{
		message = new ArrayList<Message>();
		for(Map.Entry<String , List<Message>> entry: db.entrySet())
		{
			if(entry.getKey().equals(username))
			{
				message= entry.getValue();
				break;
			}
		}
		}finally
		{
		  readLock.unlock();
		}
		return message;
		
	}
}
