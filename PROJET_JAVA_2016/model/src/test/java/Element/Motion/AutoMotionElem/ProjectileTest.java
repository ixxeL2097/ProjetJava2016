package Element.Motion.AutoMotionElem;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

public class ProjectileTest
{
	
	private Projectile proj;
	
	@Before
	public void setUp() throws Exception 
	{
		this.proj = new Projectile();
	}

	@Test
	public void testFillArrayList() 
	{

		ImageIcon GREEN_T = this.proj.getGREEN();
		ImageIcon BLUE_T = this.proj.getBLUE();
		ImageIcon PURPLE_T =  this.proj.getPURPLE();
		ImageIcon RED_T = this.proj.getRED();
		ImageIcon YELLOW_T = this.proj.getYELLOW();
		
		ImageIcon[] expectedArray = {GREEN_T, BLUE_T, PURPLE_T, RED_T, YELLOW_T};
		
		ImageIcon[] resultArray = {this.proj.getChangeColor().get(0),
									this.proj.getChangeColor().get(1),
									this.proj.getChangeColor().get(2),
									this.proj.getChangeColor().get(3),
									this.proj.getChangeColor().get(4)} ;
				
		assertArrayEquals(expectedArray, resultArray);
	}

}