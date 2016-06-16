package Element.MotionLess;


public abstract class MotionLessElemFACTORY 
{
	public static final MotionLessElem BOURSE 								= new Bourse();
	public static final MotionLessElem CANDLESTICK 							= new CandleStick();
	public static final MotionLessElem CHARGER 								= new Charger();
	public static final MotionLessElem CLOSEDGATE 							= new ClosedGate();
	public static final MotionLessElem EMPTY 								= new Empty();
	public static final MotionLessElem ENERGYBALL 							= new EnergyBall();
	public static final MotionLessElem FLACON 								= new Flacon();
	public static final MotionLessElem HORIZONTALBONE 						= new HorizontalBone();
	public static final MotionLessElem MINUS 								= new Minus();
	public static final MotionLessElem OPENGATE 							= new OpenGate();
	public static final MotionLessElem PLUS 								= new Plus();
	public static final MotionLessElem RIP 									= new Rip();
	public static final MotionLessElem STATUE 								= new Statue();
	public static final MotionLessElem STONE 								= new Stone();
	public static final MotionLessElem TOMBE 								= new Tombe();
	public static final MotionLessElem VERTICALBONE 						= new VerticalBone();
	
	private static MotionLessElem	MotionLessElemFACTORY []	= 
		
		{ BOURSE, CANDLESTICK, CHARGER, CLOSEDGATE, EMPTY, ENERGYBALL, FLACON, HORIZONTALBONE, MINUS, OPENGATE, PLUS, RIP , STATUE, STONE, TOMBE, VERTICALBONE};
	
	public static MotionLessElem getElemenFromCHAR(final char MapSymbol) 
	{
		for (final MotionLessElem motionlessElem : MotionLessElemFACTORY) 
		{
			if (motionlessElem.getMapSymbol() == MapSymbol) 
			{
				return motionlessElem;
			}
		}
		return EMPTY;
	}


	public static void setMotionLessElemFACTORY(MotionLessElem motionLessElemFACTORY[]) 
	{
		MotionLessElemFACTORY = motionLessElemFACTORY;
	}
	

}
