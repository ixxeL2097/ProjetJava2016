package Element.Motion;
import Element.*;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class Hero extends MotionElement
{
	private static char HERO = 'H';	
	private Element element;
	public static Element HeroSTART;
	public static Element Saved_ELEM;
	public static Element Next_ELEM;
	private static String HERO_MOVE="C:/Users/FredPRO/git/ProjetJava2016/PROJET_JAVA_2016/SPRITE/lorann_l.png";
	private static ImageIcon HEROIMAGE;
	public boolean inFight;
	
	
	//-------------------------------------GETTERS & SETTERS----------------------------------------------------------------------
	


	public static char getHERO() 
	{
		return HERO;
	}

	public static ImageIcon getHEROIMAGE() {
		return HEROIMAGE;
	}
	
	//---------------------------------------CONSTRUCTEURS----------------------------------------------------------------------------

	public Hero(int Y, int X) 
	{
		super(HERO_MOVE, Permeabilite.PENETRABLE);
		HEROIMAGE = new ImageIcon("C:/ProjetJava/Sprite/lorann_l.png");

		this.currentX=X;
		this.currentY=Y;	
		this.lastX=X;
		this.lastY=Y;
	}
	
	//------------------------------------------METHODES--------------------------------------------------------------------------------
	
	/*public void PlaceHero()
	{
		Saved_ELEM= map.ElementMatrix[Hero.currentY][Hero.currentX];
		map.ElementMatrix[Hero.currentY][Hero.currentX]=this.HeroSTART;
	}*/

	/*public void Move(int nextMoveUP_DWN, int nextMoveRGT_LFT)
	{
		this.element = map.ElementMatrix[nextMoveUP_DWN][nextMoveRGT_LFT];
		
		if(element.getPermea()==Permeabilite.BLOCKING)
		{
			System.out.println("Vous ne pouvez pas avancer.");
		}
		else if(element.getPermea()==Permeabilite.AGGRO)
		{	
			this.lastX=currentX;
			this.lastY=currentY;
			this.currentY=nextMoveUP_DWN;
			this.currentX=nextMoveRGT_LFT;
			this.inFight=true;			
			Next_ELEM= map.ElementMatrix[currentY][currentX];			
			this.map.updateModel_HeroMove();			
		}
		else
		{
			this.lastX=currentX;
			this.lastY=currentY;
			this.currentY=nextMoveUP_DWN;
			this.currentX=nextMoveRGT_LFT;
			this.inFight=false;					
			Next_ELEM= map.ElementMatrix[currentY][currentX];
			this.map.updateModel_HeroMove();						
		}	
	}
	
	
	public void moveForward() 
	{
		this.setIconName("/images/HERO_UP.png");
		//map.ElementMatrix[currentY][currentX].setElemIcon(new ImageIcon(getClass().getResource("/images/HERO_UP.png")));
		this.Move(this.currentY-1, this.currentX);
	}

	public void moveBackward() 
	{
		this.setIconName("/images/HERO_DW.png");
		//map.ElementMatrix[currentY][currentX].setElemIcon(new ImageIcon(getClass().getResource("/images/HERO_DW.png")));
		this.Move(this.currentY+1, this.currentX);
	}

	public void moveRight() 
	{
		this.setIconName("/images/HERO_RGT.png");
		//map.ElementMatrix[currentY][currentX].setElemIcon(new ImageIcon(getClass().getResource("/images/HERO_RGT.png")));
		this.Move(this.currentY, this.currentX+1);	
	}

	public void moveLeft() 
	{
		this.setIconName("/images/HERO_LFT.png");
		//map.ElementMatrix[currentY][currentX].setElemIcon(new ImageIcon(getClass().getResource("/images/HERO_LFT.png")));
		this.Move(this.currentY, this.currentX-1);
	}*/

}
