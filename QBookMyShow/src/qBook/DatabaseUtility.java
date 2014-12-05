package qBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtility {
	
	private static Connection con;
    private static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://paviliondv4:3306/bookmyshow_dates";//ConnectionString = "Your database connection string";
    private final static String JDBC_USERNAME = "qbook";
    private final static String JDBC_PASSWORD = "qbook123";

    /**
     * create Database object
     */
    public DatabaseUtility() {
    }

    /**
     * To Get database connection,
     * loads the database base driver
     * @return a database connection
     * @throws SQLException throws an exception if an error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_Driver);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        return con;
    }

    /**
     * to get a result set of a query
     * @param query custom query
     * @return a result set of custom query
     * @throws SQLException throws an exception if an error occurs
     */
    public static ResultSet getResultSet(String query) throws SQLException {
        Connection con = getConnection();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        rs = st.executeQuery();

        return rs;
    }

    /**
     * to run an update query such as update, delete
     * @param query custom query
     * @throws SQLException throws an exception if an error occurs
     */
    public static void runQuery(String query) throws SQLException {
        Connection con = getConnection();
//        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        st.executeUpdate();
    }
}
