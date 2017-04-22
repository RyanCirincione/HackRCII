import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Tile 
{
	Grass("res/grass.png"), Dirt("res/dirt.png"), Water("res/water.png"), Rocks("res/rock.png"),
	Cement("res/cement.png"), Sand("res/sand.png");
	
	public BufferedImage img;
	Tile(String s)
	{
		BufferedImage temp = null;
		try
		{
			temp = ImageIO.read(new File("res/unknown.png"));
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		try
		{
			img = ImageIO.read(new File(s));
		} catch (IOException e)
		{
			img = temp;
		}
	}
}
