import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HackRCIIMain extends JPanel
{
	private static final long serialVersionUID = 7963834715523348799L;

	
	World world;
	
	Rectangle camera;
	
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
	
	final int S_WIDTH = 800, S_HEIGHT = 600;
	
	public HackRCIIMain()
	{
		this.setPreferredSize(new Dimension(S_WIDTH, S_HEIGHT));
		world = new World();
		camera = new Rectangle(0, 0, S_WIDTH / 16, S_HEIGHT / 16);
	}
	
	public void tick()
	{
		world.step();
	}
	
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		Graphics2D g2d = (Graphics2D)gr;
		for(int x = 0; x < 100; x++)
			for(int y = 0; y < 100; y++)
				g2d.drawImage(world.getTile(x, y).img, x * 16 - camera.x, y * 16 - camera.y, 16, 16, null);
	}
}
