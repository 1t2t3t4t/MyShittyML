import java.awt.Point;

public class Brain implements Cloneable {
	
	 Point[] direction;
	 
	 Brain() {
		 direction = new Point[1000];
		 think();
	 }
	
	 void think() {
		for(int i=0;i<direction.length;i++) {
			direction[i] = new Point(plusOrMinus(1), plusOrMinus(1));
		}
	}
	 
	 void tweak() {
		 for(int i=0;i<direction.length;i++) {
			if (0.05 >= Math.random()) {
				direction[i] = new Point(plusOrMinus(1), plusOrMinus(1));
			}
		}
	 }
	 
	 int plusOrMinus(int n) {
		return (int)Math.pow(-1, (int)(Math.random() *2) + 1) * n;
	}
	 
	protected Brain clone() {
		 Brain clone = new Brain();
		 clone.direction = direction.clone();
		 return clone;
	}

}
