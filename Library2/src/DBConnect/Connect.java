package DBConnect;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Connect {
	public static Connection con;
	public Connect(String driverClass,String url,String userName,String password) {
		try {
			Class.forName(driverClass);
			con=DriverManager.getConnection(url,userName,password);
			System.out.println("Database has been connected.");
		}catch(ClassNotFoundException e) {
			System.err.println("Invalid Driver Class.");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}	

	}
	
	

