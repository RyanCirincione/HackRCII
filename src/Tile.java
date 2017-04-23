import java.awt.Point;

public class Tile
{
	public char key;
	public Point location;
	
	public Tile(char k, int x, int y)
	{
		key = k;
		location = new Point(x, y);
	}
}
