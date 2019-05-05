import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame {
	
	private JFrame frame;
	ViewController view;
	
	static int DELAY = 30;
	
	Frame() {
		frame = new JFrame("Main");
		frame.setSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		view = new ViewController();
		view.setBackground(Color.white);
		
		frame.add(view);
		frame.setVisible(true);
	}
	
	void start() {
		Thread t = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				while(true) {
					view.preparePaint();
					try {
						Thread.sleep(Frame.DELAY);
						view.repaint();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		t.start();
	}
	
}
