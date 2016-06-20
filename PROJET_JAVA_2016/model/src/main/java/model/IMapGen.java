package model;

import java.util.ArrayList;

import Element.Element;
import Element.Motion.Hero;
import Element.Motion.AutoMotionElem.AutoMotionElem;


public interface IMapGen 
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

	/*AutoMotionElem getStupidTracker();

	void setStupidTracker(AutoMotionElem stupidTracker);

	AutoMotionElem getSmartTracker();

	void setSmartTracker(AutoMotionElem smartTracker);
	
	AutoMotionElem getBrainLessTracker();

	void setBrainLessTracker(AutoMotionElem brainLessTracker);
	
	AutoMotionElem getSadisticTracker();

	void setSadisticTracker(AutoMotionElem sadisticTracker);*/

	Hero getLorann();

	void setLorann(Hero lorann);

	
	
}
