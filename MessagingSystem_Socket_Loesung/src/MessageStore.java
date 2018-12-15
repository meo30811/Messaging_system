import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageStore {

	 private Map<String, List<Message>> messages = new TreeMap<>();
	    private Lock monitor = new ReentrantLock();

	    public String addUser(String username) {
	        try {
	            monitor.lock();
	            if(messages.containsKey(username)) {
	                return null;
	            }
	            else {
	                messages.put(username, new LinkedList<>());
	            }

	            return username;

	        } finally {
	            monitor.unlock();
	        }
	    }

	    public Message addMessage(String username, String toUsername, String text) {
	        Message message = new Message(text, username, toUsername);

	        try {
	            monitor.lock();
	            if (messages.containsKey(toUsername)) {
	                messages.get(toUsername).add(message);
	            }

	            return message;

	        } finally {
	            monitor.unlock();
	        }
	    }


	    public List<Message> getNewMessages(String fromUsername) {

	        try {
	            monitor.lock();

	            if (messages.containsKey(fromUsername)) {
	                List<Message> userMessages = messages.get(fromUsername);
	                messages.replace(fromUsername, new LinkedList<>());
	                return  userMessages;
	            }
	            else
	                return null;

	        } finally {
	            // will be called before return
	            monitor.unlock();
	        }
	    }

}
