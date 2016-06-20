package Element.Motion.AutoMotionElem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Element.Permeabilite;
import model.Model;

public class Projectile extends AutoMotionElem implements Runnable, ActionListener
{
	private static String MAGIC_BALL="C:/ProjetJava/Sprite/250ms.gif";
	private static String MAGIC_FIREBALL = "C:/ProjetJava/Sprite/fireball_1.png";
	private final ImageIcon GREEN = new ImageIcon("C:/ProjetJava/Sprite/fireball_1.png");
	private final ImageIcon BLUE = new ImageIcon("C:/ProjetJava/Sprite/fireball_2.png");
	private final ImageIcon PURPLE = new ImageIcon("C:/ProjetJava/Sprite/fireball_3.png");
	private final ImageIcon RED = new ImageIcon("C:/ProjetJava/Sprite/fireball_4.png");
	private final ImageIcon YELLOW = new ImageIcon("C:/ProjetJava/Sprite/fireball_5.png");
	private ArrayList<ImageIcon> ChangeColor;
	private int SpriteNumber=0;

	public Projectile(Model model, int Y, int X) 
	{	
		super(model, MAGIC_FIREBALL, Permeabilite.MISSILE);
		this.MoveTimer=new Timer(250,this);
		this.setX(X);
		this.setY(Y);
		this.setIA(new ShootingBehavior(this));
		this.ChangeColor = new ArrayList<ImageIcon>();
		this.fillArrayList();
		this.run();
	}

	public void run() 
	{
		this.getMoveTimer().start();
	}

	public void actionPerformed(ActionEvent e) 
	{
		this.changeSpriteColor();
		this.getIA().AutoMove();
	}
	
	public void fillArrayList()
	{
		this.getChangeColor().add(this.getGREEN());
		this.getChangeColor().add(this.getBLUE());
		this.getChangeColor().add(this.getPURPLE());
		this.getChangeColor().add(this.getRED());
		this.getChangeColor().add(this.getYELLOW());
	}
	
	public void changeSpriteColor()
	{
		this.setSpriteNumber(this.getSpriteNumber()+1);
		if(this.getSpriteNumber() < this.getChangeColor().size())
		{
			this.setElemIcon(this.getChangeColor().get(this.getSpriteNumber()));
		}
		else
		{
			this.setSpriteNumber(0);
			this.setElemIcon(this.getChangeColor().get(this.getSpriteNumber()));
		}
	}

	public ImageIcon getGREEN() {
		return GREEN;
	}

	public ImageIcon getBLUE() {
		return BLUE;
	}

	public ImageIcon getPURPLE() {
		return PURPLE;
	}

	public ImageIcon getRED() {
		return RED;
	}

	public ImageIcon getYELLOW() {
		return YELLOW;
	}

	public int getSpriteNumber() {
		return SpriteNumber;
	}

	public void setSpriteNumber(int spriteNumber) {
		SpriteNumber = spriteNumber;
	}

	public ArrayList<ImageIcon> getChangeColor() {
		return ChangeColor;
	}

	public void setChangeColor(ArrayList<ImageIcon> changeColor) {
		ChangeColor = changeColor;
	}
	
	
	
	

}
