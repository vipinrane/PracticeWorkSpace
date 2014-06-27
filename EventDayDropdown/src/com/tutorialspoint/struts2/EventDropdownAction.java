package com.tutorialspoint.struts2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class EventDropdownAction extends ActionSupport {
//	 private String eventName;
	 public String execute()
	 {
		return ""; 
	 }
	public List<String> getEventList()
	{
//		String ret = ERROR;
	      Connection conn = null;
	      List<String> eventList=new ArrayList<String>();
	      try {
	         String URL = "jdbc:mysql://localhost:3306/bookmyshow_dates";
	         Class.forName("com.mysql.jdbc.Driver");
	         conn = DriverManager.getConnection(URL, "dra2", "dr@2");
	         String sql = "SELECT eventId,eventname FROM events";
         	 PreparedStatement ps = conn.prepareStatement(sql);
	        
	         ResultSet rs = ps.executeQuery();
	         
	         while (rs.next()) {
	        	 eventList.add(rs.getString(1));
//	            ret = SUCCESS;
	         }
	      } catch (Exception e) {
//	         ret = ERROR;
	      } finally {
	         if (conn != null) {
	            try {
	               conn.close();
	            } catch (Exception e) {
	            }
	         }
	      }
	      return eventList;
	}
}


