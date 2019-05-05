

public class Population {
	
	Dot[] dots;
	
	int n;
	
	Dot best;
	
	public Population(int n) {
		this.n = n;
		dots = new Dot[n];
		
		for(int i=0;i<n;i++) {
			dots[i] = new Dot();
		}
	}

	
	public void update(int width, int height) {
		for(Dot dot: dots) {
			dot.update(width, height);
		}
	}
	
	boolean allDead() {
		for(Dot dot: dots) {
			if (!dot.dead) return false;
		}
		return true;
	}
	
	void runBestBoi() {
		dots = new Dot[1];
		dots[0] = best;
	}
	
	void newGen(int gen) {
		Dot best = bestBoi();
		System.out.println("END gen " + gen);
		System.out.println(best.score());
		System.out.println(best.step);
		
 		if (gen == 100) {
			Frame.DELAY = 30;
			runBestBoi();
		} else {
			Frame.DELAY = 0;
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
		
		best = new Dot();
		best.brain = boi.brain.clone();
		return boi;
	}
	
	void repop(Dot model) {
		dots = new Dot[n];
		
		for(int i=0;i<n;i++) {
			dots[i] = new Dot();
			dots[i].mutate(model);
		}
	}
}
