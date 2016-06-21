package contract;

public interface IPlayer
{
	void CheckAvailableMove(int UP_DW, int RT_LF);
	boolean CheckAvailablePosition(int UP_DW, int RT_LF );
	
	void MoveUP();
	void MoveDW();
	void MoveLF();
	void MoveRT();
	void MoveUpLf();
	void MoveUpRt();
	void MoveDwLf();
	void MoveDwRt();
}
