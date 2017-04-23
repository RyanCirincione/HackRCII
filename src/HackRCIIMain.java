import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HackRCIIMain extends JPanel
{
	private static final long serialVersionUID = 7963834715523348799L;

	
	World world;
	
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
	
	final int S_WIDTH = 800, S_HEIGHT = 600;
	
	public HackRCIIMain()
	{
		this.setPreferredSize(new Dimension(S_WIDTH, S_HEIGHT));
		world = new World();
		camera = new Rectangle2D.Double(0, 0, S_WIDTH / 16, S_HEIGHT / 16);
	}
	
	public void tick()
	{
		world.step();
		camera.setRect(camera.getX() + 0.1, camera.getY() + 0.1, camera.getWidth(), camera.getHeight());
	}
	
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		Graphics2D g2d = (Graphics2D)gr;
		int cx = (int)camera.getX(), cy = (int)camera.getY(), cw = (int)camera.getWidth(), ch = (int)camera.getHeight(); 
		for(int x = cx; x < cx + cw; x++)
			for(int y = cy; y < cy + ch; y++)
			{
				g2d.drawImage(world.getTile(x, y).img, (x - cx) * 16, (y - cy) * 16, 16, 16, null);
			}
	}
}
