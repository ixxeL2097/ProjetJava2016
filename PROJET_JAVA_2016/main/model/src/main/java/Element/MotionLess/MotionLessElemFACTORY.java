package Element.MotionLess;

public abstract class MotionLessElemFACTORY 						// MotionlessElement factory, instanciate all non motion elements
{
	public static final MotionLessElem PURSE 								= new Purse();
	public static final MotionLessElem CANDLESTICK 							= new CandleStick();
	public static final MotionLessElem CHARGER 								= new Charger();
	public static final MotionLessElem CLOSEDGATE 							= new ClosedGate();
	public static final MotionLessElem EMPTY 								= new Empty();
	public static final MotionLessElem ENERGYBALL 							= new EnergyBall();
	public static final MotionLessElem FLASK 								= new Flask();
	public static final MotionLessElem HORIZONTALBONE 						= new HorizontalBone();
	public static final MotionLessElem MINUS 								= new Minus();
	public static final MotionLessElem OPENGATE 							= new OpenGate();
	public static final MotionLessElem PLUS 								= new Plus();
	public static final MotionLessElem TOMBSTONE 							= new TombStone();
	public static final MotionLessElem IDOL 								= new Idol();
	public static final MotionLessElem STONE 								= new Stone();
	public static final MotionLessElem GRAVE 								= new Grave();
	public static final MotionLessElem VERTICALBONE 						= new VerticalBone();
	
	private static MotionLessElem	MotionLessElemFACTORY []	= 
		
		{ PURSE, CANDLESTICK, CHARGER, CLOSEDGATE, EMPTY, ENERGYBALL, FLASK, HORIZONTALBONE, MINUS, OPENGATE, PLUS, TOMBSTONE , IDOL, STONE, GRAVE, VERTICALBONE}; // static motionelement's array that stores all non motion elements
	
	public static MotionLessElem getElemenFromCHAR(final char MapSymbol) 		// return char identification of a non motion element
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
