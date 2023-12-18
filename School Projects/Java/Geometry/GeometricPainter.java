import java.awt.Graphics;

import javax.swing.JFrame;


public class GeometricPainter extends JFrame {
	
	private Drawable whatIsDrawn;

	public GeometricPainter(Drawable whatIsDrawn) {
		super("Le chant du SIG");
		this.whatIsDrawn = whatIsDrawn;	
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void paint(Graphics g) {
		whatIsDrawn.draw(new SimpleDrawer(g));
	}
}    
	
