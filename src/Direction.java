
public enum Direction 
{
	Left(-1, 0), Right(1, 0), Top(0, 1), Bottom(0, -1);
	
	//Returns a direction where 0 = left and it goes counter-clockwise to 3
	public static Direction fromIndex(int index)
	{
		switch(index)
		{
		case 0:
			return Left;
		case 1:
			return Top;
		case 2:
			return Right;
		case 3:
			return Bottom;
		}
		return null;
	}
	
	Direction(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	int x, y;
}
