import java.awt.Graphics2D;
import java.awt.Point;

public interface Hazard 
{
	boolean hitsLlama(Point llama);
	void step();
	void draw(Graphics2D g);
}
