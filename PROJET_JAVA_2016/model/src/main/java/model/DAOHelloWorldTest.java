package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DAOHelloWorldTest 
{
	private DAOHelloWorld DAOHWInit;
	
	@Before
	public void setUp() throws Exception 
	{
		this.DAOHWInit = new DAOHelloWorld(DBConnection.getInstance().getConnection());
	}

	@Test
	public void testGetDbX() {
		int expectedDbX = 0;
		assertEquals(expectedDbX, this.DAOHWInit.getDbX());
	}

	@Test
	public void testGetDbY() {
		int expectedDbY = 0;
		assertEquals(expectedDbY, this.DAOHWInit.getDbY());
	}

	@Test
	public void testGetDbS() {
		String expectedDbS = null;
		assertEquals(expectedDbS, this.DAOHWInit.getDbS());
	}

	@Test
	public void testGetDbV() {
		String expectedDbV = null;
		assertEquals(expectedDbV, this.DAOHWInit.getDbV());
	}

}
