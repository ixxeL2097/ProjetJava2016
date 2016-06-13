package Element;

import javax.swing.ImageIcon;

public abstract class Element
{
	protected Permeabilite permea;
	protected String IconName;
	protected ImageIcon ElemIcon;
	
	public Permeabilite getPermea() 
	{
		return permea;
	}

	public void setPermea(Permeabilite permea) 
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
	
	public ImageIcon getElemIcon() {
		return ElemIcon;
	}

	public void setElemIcon(ImageIcon image) {
		this.ElemIcon = image;
	}

	public Element(){}
	
	public Element(String picture, Permeabilite permea)
	{
		this.permea=permea;
		this.IconName=picture;
		this.ElemIcon= new ImageIcon(picture);
	}

}
