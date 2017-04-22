import java.util.Random;

public class World 
{
	private Tile[][] floor;
	
	public World()
	{
		floor = new Tile[100][100];
		for(int x = 0; x < floor.length; x++)
			for(int y = 0; y < floor[x].length; y++)
				floor[x][y] = Tile.Water;
		Random rand = new Random();
		int islandX = rand.nextInt(100);
		int islandY = rand.nextInt(100);
		for(int x = islandX - 50; x < islandX + 50; x++)
			for(int y = islandY - 50; y < islandY + 50; y++)
				setTile(x, y, Tile.Grass);
	}
	
	public void setTile(int x, int y, Tile tile)
	{
		x = ((x % floor.length) + floor.length) % floor.length;
		y = ((y % floor[x].length) + floor[x].length) % floor[x].length;
		floor[x][y] = tile;
	}
}
