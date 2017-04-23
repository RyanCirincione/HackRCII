import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HackRCIIMain extends JPanel
{
	private static final long serialVersionUID = 7963834715523348799L;

	World world;
	
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
			KEYBOARD_WIDTH = S_WIDTH - KEYBOARD_START_X*2, KEYBOARD_HEIGHT = S_HEIGHT - KEYBOARD_START_Y*2;
	ArrayList<Tile> tiles;
	
	public HackRCIIMain()
	{
		this.setPreferredSize(new Dimension(S_WIDTH, S_HEIGHT));
		world = new World();
		
		tiles = new ArrayList<Tile>();
		tiles.add(new Tile('q', KEYBOARD_START_X + BUFFER*1 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 1/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2));
		tiles.add(new Tile('w', KEYBOARD_START_X + BUFFER*2 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 3/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2));
		tiles.add(new Tile('e', KEYBOARD_START_X + BUFFER*3 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 5/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2));
		tiles.add(new Tile('r', KEYBOARD_START_X + BUFFER*4 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 7/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2));
		tiles.add(new Tile('t', KEYBOARD_START_X + BUFFER*5 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 9/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2));
		tiles.add(new Tile('y', KEYBOARD_START_X + BUFFER*6 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 11/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2));
		tiles.add(new Tile('u', KEYBOARD_START_X + BUFFER*7 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 13/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2));
		tiles.add(new Tile('i', KEYBOARD_START_X + BUFFER*8 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 15/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2));
		tiles.add(new Tile('o', KEYBOARD_START_X + BUFFER*9 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 17/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2));
		tiles.add(new Tile('p', KEYBOARD_START_X + BUFFER*10 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 19/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2));

		tiles.add(new Tile('a', KEYBOARD_START_X + BUFFER*1 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 1/2, KEYBOARD_START_Y + BUFFER*2 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 3/2));
		tiles.add(new Tile('s', KEYBOARD_START_X + BUFFER*2 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 3/2, KEYBOARD_START_Y + BUFFER*2 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 3/2));
		tiles.add(new Tile('d', KEYBOARD_START_X + BUFFER*3 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 5/2, KEYBOARD_START_Y + BUFFER*2 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 3/2));
		tiles.add(new Tile('f', KEYBOARD_START_X + BUFFER*4 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 7/2, KEYBOARD_START_Y + BUFFER*2 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 3/2));
		tiles.add(new Tile('g', KEYBOARD_START_X + BUFFER*5 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 9/2, KEYBOARD_START_Y + BUFFER*2 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 3/2));
		tiles.add(new Tile('h', KEYBOARD_START_X + BUFFER*6 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 11/2, KEYBOARD_START_Y + BUFFER*2 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 3/2));
		tiles.add(new Tile('j', KEYBOARD_START_X + BUFFER*7 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 13/2, KEYBOARD_START_Y + BUFFER*2 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 3/2));
		tiles.add(new Tile('k', KEYBOARD_START_X + BUFFER*8 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 15/2, KEYBOARD_START_Y + BUFFER*2 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 3/2));
		tiles.add(new Tile('l', KEYBOARD_START_X + BUFFER*9 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 17/2, KEYBOARD_START_Y + BUFFER*2 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 3/2));
		tiles.add(new Tile(';', KEYBOARD_START_X + BUFFER*10 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 19/2, KEYBOARD_START_Y + BUFFER*2 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 3/2));

		tiles.add(new Tile('z', KEYBOARD_START_X + BUFFER*1 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 1/2, KEYBOARD_START_Y + BUFFER*3 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 5/2));
		tiles.add(new Tile('x', KEYBOARD_START_X + BUFFER*2 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 3/2, KEYBOARD_START_Y + BUFFER*3 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 5/2));
		tiles.add(new Tile('c', KEYBOARD_START_X + BUFFER*3 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 5/2, KEYBOARD_START_Y + BUFFER*3 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 5/2));
		tiles.add(new Tile('v', KEYBOARD_START_X + BUFFER*4 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 7/2, KEYBOARD_START_Y + BUFFER*3 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 5/2));
		tiles.add(new Tile('b', KEYBOARD_START_X + BUFFER*5 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 9/2, KEYBOARD_START_Y + BUFFER*3 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 5/2));
		tiles.add(new Tile('n', KEYBOARD_START_X + BUFFER*6 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 11/2, KEYBOARD_START_Y + BUFFER*3 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 5/2));
		tiles.add(new Tile('m', KEYBOARD_START_X + BUFFER*7 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 13/2, KEYBOARD_START_Y + BUFFER*3 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 5/2));
		tiles.add(new Tile(',', KEYBOARD_START_X + BUFFER*8 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 15/2, KEYBOARD_START_Y + BUFFER*3 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 5/2));
		tiles.add(new Tile('.', KEYBOARD_START_X + BUFFER*9 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 17/2, KEYBOARD_START_Y + BUFFER*3 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 5/2));
		tiles.add(new Tile('/', KEYBOARD_START_X + BUFFER*10 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 19/2, KEYBOARD_START_Y + BUFFER*3 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 5/2));
		//width = 11 * buffer + 10 * width
		//height = 4 * buffer + 3 * height
	}
	
	public void tick()
	{
		world.step();
	}
	
	public void paintComponent(Graphics gr)
	{
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
	}
}
