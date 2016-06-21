package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DBConnectionTest 
{
	private DBConnection DBC_init;

	@Before
	public void setUp() throws Exception 
	{
		this.DBC_init = new DBConnection();
	}
	@Test
	public void testConnectToDB() 
	{
		if(this.DBC_init.ConnectToDB() == true)
		{
			System.out.println("ConnectToDB return true");
		}
		else if(this.DBC_init.ConnectToDB() == false)
		{
			System.out.println("ConnectToDB return false");
		}
		
		// Check if DBConnectionJDB returns true. True => connection success
		assertTrue(this.DBC_init.ConnectToDB());
	}

}
