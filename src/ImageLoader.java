import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader 
{
	
	private static BufferedImage unknown;
	static
	{
		try 
		{
			unknown = ImageIO.read(new File("res/unknown.png"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static BufferedImage loadImage(String path)
	{
		try
		{
			return ImageIO.read(new File(path));
		} catch (IOException e)
		{
			e.printStackTrace();
			return unknown;
		}
	}
}
