import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Cannonball extends Spear
{
	
	static BufferedImage img;
	
	static
	{
		img = ImageLoader.loadImage("res/cannonball.png");
	}
	
	@Override
	public void step() 
	{
		position.x += direction.x * 3;
		position.y += direction.y * 3;
	}
	
	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(img, position.x, position.y, null);
	}
	
	@Override
	public int damageDone()
	{
		return 3;
	}
}
