package com.ct.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class DbUtil {

	private static String driver, url, username, password;
	private DbUtil() {
		
	}
	
	
	static {
		ResourceBundle rb = ResourceBundle.getBundle("jdbc");
		driver = rb.getString("jdbc.driver");
		url = rb.getString("jdbc.url");
		username = rb.getString("jdbc.username");
		password = rb.getString("jdbc.password");
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}
}


