import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Grenade implements Hazard 
{
	private static Random rand;
	private static BufferedImage img;
	
	static
	{
		rand = new Random();
		img = ImageLoader.loadImage("res/grenade.png");
	}
	
	Point position;
	int targetX, targetY, timer;
	double angle;
	
	public Grenade()
	{
		position = new Point(rand.nextInt(800), rand.nextInt(600));
		angle = 0;
		timer = -1;
		do
		{
			targetX = HackRCIIMain.getAbsX(rand.nextInt(10));
			targetY = HackRCIIMain.getAbsY(rand.nextInt(3));
		} while(position.distance(targetX, targetY) <  100);
	}
	
	
	@Override
	public boolean hitsLlama(Point llama) 
	{
		return false;
	}

	@Override
	public void step(HackRCIIMain main) 
	{
		if(position.distance(targetX, targetY) > 16)
		{
			double dx = position.x - targetX;
			double dy = position.y - targetY;
			double length = Math.sqrt(dx * dx + dy * dy);
			dx = dx / length;
			dy = dy / length;
			position.x -= dx;
			position.y -= dy;
		}
		else if(timer == -1)
		{
			position.setLocation(targetX, targetY);
			timer = 120;
		}
		else if(timer > 0)
		{
			timer--;
			if(timer == 0)
				main.buffer.add(new Explosion(targetX, targetY, 64));
		}
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.rotate(angle, position.x + img.getWidth() / 2, position.y + img.getHeight() / 2);
		g2.drawImage(img, position.x, position.y, null);
		g2.rotate(-angle, position.x + img.getWidth() / 2, position.y + img.getHeight() / 2);
		angle += 0.05;
	}

	@Override
	public int damageDone() 
	{
		return 0;
	}

	@Override
	public boolean shouldDie() 
	{
		return timer == 0;
	}

}
