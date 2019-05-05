import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

public class Dot extends Ellipse2D.Float {
	
	static Dot goal = new Dot() {
		@Override
		void config() {
			super.config();
			width = 10;
			height = 10;
			x = 700;
			y = 700;
			color = Color.red;
		}
	};
	
	int vx = 0;
	int vy = 0;
	
	Color color;
	int step = 0;
	
	boolean win = false;
	boolean dead = false;
	boolean champion = false;
	
	Brain brain;
	
	Dot() {
		brain = new Brain();
		config();
	}
	
	void config() {
		width = 5;
		height = 5;
		x = 50;
		y = 50;
		color = Color.blue;
	}
	
	void setChamp() {
		champion = true;
		color = Color.yellow;
	}
	
	void kill() {
		dead = true;
	}
	
	void mutate(Dot model) {
		brain = model.brain.clone();
		brain.tweak();
	}

	void update(int width, int height) {
		if (dead || win) return;
		if (step < brain.direction.length) {
			vx += brain.direction[step].x;
			vy += brain.direction[step].y;
			x += vx;
			y += vy;
		} else {
			dead = true;
		}
		
		if (getX() >= width || getY() >= height || getX() <= 0 || getY() <= 0) dead = true;
		if (Dot.goal.contains(x, y)) {
			win = true;
			dead = true;
		}
		step++;
	}
	
	double score() {
		double score = 0.0;
		Point2D p = new Point((int)x, (int)y);
		Point2D gp = new Point((int)Dot.goal.x, (int)Dot.goal.y);
		score += 1000.0 / (p.distance(gp) + 1);
		if (win) {
			score += 500 * 1000.0 / (step);
		} 
		return score;
	}
	
}
