package vaannila;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RegisterAction extends ActionSupport {
	
	private String userName;
	
	private String password;
	
	private String gender;
	
	private String about;
	
	private String country;
	
	private ArrayList<Country> countryList;
	
	private String[] community;
	
	private ArrayList<String> communityList;
	
	private Boolean  mailingList;

	private ArrayList<Event> eventList;
	
	public String populate() {

		countryList = new ArrayList<Country>();
		countryList.add(new Country(1, "India"));
		countryList.add(new Country(2, "USA"));
		countryList.add(new Country(3, "France"));
		
		communityList = new ArrayList<String>();
		communityList.add("Java");
		communityList.add(".Net");
		communityList.add("SOA");
		
		community = new String[]{"Java",".Net"};
		mailingList = true;
		populateEvent();
		return "populate";
	}

	public void populateEvent() {

		Connection conn = null;
	      
	      try {
	         String URL = "jdbc:mysql://localhost:3306/bookmyshow_dates";
	         Class.forName("com.mysql.jdbc.Driver");
	         conn = DriverManager.getConnection(URL, "dra2", "dr@2");
	         String sql = "SELECT EventId,EventName,DATE,\r\n" + 
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
	
	public String execute() {
		return SUCCESS;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public ArrayList<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<Country> countryList) {
		this.countryList = countryList;
	}

	public String[] getCommunity() {
		return community;
	}

	public void setCommunity(String[] community) {
		this.community = community;
	}

	public ArrayList<String> getCommunityList() {
		return communityList;
	}

	public void setCommunityList(ArrayList<String> communityList) {
		this.communityList = communityList;
	}

	public Boolean getMailingList() {
		return mailingList;
	}

	public void setMailingList(Boolean mailingList) {
		this.mailingList = mailingList;
	}

	public ArrayList<Event> getEventList() {
		return eventList;
	}

	public void setEventList(ArrayList<Event> eventList) {
		this.eventList = eventList;
	}

}
