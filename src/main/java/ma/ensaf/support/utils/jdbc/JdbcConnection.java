package ma.ensaf.support.utils.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {
	private static Connection connection;
	static {
		try {
			//TODO utiliser un fichier properties pour stocker les information de connexion a la bd
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_CATAL", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}
