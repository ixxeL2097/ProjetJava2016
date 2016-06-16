package Element;

import javax.swing.ImageIcon;

public abstract class Element
{
	protected Permeabilite permea;
	protected String IconName;
	protected ImageIcon ElemIcon;
	
	public synchronized Permeabilite getPermea() 
	{
		return permea;
	}

	public synchronized void setPermea(Permeabilite permea) 
	{
		this.permea = permea;
	}

	public String getIconName() 
	{
		return IconName;
	}
	public void setIconName(String iconName) 
	{
		IconName = iconName;
	}
	
	public synchronized ImageIcon getElemIcon() {
		return ElemIcon;
	}

	public synchronized void setElemIcon(ImageIcon image) {
		this.ElemIcon = image;
	}

	public Element(){}
	
	public Element(String picture, Permeabilite permea)
	{
		this.setPermea(permea);
		this.setIconName(picture);
		this.setElemIcon(new ImageIcon(picture)); 
	}

}
