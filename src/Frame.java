import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame {
	
	private final JFrame frame;
	MainPanel view;
	
	static int DELAY = 30;
	static boolean PAUSE = true;
	
	Frame() {
		frame = new JFrame("Main");
		frame.setSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		view = new MainPanel();
		view.setBackground(Color.white);
		
		frame.add(view);
		frame.setVisible(true);
	}
	
	void start() {
		Thread t = new Thread() {
			@Override
			public void run() {
				super.run();
				while(true) {
					if (!Frame.PAUSE) {
						execute();
					} else {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			private void execute() {
				view.preparePaint();
				try {
					Thread.sleep(Frame.DELAY);
					view.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		t.start();
	}
	
}
