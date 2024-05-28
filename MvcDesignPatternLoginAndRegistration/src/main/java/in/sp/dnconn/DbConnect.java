package in.sp.dnconn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc_db", "root", "Aailuvbaba@123");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	

}
