package Element.MotionLess;

import javax.swing.ImageIcon;

import Element.*;

public class Number extends MotionLessElem 
{

	public Number(int digit) 
	{
		super( "C:/ProjetJava/Sprite/zero.png", Permeabilite.BLOCKING);
		
		switch(digit)
		{
			case 1:	this.setElemIcon(new ImageIcon("C:/ProjetJava/Sprite/un.png"));
				break;
			case 2:	this.setElemIcon(new ImageIcon("C:/ProjetJava/Sprite/deux.png"));
				break;
			case 3:	this.setElemIcon(new ImageIcon("C:/ProjetJava/Sprite/trois.png"));
				break;
			case 4:	this.setElemIcon(new ImageIcon("C:/ProjetJava/Sprite/quatre.png"));
				break;
			case 5:	this.setElemIcon(new ImageIcon("C:/ProjetJava/Sprite/cinq.png"));
				break;
			case 6:	this.setElemIcon(new ImageIcon("C:/ProjetJava/Sprite/six.png"));
				break;
			case 7:	this.setElemIcon(new ImageIcon("C:/ProjetJava/Sprite/sept.png"));
				break;
			case 8:	this.setElemIcon(new ImageIcon("C:/ProjetJava/Sprite/huit.png"));
				break;
			case 9:	this.setElemIcon(new ImageIcon("C:/ProjetJava/Sprite/neuf.png"));
				break;
			default: this.setElemIcon(new ImageIcon("C:/ProjetJava/Sprite/zero.png"));
				break;	
		}
	}
	

}
