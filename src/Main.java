import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Main {

	public static void main(String[] args) {
		Frame f = new Frame();
		f.view.addMouseListener(new MouseListener() {
			
			int minx;
			int miny;
			int maxx;
			int maxy;
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("click");
				Dot.goal.x = e.getX();
				Dot.goal.y = e.getY();
				f.view.p.reset();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("pressed");
				minx = e.getX();
				miny = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("release");
				maxx = e.getX();
				maxy = e.getY();
				f.view.addObstrucles(minx, miny, maxx, maxy);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("EXIT");
			}
		});
		
		f.view.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getX());
			}
		});
		
		f.view.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				f.view.type(e.getKeyChar());
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		f.view.setFocusable(true);
		
		f.start();
	}
	
}
