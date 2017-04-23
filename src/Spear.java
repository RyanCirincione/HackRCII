import java.awt.image.BufferedImage;

public class Spear extends LinearHazard 
{
	static BufferedImage img;
	
	static
	{
		img = ImageLoader.loadImage("res/spear.png");
	}
	
	@Override
	protected BufferedImage img()
	{
		return img;
	}
	
	@Override
	protected int speed()
	{
		return 1;
	}
	
	@Override
	public int damageDone()
	{
		return 1;
	}
}
