package projetHopital.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Context {
	
	static {
		// bloc static=>execution 1 fois
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// singleton des DAO à placer ici
	
	private static Context singleton = null;
	
	public static Context getContext() {
		if (singleton == null) {
			singleton = new Context();
		}
		return singleton;
	}
	
	private Connection connection;
	
	private Context() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital", "root", "root123@");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
		
	public static void destroy() {
		if (singleton != null) {
			singleton.close();
			singleton = null;
		}
	}
	
	private void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
