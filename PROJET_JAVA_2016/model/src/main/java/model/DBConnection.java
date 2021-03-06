package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final class DBConnection {
	
	private static DBConnection	INSTANCE	= null;
	private Connection					connection;

	public DBConnection() 
	{
		this.ConnectToDB();
	}
	
	public static synchronized DBConnection getInstance() {
		if (DBConnection.INSTANCE == null) {
			DBConnection.INSTANCE = new DBConnection();
		}
		return DBConnection.INSTANCE;
	}


	public Boolean ConnectToDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://10.162.128.237:3307/jpu2016project?autoReconnect=true&useSSL=false","invite","motdepasse");
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public Connection getConnection() {
		return this.connection;
	}
	

}
