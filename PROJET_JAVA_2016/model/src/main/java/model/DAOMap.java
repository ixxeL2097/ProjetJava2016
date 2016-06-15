package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class DAOMap extends DAOEntity<Entity>{
	
	public DAOMap(Connection connection) throws SQLException{
		
		super(connection);
	}

	@Override
	public boolean create(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Entity find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity find(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DBConnection ConnectDB(){
		
		Statement st;
		ResultSet resultSet;
		
		
		try
		{
			st = getConnection().createStatement();
			resultSet = st.executeQuery("SELECT ID_MAP_lvl1 FROM maps");
			
			while(resultSet.next())
			{
				System.out.println("ID Maps : " + resultSet.getString("ID_Map_lvl"));
			}
		}
		
		catch(Exception e)
		{
			System.out.println("Impossible de se connecter à la base de données ou mauvaise requête (synthax error");
		}
		return null;
	}
	
	public DBConnection addMapBdd(int i, int x, int y, char c){
		
		try{
			final String sql = "{call AjoutMap(?,?,?,?)}";
			final CallableStatement call = this.getConnection().prepareCall(sql);
			
			call.setInt(1, i);
			call.setInt(2, x);
			call.setInt(3, y);
			call.setString(4, Character.toString(c));
			call.execute();
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	 
}
