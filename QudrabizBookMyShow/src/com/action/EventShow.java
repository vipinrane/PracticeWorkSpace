package com.action;

public class EventShow {

	private int showId;
	private String eventDate;
	private int eventId;

	EventShow(int showId, String eventDate, int eventId)
	{
		this.showId = showId;
		this.eventId = eventId;
		this.eventDate = eventDate;
	}
	
	public int getshowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}
	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

}
