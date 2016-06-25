package Thread.mario;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PlayMario {
	static JFrame frame;
	static int cnt = 0;

	public static void main(String[] args) {
		frame = new JFrame("Walking Marios");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		JPanel bt = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
		JButton bt1 = new JButton("Mario Run!");
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RunnableMario m = new RunnableMario(frame, cnt++);
                new Thread(m).start();
			}
		});
		bt.add(bt1);

		frame.add(bt, "South");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}

class RunnableMario extends Mario implements Runnable {
	private JFrame frame;

	public RunnableMario(JFrame f, int pos) {
		super(f, pos);
		this.frame = f;
	}

	public void run() {
		while (true) {
			Graphics2D g = (Graphics2D) frame.getGraphics();
			draw(g);
			g.dispose();

			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}