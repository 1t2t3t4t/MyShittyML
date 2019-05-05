

public class Population {
	
	Dot[] dots;
	
	int n;
	
	Dot best;
	int minStep = 1000;
	
	int allDie = 0;
	
	int gen = 1;
	
	public Population(int n) {
		this.n = n;
		Frame.DELAY = 0;
		dots = new Dot[n];
		
		for(int i=0;i<n;i++) {
			dots[i] = new Dot();
		}
	}

	
	public void update(int width, int height) {
		for(Dot dot: dots) {
			dot.update(width, height);
			if (dot.step > minStep) {
				dot.dead = true;
			}
		}
	}
	
	boolean allDead() {
		for(Dot dot: dots) {
			if (!dot.dead) return false;
		}
		return true;
	}
	
	boolean noWinner() {
		for(Dot dot: dots) {
			if (dot.win) return false;
		}
		
		return true;
	}
	
	void runBestBoi() {
		dots = new Dot[1];
		dots[0] = best;
	}
	
	void newGen() {
		gen++;
		double score = best != null ? best.score() : 0;
		bestBoi();
		System.out.println("END gen " + gen);
		
		if (noWinner() && best.score() <= score) {
			allDie++;
		}
		
 		if (allDie >= 10) {
			repop(null);
			reset();
		} else {
			repop(best);
		}
	}
	
	Dot bestBoi() {
		Dot boi = null;
		double maxScore = 0.0;
		for(Dot dot: dots) {
			if (dot.score() > maxScore) {
				maxScore = dot.score();
				boi = dot;
			}
		}
		
		if (best != null && boi.score() >= best.score()) {
			best = new Dot();
			best.brain = boi.brain.clone();
		} else if (best == null) {
			best = new Dot();
			best.brain = boi.brain.clone();
		}

		System.out.println(boi.score());
		System.out.println(boi.step);
		System.out.println(boi.win);

		if (boi.win) {
			minStep = boi.step;
			Frame.DELAY = 30;
		}
		
		return boi;
	}
	
	void reset() {
		best = null;
		minStep = 1000;
		allDie = 0;
		gen = 1;
		Frame.DELAY = 0;
	}
	
	void repop(Dot model) {
		Dot[] dots = new Dot[n];
		
		for(int i=0;i<n;i++) {
			dots[i] = new Dot();
			if (model != null) {
				dots[i].mutate(model);
			} 
			
			if (i >= n - 10) {
				dots[i].brain.think();
			}
		}
		
		if (model != null) {
			dots[0].brain = model.brain.clone();
			dots[0].setChamp();	
		}
		
		this.dots = dots;
	}
}
