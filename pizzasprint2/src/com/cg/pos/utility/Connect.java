package com.cg.pos.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	private static Connection connection;
	public static Connection getConnection() {
		if(connection==null) {
			try {
				connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}
	
}
