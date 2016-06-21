package view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import contract.ControllerOrder;

public class ViewTest {

	@Before
	public void setUp() throws Exception 
	{
		
	}

	@Test
	public void testKeyCodeToControllerOrder() 
	{
		
		//Simple Moving (UDRL)
				
		final ControllerOrder m_up = View.keyCodeToControllerOrder(38,38); //UP Moving
		final ControllerOrder m_down = View.keyCodeToControllerOrder(40,40); //DOWN Moving
		final ControllerOrder m_right = View.keyCodeToControllerOrder(39,39); //RIGHT Moving
		final ControllerOrder m_left = View.keyCodeToControllerOrder(37,37); //LEFT Moving
		
			assertSame(ControllerOrder.UP, m_up);
			assertSame(ControllerOrder.DOWN, m_down);
			assertSame(ControllerOrder.RIGHT, m_right);
			assertSame(ControllerOrder.LEFT, m_left);
			
		
		// Diagonal Moving
		
		final ControllerOrder m_uprt = View.keyCodeToControllerOrder(38,39); //UPPERRIGHT Moving
		final ControllerOrder m_uplf = View.keyCodeToControllerOrder(38,37); //UPPERRIGHT Moving
		final ControllerOrder m_downrt = View.keyCodeToControllerOrder(40,39); //DOWNRIGHT Moving
		final ControllerOrder m_downlf = View.keyCodeToControllerOrder(40,37); //DOWNLEFT Moving
		
			assertSame(ControllerOrder.UPPERRIGHT, m_uprt);
			assertSame(ControllerOrder.UPPERLEFT, m_uplf);
			assertSame(ControllerOrder.DOWNRIGHT, m_downrt);
			assertSame(ControllerOrder.DOWNLEFT, m_downlf);
	
	}

}