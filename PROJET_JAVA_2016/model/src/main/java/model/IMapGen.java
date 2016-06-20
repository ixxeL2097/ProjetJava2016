package model;

import Element.Element;
import Element.Motion.Hero;
import Element.Motion.AutoMotionElem.Daemon.DemonBlind;
import Element.Motion.AutoMotionElem.Daemon.DemonRandom;
import Element.Motion.AutoMotionElem.Daemon.DemonSadistic;
import Element.Motion.AutoMotionElem.Daemon.DemonTracker;

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

	DemonBlind getStupidTracker();

	void setStupidTracker(DemonBlind stupidTracker);

	DemonTracker getSmartTracker();

	void setSmartTracker(DemonTracker smartTracker);
	
	DemonRandom getBrainLessTracker();

	void setBrainLessTracker(DemonRandom brainLessTracker);

	Hero getLorann();

	void setLorann(Hero lorann);

	DemonSadistic getSadisticTracker();

	void setSadisticTracker(DemonSadistic sadisticTracker);
	
}
