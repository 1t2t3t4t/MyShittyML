import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Point;

public class ViewController extends JPanel {
	
	Population p;
	
	int gen = 1;
	
	public ViewController() {
		// TODO Auto-generated constructor stub
		super();
		p = new Population(150);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		draw(Dot.goal, (Graphics2D)g);
		for (Dot dot: p.dots) {
			draw(dot, (Graphics2D)g);
		}
	}
	
	private void draw(Dot dot, Graphics2D g) {
		if (dot == null) return;
		g.setColor(dot.color);
		g.fill(dot);;
	}
	
	void preparePaint() {
		p.update(this.getWidth(), this.getHeight());
		if (p.allDead()) {
			gen++;
			p.newGen(gen);
		}
	}
}
