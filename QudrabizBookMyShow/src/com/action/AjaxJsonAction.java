package com.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;


import com.opensymphony.xwork2.Action;
public class AjaxJsonAction implements Action{

	private Map<String, String> stateMap = new LinkedHashMap<String, String>();
	private String dummyMsg;
	//Parameter for Jquery
	private String countryName;
	private String eventName;
//
//	private Map<String, String> eventList = new LinkedHashMap<String, String>();
//	private Map<String, String> eventDateList = new LinkedHashMap<String, String>();
	private ArrayList<Event> eventList;
	private ArrayList<EventShow> eventShowList;
	private Map<String, String> edList = new LinkedHashMap<String, String>();

	public Map<String, String> getStateMap() {
		return stateMap;
	}

	public String getDummyMsg() {
		return dummyMsg;
	}

	public String getCountryName() {
		return countryName;
	}
	

	public void setStateMap(Map<String, String> stateMap) {
		this.stateMap = stateMap;
	}

	public void setDummyMsg(String dummyMsg) {
		this.dummyMsg = dummyMsg;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
//	public Map<String, String> getEventList() {
//		return eventList;
//	}
//	public void setEventList(Map<String, String> eventList) {
//		this.eventList = eventList;
//	}
//	
//	public Map<String, String> getEventDateList() {
//		return eventDateList;
//	}
//	public void setEventDateList(Map<String, String> eventDateList) {
//		this.eventDateList = eventDateList;
//	}
	public String getEventName() {
		return eventName;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public ArrayList<Event> getEventList() {
		return eventList;
	}

	public void setEventList(ArrayList<Event> eventList) {
		this.eventList = eventList;
	}
	
	public ArrayList<EventShow> getEventShowList() {
		return eventShowList;
	}

	public void setEventShowList(ArrayList<EventShow> eventShowList) {
		this.eventShowList = eventShowList;
	}
	
public Map<String, String> getedList() {
	return edList;
}
public void setedList(Map<String, String> edList) {
	this.edList = edList;
}
	

private List<EventShow> eventShows;

public List<EventShow> getEventShows() { return eventShows; }

	public String execute() {

//		dummyMsg = "Ajax action Triggered";
//		return SUCCESS;
		System.out.println("inside execute.");
		populateEvent();
		 System.out.println("eventList Count="+eventList.size());
//		populateShow(eventName);
		return "success";
	}

	public void populateEvent() {
		System.out.println("inside populateEvent.");
		Connection conn = null;
	      
	      try {
	         String URL = "jdbc:mysql://localhost:3306/bookmyshow_dates";
	         Class.forName("com.mysql.jdbc.Driver");
	         conn = DriverManager.getConnection(URL, "dra2", "dr@2");
	         String sql = "SELECT DISTINCT * FROM EVENTS";
//	         String sql ="SELECT * FROM user3333";
       	 PreparedStatement ps = conn.prepareStatement(sql);
       	
	         ResultSet rs = ps.executeQuery();
	         
	         eventList = new ArrayList<Event>();
	         while (rs.next()) {
//	        	 eventList.put(rs.getString("EventId"),rs.getString("EventName"));
	        	 eventList.add(new Event(rs.getInt("EventId"),rs.getString("EventName")));
	         }
	      } catch (Exception e) {
//	    	  return "error";
	      } 
	      finally {
	         if (conn != null) {
	            try {
	               conn.close();
	            } catch (Exception e) {
	            }
	         }
	      }
	      System.out.println("inside populateEvent End.");
	     
//	      return "getEventList";
	}
	
//	public void populateShow(String eventName) {
//	public ArrayList<EventShow> populateShow() {
//	public Map<String, String> populateShow() {
	public void populateShow() {
		String eventId="1";
		System.out.println("parameter:"+eventId);
		Connection conn = null;
	      
	      try {
	         String URL = "jdbc:mysql://localhost:3306/bookmyshow_dates";
	         Class.forName("com.mysql.jdbc.Driver");
	         conn = DriverManager.getConnection(URL, "dra2", "dr@2");
	         
	         
	         String sql="SELECT ShowId,EventDate,\r\n" + 
	         		"CASE \r\n" + 
	         		"	WHEN DATEDIFF(EventDate,NOW()) = 0 THEN CONCAT('Today',', ',DAY(EventDate),' ',MONTHNAME(EventDate))\r\n" + 
	         		"	WHEN DATEDIFF(EventDate,NOW()) = 1 THEN CONCAT('Tomorrow',', ',DAY(EventDate),' ',MONTHNAME(EventDate))\r\n" + 
	         		"	WHEN DATEDIFF(EventDate,NOW()) > 1 THEN CONCAT(DAYNAME(EventDate),', ',DAY(EventDate),' ',MONTHNAME(EventDate))\r\n" + 
	         		"END AS EventDay\r\n" + 
	         		",es.EventId\r\n" + 
	         		"FROM EventShows es\r\n" + 
	         		"INNER JOIN EVENTS e ON es.EventId = e.EventId AND (EventDate>=NOW())\r\n" + 
	         		"WHERE e.EventId='"+eventId+" ';";
//	         String sql ="SELECT * FROM user3333";
       	 PreparedStatement ps = conn.prepareStatement(sql);
       	
	         ResultSet rs = ps.executeQuery();
	         
	         eventShowList = new ArrayList<EventShow>();
	         while (rs.next()) {
	        	 eventShowList.add(new EventShow(rs.getInt("ShowId"),rs.getString("EventDay"),rs.getInt("EventId")));
//	        	 eventDateList.put(rs.getString("EventId"), rs.getString("EventName"));
	        	 edList.put(rs.getString("EventId"), rs.getString("EventName"));
	         }
	      } catch (Exception e) {
//	    	  return "error";
	      } 
	      finally {
	         if (conn != null) {
	            try {
	               conn.close();
	            } catch (Exception e) {
	            }
	         }
	      }
System.out.println("eventShowList:"+eventShowList.size());
//		JSONArray jsArray = new JSONArray(eventShowList);
		
		// Converts JSON string into a List of Product object
//		List<EventShow> eventShows = ((List<EventShow>) eventShowList);
//		 return eventShowList;
	}
	
}