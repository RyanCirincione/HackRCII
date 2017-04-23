import java.util.ArrayList;
import java.util.Random;

public class World 
{
	private Tile[][] floor;
	public ArrayList<Entity> entities;
	
	public World()
	{
		floor = new Tile[100][100];
		for(int x = 0; x < floor.length; x++)
			for(int y = 0; y < floor[x].length; y++);
//				floor[x][y] = Tile.Water;
		Random rand = new Random();
		int islandX = rand.nextInt(100);
		int islandY = rand.nextInt(100);
//<<<<<<< HEAD
//		for(int x = islandX - 50; x < islandX + 50; x++)
//			for(int y = islandY - 50; y < islandY + 50; y++);
////				setTile(x, y, Tile.Grass);
//=======
//		for(int x = islandX - 25; x < islandX + 25; x++)
//			for(int y = islandY - 25; y < islandY + 25; y++)
//				setTile(x, y, Tile.Grass);
//		for(int x = 0; x < floor.length; x++)
//		{
//			for(int y = 0; y < floor[x].length; y++)
//			{
//				boolean set = false;
//				if(getTile(x, y) != Tile.Water)
//				{
//					for(int i = -1; !set && i <= 1; i++)
//						for(int j = -1; ! set && j <= 1; j++)
//						{
//							if(getTile(x + i, y + j) == Tile.Water)
//							{
//								setTile(x, y, Tile.Sand);
//								set = false;
//							}
//						}
//				
//				}
//			}
//		}
//>>>>>>> ae045b548f2c9eaba5f7de6c47c8c3aa613d33f3
		entities = new ArrayList<>();
		entities.add(new Tree(islandX, islandY));
	}
	
	public void step()
	{
		entities.forEach(e -> e.step(this));
	}
	
	public boolean isFree(int x, int y)
	{
		for(Entity e : entities)
			if(e.x == x && e.y == y)
				return false;
		return true;
	}
	
	public void setTile(int x, int y, Tile tile)
	{
		x = ((x % floor.length) + floor.length) % floor.length;
		y = ((y % floor[x].length) + floor[x].length) % floor[x].length;
		floor[x][y] = tile;
	}
	
	public Tile getTile(int x, int y)
	{
		x = ((x % floor.length) + floor.length) % floor.length;
		y = ((y % floor[x].length) + floor[x].length) % floor[x].length;
		return floor[x][y];
	}
}
