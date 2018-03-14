package com.optimum.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	private static Connection conn;

	public static Connection getConnection() {
		if( conn != null )
			return conn;
			try {

			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/assignment";
			String user = "root";
			String password = "root";
			
			Class.forName( driver );
			conn = DriverManager.getConnection( url, user, password );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void closeConnection( Connection toBeClosed ) {
		if( toBeClosed == null )
			return;
		try {
			toBeClosed.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
