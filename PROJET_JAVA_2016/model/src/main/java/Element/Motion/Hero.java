package Element.Motion;
import javax.swing.ImageIcon;

import Element.*;
import contract.ControllerOrder;
import contract.IPlayer;
import model.DimensionMap;
import model.Model;

public class Hero extends MotionElement implements IPlayer
{
	private static final String HERO_MOVE="C:/ProjetJava/Sprite/lorann.gif";
	private static final ImageIcon MOVEUP = new ImageIcon("C:/ProjetJava/Sprite/lorann_u.png");
	private static final ImageIcon MOVEDW = new ImageIcon("C:/ProjetJava/Sprite/lorann_b.png");
	private static final ImageIcon MOVELF = new ImageIcon("C:/ProjetJava/Sprite/lorann_l.png");
	private static final ImageIcon MOVERT = new ImageIcon("C:/ProjetJava/Sprite/lorann_r.png");
	private static final ImageIcon MOVEUPRT = new ImageIcon("C:/ProjetJava/Sprite/lorann_ur.png");
	private static final ImageIcon MOVEDWRT = new ImageIcon("C:/ProjetJava/Sprite/lorann_br.png");
	private static final ImageIcon MOVEUPLF = new ImageIcon("C:/ProjetJava/Sprite/lorann_ul.png");
	private static final ImageIcon MOVEDWLF = new ImageIcon("C:/ProjetJava/Sprite/lorann_bl.png");
	private static final ImageIcon LORANNGIF = new ImageIcon("C:/ProjetJava/Sprite/lorann.gif");
	private boolean HasMoved = false;
	private boolean Alive ;
	private boolean Shootable=true;
	private static int SCORE=0;
	private static int LIVES=1;
	private ControllerOrder LastLorannMove=ControllerOrder.UP;
	
	
	
	//---------------------------------------CONSTRUCTEURS----------------------------------------------------------------------------

	public Hero(Model model,int Y, int X) 
	{
		super(model ,HERO_MOVE, Permeabilite.HERO);
		this.setX(X);
		this.setY(Y);
		this.setLastX(X);
		this.setLastY(Y);
		this.setAlive(true);
	}
	
	public void CheckAvailableMove(int UP_DW, int RT_LF)
	{
		if((this.getY()+UP_DW >=0 && this.getX()+RT_LF >=0) && (this.getY()+UP_DW < DimensionMap.Y && this.getX()+RT_LF < DimensionMap.X))
		{
			this.getModel().MoveLorann(UP_DW, RT_LF);
		}
		else
		{
			this.setShootable(false);
		}
	}
	
	public boolean CheckAvailablePosition(int UP_DW, int RT_LF )
	{
		if((this.getY()-UP_DW >=0 && this.getX()-RT_LF >=0) && (this.getY()-UP_DW < DimensionMap.Y && this.getX()-RT_LF < DimensionMap.X))
		{
			return true;
		}
		else
		{
			return false;
		}	
	}
	
	public void MoveUP() 
	{
		this.setElemIcon(this.getMoveUp());
		this.setLastLorannMove(ControllerOrder.UP);
		this.CheckAvailableMove(-1, 0);	
	}

	public void MoveDW() 
	{
		this.setElemIcon(this.getMoveDw());
		this.setLastLorannMove(ControllerOrder.DOWN);
		this.CheckAvailableMove(1, 0);		
	}

	public void MoveLF() 
	{
		this.setElemIcon(this.getMoveLf());
		this.setLastLorannMove(ControllerOrder.LEFT);
		this.CheckAvailableMove(0, -1);		
	}

	public void MoveRT() 
	{
		this.setElemIcon(this.getMoveRt());
		this.setLastLorannMove(ControllerOrder.RIGHT);
		this.CheckAvailableMove(0, 1);		
	}

	public void MoveUpLf() 
	{
		this.setElemIcon(this.getMoveUpLf());
		this.setLastLorannMove(ControllerOrder.UPPERLEFT);
		this.CheckAvailableMove(-1, -1);	
	}

	public void MoveUpRt() 
	{
		this.setElemIcon(this.getMoveUpRt());
		this.setLastLorannMove(ControllerOrder.UPPERRIGHT);
		this.CheckAvailableMove(-1, 1);	
	}

	public void MoveDwLf() 
	{
		this.setElemIcon(this.getMoveDwLf());
		this.setLastLorannMove(ControllerOrder.DOWNLEFT);
		this.CheckAvailableMove(1, -1);	
	}

	public void MoveDwRt() 
	{
		this.setElemIcon(this.getMoveDwRt());
		this.setLastLorannMove(ControllerOrder.DOWNRIGHT);
		this.CheckAvailableMove(1, 1);	
	}
	

	public ImageIcon getMoveUp() {
		return MOVEUP;
	}

	public ImageIcon getMoveDw() {
		return MOVEDW;
	}

	public ImageIcon getMoveLf() {
		return MOVELF;
	}

	public ImageIcon getMoveRt() {
		return MOVERT;
	}

	public ImageIcon getMoveUpRt() {
		return MOVEUPRT;
	}

	public ImageIcon getMoveDwRt() {
		return MOVEDWRT;
	}

	public ImageIcon getMoveUpLf() {
		return MOVEUPLF;
	}

	public ImageIcon getMoveDwLf() {
		return MOVEDWLF;
	}

	public ImageIcon getLorannGIF() {
		return LORANNGIF;
	}

	public boolean isHasMoved() {
		return HasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		HasMoved = hasMoved;
	}

	public boolean isAlive() {
		return Alive;
	}

	public void setAlive(boolean alive) {
		Alive = alive;
	}

	public ControllerOrder getLastLorannMove() {
		return LastLorannMove;
	}

	public void setLastLorannMove(ControllerOrder lastLorannMove) {
		LastLorannMove = lastLorannMove;
	}

	public boolean isShootable() {
		return Shootable;
	}

	public void setShootable(boolean shootable) {
		Shootable = shootable;
	}

	public int getScore() {
		return SCORE;
	}

	public static void setScore(int score) {
		Hero.SCORE = score;
	}

	public int getLives() {
		return LIVES;
	}

	public void setLives(int lives) {
		Hero.LIVES = lives;
	}
	
	
	
	
	
}
