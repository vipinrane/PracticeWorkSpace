package vaannila;

public class Event {

	private int eventId;
	
	private String eventName;
	
	Event(int eventId, String eventName)
	{
		this.eventId = eventId;
		this.eventName = eventName;
	}
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
}
