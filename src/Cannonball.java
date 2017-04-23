import java.awt.image.BufferedImage;

public class Cannonball extends LinearHazard
{
	
	static BufferedImage img;
	
	static
	{
		img = ImageLoader.loadImage("res/cannonball.png");
	}
	
	@Override
	protected BufferedImage img()
	{
		return img;
	}
	
	@Override
	protected int speed()
	{
		return 3;
	}
	
	@Override
	public int damageDone()
	{
		return 3;
	}
}
