import java.awt.Graphics;
import java.awt.Point;

public interface Hazard 
{
	boolean hitsLlama(Point llama);
	void step(HackRCIIMain main);
	void draw(Graphics g);
	int damageDone();
	boolean shouldDie();
}
