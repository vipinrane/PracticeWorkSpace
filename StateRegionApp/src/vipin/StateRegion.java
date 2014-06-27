package vipin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StateRegion {
    private List<StateMaster> stateList = new ArrayList<StateMaster>();
    private List<RegnMaster> regnList = new ArrayList<RegnMaster>();
    private Integer stateId;

    public Integer getStateId() {
        return this.stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public List<StateMaster> getStateList() {
        return stateList;
    }

    public void setStateList(List<StateMaster> stateList) {
        this.stateList = stateList;
    }

    public void setRegnList(List<RegnMaster> regnList) {
        this.regnList = regnList;
    }

    public List<RegnMaster> getRegnList() {
        return regnList;
    }

    public String execute() throws Exception {

        stateList = getStateData();//
            if (stateId != null) {
            regnList = getRegnByStateId(stateId);
        }

        //setMessage(getText(MESSAGE));
        return "success";
    }
    /**
     * Provide default valuie for Message property.
     */
    
    public List<StateMaster> getStateData()
    {
    	System.out.println("inside getStateData.");
		Connection conn = null;
		List<StateMaster> stateList = null;
	      try {
	         String URL = "jdbc:mysql://localhost:3306/bookmyshow_dates";
	         Class.forName("com.mysql.jdbc.Driver");
	         conn = DriverManager.getConnection(URL, "dra2", "dr@2");
	         String sql = "SELECT DISTINCT * FROM State";
	         
       	 PreparedStatement ps = conn.prepareStatement(sql);
       	
	         ResultSet rs = ps.executeQuery();
	         
	         stateList = new ArrayList<StateMaster>();
	         while (rs.next()) {
	        	 stateList.add(new StateMaster(rs.getInt("StateId"),rs.getString("Name")));
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
	      System.out.println("inside getStateData End.");
	      return stateList;
    }
    
    public List<RegnMaster> getRegnByStateId(Integer stateId)
    {
    	System.out.println("inside getRegnByStateId.");
		Connection conn = null;
		List<RegnMaster> regionList = null;
	      try {
	         String URL = "jdbc:mysql://localhost:3306/bookmyshow_dates";
	         Class.forName("com.mysql.jdbc.Driver");
	         conn = DriverManager.getConnection(URL, "dra2", "dr@2");
	         String sql = "SELECT DISTINCT * FROM Region WHERE StateId="+stateId;
	         
       	 PreparedStatement ps = conn.prepareStatement(sql);
       	
	         ResultSet rs = ps.executeQuery();
	         
	         regionList = new ArrayList<RegnMaster>();
	         while (rs.next()) {
	        	 regionList.add(new RegnMaster(rs.getInt("RegionId"),rs.getString("Name"),rs.getInt("StateId")));
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
	      System.out.println("inside getRegnByStateId End.");
	      return regionList;
    }
}