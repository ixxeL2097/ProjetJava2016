package model;

import java.util.ArrayList;

import Element.Element;
import Element.Motion.Hero;
import Element.Motion.AutoMotionElem.AutoMotionElem;


public interface IMapGen 		// MapGen interface 
{		
	Model getModel();
	
	int getMapLevel();

	void setMapLevel(int mapLevel);
	
	String getMapName();

	void setMapName(String mapName);

	void setModel(Model model);

	Element getElemMtx(int y, int x);

	void setElemMtx(Element elemMtx, int y, int x);
	
	ArrayList<AutoMotionElem> getMobilList();

	Hero getLorann();

	void setLorann(Hero lorann);

	
	
}
