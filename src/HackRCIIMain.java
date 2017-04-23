import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	
	static final int S_WIDTH = 800, S_HEIGHT = 400, BUFFER = 20, KEYBOARD_START_X = 50, KEYBOARD_START_Y = 75,
			KEYBOARD_WIDTH = S_WIDTH - KEYBOARD_START_X*2, KEYBOARD_HEIGHT = S_HEIGHT - KEYBOARD_START_Y*2,
			TRANSITION = 5;
	ArrayList<Tile> tiles;
	ArrayList<Hazard> hazards, buffer;
	BufferedImage llamaImg, backgroundImg;
	Point llamaPos, oldPos;
	int transition, health;
	final int MAX_HEALTH = 10;
	int score=0;
	
	public HackRCIIMain()
	{
		this.setPreferredSize(new Dimension(S_WIDTH, S_HEIGHT));
		llamaPos = new Point(KEYBOARD_START_X + BUFFER*1 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 1/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2);
		oldPos = new Point(KEYBOARD_START_X + BUFFER*1 + (KEYBOARD_WIDTH - 11*BUFFER)/10 * 1/2, KEYBOARD_START_Y + BUFFER*1 + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * 1/2);
		transition = TRANSITION;
		llamaImg = ImageLoader.loadImage("res/llama1.png");
		backgroundImg = ImageLoader.loadImage("res/background.png");
		
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
		
		hazards = new ArrayList<>();
		buffer = new ArrayList<>();
		hazards.add(new Spear());

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
		health = MAX_HEALTH;
		this.setFocusable(true);
		this.requestFocus();
	}
	
	public static int getAbsX(int tileX)
	{
		return KEYBOARD_START_X + BUFFER*(tileX+1) + (KEYBOARD_WIDTH - 11*BUFFER)/10 * ((tileX * 2) + 1)/2;
	}
	
	public static int getAbsY(int tileY)
	{
		return KEYBOARD_START_Y + BUFFER*(tileY + 1) + (KEYBOARD_HEIGHT - 4*BUFFER)/3 * ((tileY * 2) + 1)/2; 
	}
	
	public void tick()
	{
		score=score+1;
		if(transition < TRANSITION)
			transition++;
		for(Iterator<Hazard> hazard = hazards.iterator(); hazard.hasNext(); )
		{
			Hazard h = hazard.next();
			if(h.shouldDie())
			{
				hazard.remove();
			}
			else
			{
				h.step(this);
				if(h.hitsLlama(llamaPos))
				{
					health -= h.damageDone();
					hazard.remove();
				}
			}
		}
		hazards.addAll(buffer);
		buffer.clear();
		if(Math.random() < 0.01)
		{
			double rand = Math.random();
			if(rand < 0.4)
				hazards.add(new Spear());
			else if(rand < 0.8)
				hazards.add(new Cannonball());
			else
				hazards.add(new Grenade());
		}
		
		if(health <= 0)
		{
			JOptionPane.showMessageDialog(null, "Game Over!\nScore: " + score + "\nPress OK to close.");
			System.exit(0);
		}
	}
	
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		
		gr.drawImage(backgroundImg, 0, 0, S_WIDTH, S_HEIGHT, null);
		gr.drawString("Score: "+score, S_WIDTH/2-50, S_HEIGHT-2);
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
		for(Hazard h : hazards)
			h.draw(gr);
		gr.setColor(new Color(255, 64, 64));
		gr.fillRect(100, 30, 600 * health / MAX_HEALTH, 10);
	}
}
