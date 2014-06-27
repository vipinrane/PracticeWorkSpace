package qBooking.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HelloWorldAction {
	private ArrayList<Event> eventList;
	private ArrayList<Event> eventDateList;
	public ArrayList<Event> getEventList() {
		return eventList;
	}

	public void setEventList(ArrayList<Event> eventList) {
		this.eventList = eventList;
	}
	
	public ArrayList<Event> getEventDateList() {
		return eventDateList;
	}

	public void setEventDateList(ArrayList<Event> eventDateList) {
		this.eventDateList = eventDateList;
	}

	public String execute()
	{
		System.out.println("Hello From execute.");
		populateEvent();
//		return "success";
//		return "failure";
		return "eventDropdown";
	}
	
	public void populateEvent() {

		Connection conn = null;
	      
	      try {
	         String URL = "jdbc:mysql://localhost:3306/bookmyshow_dates";
	         Class.forName("com.mysql.jdbc.Driver");
	         conn = DriverManager.getConnection(URL, "dra2", "dr@2");
	         String sql = "SELECT EventId, EventName,DATE,\r\n" + 
	         		"CASE \r\n" + 
	         		"	WHEN DATEDIFF(DATE,NOW()) = 0 THEN CONCAT('Today',', ',DAY(DATE),' ',MONTHNAME(DATE))\r\n" + 
	         		"	WHEN DATEDIFF(DATE,NOW()) = 1 THEN CONCAT('Tomorrow',', ',DAY(DATE),' ',MONTHNAME(DATE))\r\n" + 
	         		"	WHEN DATEDIFF(DATE,NOW()) > 1 THEN CONCAT(DAYNAME(DATE),', ',DAY(DATE),' ',MONTHNAME(DATE))\r\n" + 
	         		"END AS EventDay\r\n" + 
	         		"FROM EVENTS\r\n" + 
	         		"WHERE DATE_FORMAT(DATE,'%d/%m/%Y') >= DATE_FORMAT(NOW(),'%d/%m/%Y')";
//	         String sql ="SELECT * FROM user3333";
       	 PreparedStatement ps = conn.prepareStatement(sql);
       	
	         ResultSet rs = ps.executeQuery();
	         
	         eventList = new ArrayList<Event>();
	         while (rs.next()) {
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

//	      return "populateEvents";
	}
	
	public void populateEventDate(String eventName) {
		System.out.println("parameter:"+eventName);
		Connection conn = null;
	      
	      try {
	         String URL = "jdbc:mysql://localhost:3306/bookmyshow_dates";
	         Class.forName("com.mysql.jdbc.Driver");
	         conn = DriverManager.getConnection(URL, "dra2", "dr@2");
	         String sql = "SELECT EventId, EventName,DATE,\r\n" + 
	         		"CASE \r\n" + 
	         		"	WHEN DATEDIFF(DATE,NOW()) = 0 THEN CONCAT('Today',', ',DAY(DATE),' ',MONTHNAME(DATE))\r\n" + 
	         		"	WHEN DATEDIFF(DATE,NOW()) = 1 THEN CONCAT('Tomorrow',', ',DAY(DATE),' ',MONTHNAME(DATE))\r\n" + 
	         		"	WHEN DATEDIFF(DATE,NOW()) > 1 THEN CONCAT(DAYNAME(DATE),', ',DAY(DATE),' ',MONTHNAME(DATE))\r\n" + 
	         		"END AS EventDay\r\n" + 
	         		"FROM EVENTS\r\n" + 
	         		"WHERE EventName="+eventName;
//	         String sql ="SELECT * FROM user3333";
       	 PreparedStatement ps = conn.prepareStatement(sql);
       	
	         ResultSet rs = ps.executeQuery();
	         
	         eventDateList = new ArrayList<Event>();
	         while (rs.next()) {
	        	 eventDateList.add(new Event(rs.getInt("EventId"),rs.getString("EventName"),rs.getString("EventDay")));
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

//	      return "populateEvents";
	}
}
