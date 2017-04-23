import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HackRCIIMain extends JPanel
{
	private static final long serialVersionUID = 7963834715523348799L;

	Rectangle2D camera;
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hack RC II");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		HackRCIIMain panel = new HackRCIIMain();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask(){
			public void run()
			{
				panel.tick();
				panel.repaint();
			}
		}, 0, 1000/60);
	}
	
	final int S_WIDTH = 800, S_HEIGHT = 400, BUFFER = 20, KEYBOARD_START_X = 50, KEYBOARD_START_Y = 75,
			KEYBOARD_WIDTH = S_WIDTH - KEYBOARD_START_X*2, KEYBOARD_HEIGHT = S_HEIGHT - KEYBOARD_START_Y*2,
			TRANSITION = 5;
	ArrayList<Tile> tiles;
	BufferedImage llamaImg;
	Point llamaPos, oldPos;
	int transition;
	
	public HackRCIIMain()
	{
		this.setPreferredSize(new Dimension(S_WIDTH, S_HEIGHT));
		llamaPos = new Point(KEYBOARD_START_X + BUFFER*1 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 1/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2);
		oldPos = new Point(KEYBOARD_START_X + BUFFER*1 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 1/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2);
		transition = TRANSITION;
		try
		{
			llamaImg = ImageIO.read(new File("res/llama1.png"));
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		tiles = new ArrayList<Tile>();
		char[][] letters = {
				{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
				{'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';'},
				{'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/'}
		};
		for(int y = 0; y < letters.length; y++)
		{
			for(int x = 0; x < letters[y].length; x++)
			{
				tiles.add(new Tile(letters[y][x], getAbsX(x), getAbsY(y)));
			}
		}

		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				if(transition == TRANSITION)
				{
					for(Tile t : tiles)
						if(e.getKeyChar() == t.key)
						{
							oldPos.setLocation(llamaPos);
							llamaPos.setLocation(t.location);
							transition = 0;
							break;
						}
				}
			}
		});
		this.setFocusable(true);
		this.requestFocus();
	}
	
	public int getAbsX(int tileX)
	{
		return KEYBOARD_START_X + BUFFER*(tileX+1) + (KEYBOARD_WIDTH - 11*BUFFER)/10 * ((tileX * 2) + 1)/2;
	}
	
	public int getAbsY(int tileY)
	{
		return KEYBOARD_START_Y + BUFFER*(tileY + 1) + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * ((tileY * 2) + 1)/2; 
	}
	
	public void tick()
	{
		if(transition < TRANSITION)
			transition++;
	}
	
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		
		gr.setColor(Color.green);
		gr.drawRect(KEYBOARD_START_X, KEYBOARD_START_Y, KEYBOARD_WIDTH, KEYBOARD_HEIGHT);
		gr.setColor(Color.gray);
		for(Tile t : tiles)
		{
			gr.drawRoundRect(t.location.x - (KEYBOARD_WIDTH - 11*BUFFER)/10/2,
					t.location.y - (KEYBOARD_HEIGHT - 4*BUFFER)/3/2, (KEYBOARD_WIDTH - 11*BUFFER)/10,
					(KEYBOARD_HEIGHT - 4*BUFFER)/3, 3, 3);
			gr.drawString((""+t.key).toUpperCase(), t.location.x - 4, t.location.y + 4);
		}
		gr.drawImage(llamaImg, llamaPos.x - (int)((llamaPos.x - oldPos.x) * (1.0 - (double)transition/TRANSITION)) - 16,
				llamaPos.y - (int)((llamaPos.y - oldPos.y) * (1.0 - (double)transition/TRANSITION)) - 16, 32, 32, null);
	}
}
