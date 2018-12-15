import java.util.List;

public class Message {

	private String nachricht;
	
	public Message(String nachricht)
	{
		this.nachricht = nachricht;
	}
	
	public void setNachricht(String nachricht)
	{
		this.nachricht= nachricht;
	}
	public String getNachricht()
	{
		return this.nachricht;
	}
}
