package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


class DAOMapDB extends DAOEntity<MapDB> {
	
	int dbX = 0;
	int dbY = 0;
	String dbS;
	int dbV = 0;
	int nbLigne = 0;

	public DAOMapDB(final Connection connection) throws SQLException {
		super(connection);
	}

/**
 * 
 * @param i
 * 		ID de la map dont on veux verifier l'existence
 * @return
 * 		Retourne 1 si la map existe déjà dans la BDD 
 */
public MapDB CheckIfExist(int i){
		
		try
		{
			dbV = 0;
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
 * 		ID de la map que l'on ajoute a la BDD
 * @param x
 * 		Coordonnée X du sprite
 * @param y
 * 		Coordonnée Y du sprite
 * @param c
 * 		Char representant le sprite
 */
	public MapDB addMapDB(int i, int x, int y, char c)
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
	 * 	ID de la map que l'on recupere
	 * @param x
	 *  Coordonnée X des sprites
	 * @param y
	 * 	Coordonnée Y des spirtes
	 */
	public MapDB DataFromDB(int i, int x, int y)
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


	@Override
	public boolean create(MapDB entity) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean delete(MapDB entity) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean update(MapDB entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MapDB find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapDB find(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
