import java.util.Random;

public class Tree extends Entity 
{
	int age;
	final float CHANCE_GERMINATE = 0.01f;
	static Random rand;
	
	static
	{
		rand = new Random();
	}
	
	public Tree(int x, int y)
	{
		super(x, y);
	}
	
	@Override
	public void step(World world)
	{
		super.step(world);
		if(Math.random() < CHANCE_GERMINATE)
		{
			double x = Math.random() * 2 - 1;
			double y = Math.random() * 2 - 1;
			int xTile = (int)Math.round(x);
			int yTile = (int)Math.round(y);
			if(!world.isFree(xTile, yTile))
				world.entities.add(new Tree(xTile, yTile));
		}
	}
}
