package com.javatpoint;
import java.sql.*;
import java.util.ArrayList;

public class FetchRecords {
	ArrayList<User> list=new ArrayList<User>();

	public ArrayList<User> getList() {
		return list;
	}
//	public void setList(ArrayList<User> list) {
//		this.list = list;
//	}

	public String execute(){
		try{
			System.out.println("Inside1");
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			 String URL = "jdbc:mysql://localhost:3306/bookmyshow_dates";
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection con = DriverManager.getConnection(URL, "dra2", "dr@2");
			PreparedStatement ps=con.prepareStatement("SELECT * FROM user3333");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				System.out.println("Inside2");
				User user=new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				list.add(user);
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
		
		return "success";
   }
}
