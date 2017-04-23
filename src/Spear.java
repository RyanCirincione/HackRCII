import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Spear implements Hazard 
{
	private static Random rand;
	static BufferedImage img;
	
	static
	{
		rand = new Random();
		img = ImageLoader.loadImage("res/spear.png");
	}
	
	Direction direction;
	Point position;
	AffineTransform trans;
	
	public Spear()
	{
		direction = Direction.fromIndex(rand.nextInt(4));
		int x, y;
		switch(direction)
		{
		case Top:
		case Bottom:
			x = HackRCIIMain.getAbsX(rand.nextInt(10));
			y = HackRCIIMain.getAbsY(1 + direction.y * -2);
			break;
		case Left:
		case Right:
			x = HackRCIIMain.getAbsX(5 + direction.x * -6);
			y = HackRCIIMain.getAbsY(rand.nextInt(3));
			break;
		default:
			throw new RuntimeException("Send help, it broke");
		}
		position = new Point(x, y);
		trans = new AffineTransform();
	}

	@Override
	public boolean hitsLlama(Point llama) 
	{
		return llama.distanceSq(position) <= 32 * 32;
	}

	@Override
	public void step() 
	{
		position.x += direction.x;
		position.y += direction.y;
	}

	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(img, position.x, position.y, null);
	}
}
