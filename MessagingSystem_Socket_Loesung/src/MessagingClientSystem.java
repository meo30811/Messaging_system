import java.net.*;
import java.io.*;
import java.util.*;

public class MessagingClientSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     try
     {
    	 Socket client = new Socket("localhost",1213);
		SocketThread runClient = new SocketThread(client);
		new Thread(runClient).start();
     }catch(Exception e)
     {
    	 e.printStackTrace();
     }
	}

}
