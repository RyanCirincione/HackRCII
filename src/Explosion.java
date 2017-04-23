import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Explosion implements Hazard
{
	int x, y, radius, maxRadius;
	
	public Explosion(int x, int y, int maxRadius)
	{
		this.x = x;
		this.y = y;
		this.radius = 0;
		this.maxRadius = maxRadius;
	}
	
	@Override
	public boolean hitsLlama(Point llama) 
	{
		return llama.distanceSq(x, y) <= 16 * radius;
	}

	@Override
	public void step() 
	{
		radius += 1;
	}

	@Override
	public void draw(Graphics g) 
	{
		g.setColor(Color.red);
		g.drawOval(x - radius, y - radius, radius * 2, radius * 2);		
	}

	@Override
	public int damageDone() 
	{
		return 2;
	}

	@Override
	public boolean shouldDie() 
	{
		return radius >= maxRadius;
	}
	
}
