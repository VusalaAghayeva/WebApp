package az.orient.course.util;

import java.sql.*;

public class JdbcUtility {
	public static void close(Connection c, PreparedStatement ps, ResultSet rs) throws Exception {
		if (c != null) {
			c.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}

	}
	public static Connection getMyConnection() throws Exception {
		Connection c=null;
		String dbDriver = "com.mysql.cj.jdbc.Driver";
		String dbURL = "jdbc:mysql:// localhost:3306/videoders?useSSl=false";
		String dbName = "videoders";
		String dbUsername = "root";
		String dbPassword = "12345";

		Class.forName(dbDriver);
		c = DriverManager.getConnection(dbURL,
				dbUsername,
				dbPassword);
		return c;
	}
}

