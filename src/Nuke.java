import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Nuke implements Hazard 
{
	private static Random rand;
	private static BufferedImage img;
	
	static
	{
		rand = new Random();
		img = ImageLoader.loadImage("res/nuke.png");
	}
	
	int x, y;
	float progress;
	
	public Nuke()
	{
		x = HackRCIIMain.getAbsX(rand.nextInt(10));
		y = HackRCIIMain.getAbsX(rand.nextInt(3));
	}

	@Override
	public boolean hitsLlama(Point llama) 
	{
		return false;
	}

	@Override
	public void step(HackRCIIMain main) 
	{
		progress += 0.01;
		if(progress >= 1)
		{
			main.buffer.add(new Explosion(x, y, 128));
		}
	}

	@Override
	public void draw(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(img, x + 10, (int)(y * progress), null);
	}

	@Override
	public int damageDone() {
		return 0;
	}

	@Override
	public boolean shouldDie() {
		return progress >= 1;
	}

}
