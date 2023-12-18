import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class SimpleDrawer {

	private Graphics2D g2D;
	private static Color POINT_COLOR = Color.BLACK;
	private static Color LINE_COLOR = Color.BLUE;
	
	public SimpleDrawer(Graphics g) {
		this.g2D = (Graphics2D) g;
	}
	
	public void drawPoint(int x, int y) {
		g2D.setColor(POINT_COLOR);
		g2D.fillOval( x + 400-3, - y + 300 -3, 6, 6);
	}
	
	public void drawLineSegment(int x0, int y0, int x1, int y1) {
		g2D.setColor(LINE_COLOR);
		g2D.drawLine( x0 + 400,   - y0 + 300,  x1 + 400,  - y1 + 300);		
	}
}
