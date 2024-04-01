package todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public enum ConnectionUtil {
	INSTANCE;
	
	private Connection conn;
	
	
	public Connection getConnection() throws Exception {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.219.100:1521/xe", "jspServlet", "jspServlet");
		
		return conn;
	}
}
