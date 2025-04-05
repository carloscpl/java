package br.ucb.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String sql = "jdbc:mysql://localhost/sistemabancario";
				connection = DriverManager.getConnection(sql, "root", "");
				
				//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//String sql = "jdbc:microsoft:sqlserver://localhost/sistemabancario";
				//connection = DriverManager.getConnection(sql, "sa", "");
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException("Driver não localizado");
			}
		}
		return connection;
	}

}
