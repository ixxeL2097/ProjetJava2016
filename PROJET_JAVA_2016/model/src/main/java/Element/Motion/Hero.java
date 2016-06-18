package Element.Motion;
import javax.swing.ImageIcon;

import Element.*;
import contract.ControllerOrder;
import model.Model;

public class Hero extends MotionElement
{
	private static String HERO_MOVE="C:/ProjetJava/Sprite/lorann.gif";
	private ImageIcon MoveUp;
	private ImageIcon MoveDw;
	private ImageIcon MoveLf;
	private ImageIcon MoveRt;
	private ImageIcon MoveUpRt;
	private ImageIcon MoveDwRt;
	private ImageIcon MoveUpLf;
	private ImageIcon MoveDwLf;
	private ImageIcon LorannGIF;
	private boolean HasMoved = false;
	private boolean Alive ;
	private boolean Shootable=true;
	private int score=0;
	private ControllerOrder LastLorannMove=ControllerOrder.UP;
	
	
	
	//---------------------------------------CONSTRUCTEURS----------------------------------------------------------------------------

	public Hero(Model model,int Y, int X) 
	{
		super(model ,HERO_MOVE, Permeabilite.HERO);
		this.setX(X);
		this.setY(Y);
		this.setLastX(X);
		this.setLastY(Y);
		this.MoveUp = new ImageIcon("C:/ProjetJava/Sprite/lorann_u.png");
		this.MoveDw = new ImageIcon("C:/ProjetJava/Sprite/lorann_b.png");
		this.MoveLf = new ImageIcon("C:/ProjetJava/Sprite/lorann_l.png");
		this.MoveRt = new ImageIcon("C:/ProjetJava/Sprite/lorann_r.png");
		this.MoveUpRt = new ImageIcon("C:/ProjetJava/Sprite/lorann_ur.png");
		this.MoveDwRt = new ImageIcon("C:/ProjetJava/Sprite/lorann_br.png");
		this.MoveUpLf = new ImageIcon("C:/ProjetJava/Sprite/lorann_ul.png");
		this.MoveDwLf = new ImageIcon("C:/ProjetJava/Sprite/lorann_bl.png");
		this.LorannGIF = new ImageIcon("C:/ProjetJava/Sprite/lorann.gif");
		this.setAlive(true);
	}
	
	public void MoveUP() 
	{
		this.setElemIcon(this.getMoveUp());
		this.setLastLorannMove(ControllerOrder.UP);
		this.getModel().MoveLorann(-1,0);		
	}

	public void MoveDW() 
	{
		this.setElemIcon(this.getMoveDw());
		this.setLastLorannMove(ControllerOrder.DOWN);
		this.getModel().MoveLorann(1,0);	
	}

	public void MoveLF() 
	{
		this.setElemIcon(this.getMoveLf());
		this.setLastLorannMove(ControllerOrder.LEFT);
		this.getModel().MoveLorann(0,-1);	
	}

	public void MoveRT() 
	{
		this.setElemIcon(this.getMoveRt());
		this.setLastLorannMove(ControllerOrder.RIGHT);
		this.getModel().MoveLorann(0,1);	
	}

	public void MoveUpLf() 
	{
		this.setElemIcon(this.getMoveUpLf());
		this.setLastLorannMove(ControllerOrder.UPPERLEFT);
		this.getModel().MoveLorann(-1,-1);
	}

	public void MoveUpRt() 
	{
		this.setElemIcon(this.getMoveUpRt());
		this.setLastLorannMove(ControllerOrder.UPPERRIGHT);
		this.getModel().MoveLorann(-1,1);
	}

	public void MoveDwLf() 
	{
		this.setElemIcon(this.getMoveDwLf());
		this.setLastLorannMove(ControllerOrder.DOWNLEFT);
		this.getModel().MoveLorann(1,-1);
	}

	public void MoveDwRt() 
	{
		this.setElemIcon(this.getMoveDwRt());
		this.setLastLorannMove(ControllerOrder.DOWNRIGHT);
		this.getModel().MoveLorann(1,1);
	}
	

	public ImageIcon getMoveUp() {
		return MoveUp;
	}

	public ImageIcon getMoveDw() {
		return MoveDw;
	}

	public ImageIcon getMoveLf() {
		return MoveLf;
	}

	public ImageIcon getMoveRt() {
		return MoveRt;
	}

	public ImageIcon getMoveUpRt() {
		return MoveUpRt;
	}

	public ImageIcon getMoveDwRt() {
		return MoveDwRt;
	}

	public ImageIcon getMoveUpLf() {
		return MoveUpLf;
	}

	public ImageIcon getMoveDwLf() {
		return MoveDwLf;
	}

	public ImageIcon getLorannGIF() {
		return LorannGIF;
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
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
