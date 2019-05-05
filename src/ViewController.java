import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

public class ViewController extends JPanel {
	
	Population p;
	
	ArrayList<Shape> obstrucles;
	
	boolean pause = false;
	
	public ViewController() {
		// TODO Auto-generated constructor stub
		super();
		p = new Population(1000);
		obstrucles = new ArrayList<Shape>();
	}
	
	void addObstrucles(int minx, int miny, int maxx, int maxy) {
		if (maxy < miny) {
			int t = miny;
			miny = maxy;
			maxy = t; 
		} 
		
		if (maxx < minx) {
			int t = minx;
			minx = maxx;
			maxx = t; 
		}
		
		int width = Math.abs(maxx - minx);
		int height = Math.abs(maxy - miny);
		obstrucles.add(new Rectangle(minx, miny, width, height));
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		for(Shape s: obstrucles) {
			Graphics2D g2 = (Graphics2D)g;
			g2.fill(s);
		}
		draw(Dot.goal, (Graphics2D)g);
		for (Dot dot: p.dots) {
			draw(dot, (Graphics2D)g);
		}
	}
	
	private void draw(Dot dot, Graphics2D g) {
		if (dot == null) return;
		g.setColor(dot.color);
		g.fill(dot);
		
		if (dot.champion) {
			g.setColor(Color.black);
			g.draw(dot);
		}
	}
	
	void preparePaint() {
		p.update(this.getWidth(), this.getHeight());
		for(Shape s: obstrucles) {
			for (Dot dot: p.dots) {
				if (s.contains(dot.getBounds())) {
					dot.dead = true;
				}
			}
		}
		if (p.allDead()) {
			p.newGen();
		}
	}
	
	void type(char c) {
		switch (c) {
		case 'c':
			p.runBestBoi();
			break;
		case 'r':
			obstrucles.clear();
			break;
		case 'p':
			System.out.println("===PAUSE===");
			Frame.PAUSE = true;
			break;
		case 's':
			System.out.println("===CONTINUE===");
			Frame.PAUSE = false;
			break;
		case ' ':
			Frame.DELAY = 0;
			break;
		default:
			break;
		}
	}
}
