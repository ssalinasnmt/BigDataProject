package voltDb;

import java.sql.*;

public class VoltDbConnection {
	private static String driver = "org.voltdb.jdbc.Driver";
	private static String url = "jdbc:voltdb://localhost:21212";
	
	private Connection conn = null;
	private String tableName = null; // Table to use for SQL statements
	
	/**
	 * Creates a new connection to the VoltDB server
	 * @param tableName Name of table that this connection should execute statements on.
	 */
	public VoltDbConnection(String tableName) {
		this.tableName = tableName;
		
		/* Load driver and set up database connection */
        try {
        	Class.forName(driver);
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("Error creating JDBC connection");
		} catch (ClassNotFoundException e) {
			System.out.println("Error linking in VoltDB driver");
		}
	}
}
