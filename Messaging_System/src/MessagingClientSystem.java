import java.net.*;
import java.io.*;
import java.util.*;

public class MessagingClientSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     try
     {
		Runnable client = new ClientThread();
		new Thread(client).start();
     }catch(Exception e)
     {
    	 e.printStackTrace();
     }
	}

}
