package qBooking.struts2;

public class Event {
	private int eventId;
	
	private String eventName;
	
	private String eventDate;
	
	Event(int eventId, String eventName)
	{
		this.eventId = eventId;
		this.eventName = eventName;
	}
		
	Event(int eventId, String eventName,String eventDate)
	{
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDate=eventDate;
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
	
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	
}
