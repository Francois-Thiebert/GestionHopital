package projetHopital.Dao;

import java.sql.SQLException;
import java.sql.Statement;

public class Closer {
	
	public static void close(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
