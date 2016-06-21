package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


class DAOMapDB extends DAOEntity<DBMap> {
	
	int dbX = 0;
	int dbY = 0;
	int dbV = 0;
	int nbLigne = 0;
	String dbS;

	public DAOMapDB(final Connection connection) throws SQLException {
		super(connection);
	}


	public boolean create(final DBMap entity) {
		// Not implemented
		return false;
	}


	public boolean delete(final DBMap entity) {
		// Not implemented
		return false;
	}


	public boolean update(final DBMap entity) {
		// Not implemented
		return false;
	}

/**
 * 
 * @param i
 * ID de la map dont on verifie l'existence
 */
public DBMap CheckIfExist(int i){
		
		try
		{
			dbV= 0;
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
			
			dbV = 1;
			}
		
		}
		catch (final SQLException e)
		{
			e.printStackTrace ();
			
		}
		return null;
		
	}
	/**
	 * 
	 * @param i
	 * ID de la map a ajouter dans la BDD
	 * @param x
	 * Coordonnee X d'un sprite
	 * @param y
	 * Coordonnee Y d'un sprite
	 * @param c
	 * Sprite a ajouter
	 */
	public DBMap addMapDB(int i, int x, int y, char c)
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
	/**
	 * 
	 * @param i
	 * ID de la map que l'on recupere
	 * @param x
	 * Coordonnee X du sprite que l'on recupere
	 * @param y
	 * Coordonnee Y du spirte que l'on recupere
	 */
	public DBMap DataFromDB(int i, int x, int y)
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
	public DBMap find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DBMap find(String key) {
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
	
	public int getDbV() {
		return dbV;
	}
	
}
