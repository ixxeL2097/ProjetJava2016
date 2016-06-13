package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class Entity.
 *
 * @author Jean-Aymeric Diet
 */
abstract class Entity 
{
	public DBConnection find(final int id) 
	{
		DBConnection dbc = new DBConnection();

			try {
				final String sql = "{call MapFromDB(?)}";
				final CallableStatement call = this.getConnection().prepareCall(sql);
				call.setInt(1, id);
				call.execute();
				final ResultSet resultSet = call.getResultSet();
				} 
		
				catch (final SQLException e) 
				{
					e.printStackTrace();
				}
		
		return null;
	}

	
	
	
	
}
