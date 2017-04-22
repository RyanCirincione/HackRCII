import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public enum Tile 
{
	Grass("res/grass.png"), Dirt("res/dirt.png"), Water("res/water.png"), Rocks("res/rock.png"),
	Cement("res/cement.png"), Sand("res/sand.png");
	
	public BufferedImage img;
	Tile(String s)
	{
		img = ImageIO.read(new File(s));
	}
}
