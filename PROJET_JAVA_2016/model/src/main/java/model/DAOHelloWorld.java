package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class DAOHelloWorld.
 *
 * @author Jean-Aymeric Diet
 */
class DAOHelloWorld extends DAOEntity<HelloWorld> {
	
	int dbX = 0;
	int dbY = 0;
	String dbS;
	String dbV;
	int nbLigne = 0;


	/**
	 * Instantiates a new DAO hello world.
	 *
	 * @param connection
	 *          the connection
	 * @throws SQLException
	 *           the SQL exception
	 */
	public DAOHelloWorld(final Connection connection) throws SQLException {
		super(connection);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#create(model.Entity)
	 */
	@Override
	public boolean create(final HelloWorld entity) {
		// Not implemented
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#delete(model.Entity)
	 */
	@Override
	public boolean delete(final HelloWorld entity) {
		// Not implemented
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#update(model.Entity)
	 */
	@Override
	public boolean update(final HelloWorld entity) {
		// Not implemented
		return false;
	}


public HelloWorld CheckIfExist(int i){
		
		try
		{
			
			final String db_verif = "{call CheckExist(?)}";
			final CallableStatement callVerif = this.getConnection().prepareCall(db_verif);
			
			callVerif.setInt(1, i);
			
			callVerif.execute();
			
		final ResultSet resultSet = callVerif.getResultSet();
		resultSet.last();
		int nbLigne = resultSet.getRow();
		resultSet.beforeFirst();
		System.out.println(nbLigne);
		
		if(nbLigne != 0){
			
			dbV = "false";
			}
		
		}
		catch (final SQLException e)
		{
			e.printStackTrace ();
			
		}
		return null;
		
	}
	
	public HelloWorld addMapDB(int i, int x, int y, char c)
	{
		try 
		{
			final String db_addmap = "{call AddMap(?,?,?,?)}";
			final CallableStatement call = this.getConnection().prepareCall(db_addmap);
			
			call.setInt(1, i);
			call.setInt(2, x);
			call.setInt(3, y);
			call.setString(4, Character.toString(c));
			
			call.execute();
		}
		catch (final SQLException e)
		{
			e.printStackTrace ();
			
		}
		return null;
	}
	
	public HelloWorld DataFromDB(int i, int x, int y)
	{
		
		try
		{
			final String db_data = "{call DataFromDB(?,?,?)}";
			final CallableStatement callproc2 = this.getConnection().prepareCall(db_data);
			callproc2.setInt(1, i);
			callproc2.setInt(2, x);
			callproc2.setInt(3, y);
			callproc2.execute();
			final ResultSet resultSet = callproc2.getResultSet();
			if(resultSet.first())
			{
				dbS = resultSet.getString("Char_Sprite");	
			}
		}

		catch (final SQLException e)
		{
			e.printStackTrace ();
		
		}
		return null;

	}

	@Override
	public HelloWorld find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HelloWorld find(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getDbX() {
		return dbX;
	}

	public int getDbY() {
		return dbY;
	}

	public String getDbS() {
		return dbS;
	}
	
	public String getDbV() {
		return dbV;
	}
	
}
