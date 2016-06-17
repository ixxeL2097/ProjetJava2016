package Element.Motion;
import javax.swing.ImageIcon;

import Element.*;
import contract.ControllerOrder;

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
	protected boolean HasMoved = false;
	protected boolean Alive ;
	protected ControllerOrder LastLorannMove=ControllerOrder.UP;
	private boolean Shootable=true;
	
	
	//---------------------------------------CONSTRUCTEURS----------------------------------------------------------------------------

	public Hero(int Y, int X) 
	{
		super(HERO_MOVE, Permeabilite.HERO);
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
	
	
	
	
	
	
}
