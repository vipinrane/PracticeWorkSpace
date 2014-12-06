package qBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;
import com.opensymphony.xwork2.Action;

public class AjaxJsonAction implements Action {
	private Map<String, String> eventList = new LinkedHashMap<String, String>();
	private Map<String, String> stateMap = new LinkedHashMap<String, String>();
	private String dummyMsg;
	// Parameter for Jquery
	private String eventName;
	private String eventId;

	public String execute() {
		System.out.println("inside Execute");
		dummyMsg = "Ajax action Triggered";
		populateEvent();
		return "success";
	}

	@SuppressWarnings("resource")
	public void populateEvent() {
		System.out.println("inside populateEvent.");
		Connection conn = null;

		try {
			String URL = "jdbc:mysql://paviliondv4:3306/bookmyshow_dates";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "qbook", "qbook123");

			String sql = "SELECT DISTINCT * FROM EVENTS";
			// String sql ="SELECT * FROM user3333";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				eventList.put(rs.getString("EventId"),
						rs.getString("EventName"));
			}
		} catch (Exception e) {
			// return "error";
			System.out.println("Error:" + e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
		System.out.println("Count=" + eventList.size());
		System.out.println("inside populateEvent End.");

		// return "getEventList";
	}

	public void populateShow(String eventId) {
		// System.out.println("parameter:"+eventId);
		Connection conn = null;
		try {
			String URL = "jdbc:mysql://paviliondv4:3306/bookmyshow_dates";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "qbook", "qbook123");
			System.out.println("Connection State=" + conn.isClosed());

			String sql = "SELECT ShowId,EventDate,\r\n"
					+ "CASE \r\n"
					+ "	WHEN DATEDIFF(EventDate,NOW()) = 0 THEN CONCAT('Today',', ',DAY(EventDate),' ',MONTHNAME(EventDate))\r\n"
					+ "	WHEN DATEDIFF(EventDate,NOW()) = 1 THEN CONCAT('Tomorrow',', ',DAY(EventDate),' ',MONTHNAME(EventDate))\r\n"
					+ "	WHEN DATEDIFF(EventDate,NOW()) > 1 THEN CONCAT(DAYNAME(EventDate),', ',DAY(EventDate),' ',MONTHNAME(EventDate))\r\n"
					+ "END AS EventDay\r\n"
					+ ",es.EventId\r\n"
					+ "FROM EventShows es\r\n"
					+ "INNER JOIN EVENTS e ON es.EventId = e.EventId AND (EventDate>=NOW())\r\n"
					+ "WHERE e.EventId=" + eventId + ";";
			// String sql ="SELECT * FROM user3333";
			System.out.println("Query:" + sql);
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				stateMap.put(rs.getString("ShowId"), rs.getString("EventDay"));
			}
		} catch (Exception e) {
			// return "error";
			System.out.println("Error:" + e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
		dummyMsg = "eventShowList:" + stateMap.size();
		System.out.println("eventShowList:" + stateMap.size());
	}

	public String TestABC() {
		System.out.println("---" + eventName);
		if (eventName.equals("Select event")) {
			stateMap.put("1", "Select State");
		} else {
			populateShow(eventId);
		}
		return "success";
	}

	public Map<String, String> getStateMap() {
		return stateMap;
	}

	public String getDummyMsg() {
		return dummyMsg;
	}

	public void setStateMap(Map<String, String> stateMap) {
		this.stateMap = stateMap;
	}

	public void setDummyMsg(String dummyMsg) {
		this.dummyMsg = dummyMsg;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Map<String, String> getEventList() {
		return eventList;
	}

	public void setEventList(Map<String, String> eventList) {
		this.eventList = eventList;
	}

}