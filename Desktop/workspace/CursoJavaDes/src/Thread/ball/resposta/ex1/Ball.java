package Thread.ball.resposta.ex1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class Ball extends Thread {
	private JPanel box;
	private static final int XSIZE = 10;
	private static final int YSIZE = 10;
	private int inicialX = 0;
	private int inicialY = 0;
	private int x = 0;
	private int y = 0;
	private int dx = 2;
	private int dy = 2;
	private Color color = Color.black;

	public Ball(JPanel b) {
		box = b;
	}

	public Ball(JPanel b, Color c) {
		box = b;
		color = c;
	}

	public void setX(int x) {
		this.inicialX = x;
		this.x = x;
	}

	public void setY(int y) {
		this.inicialY = y;
		this.y = y;
	}

	public void draw() {
		Graphics g = box.getGraphics();
		g.setColor(color);
		g.fillOval(x, y, XSIZE, YSIZE);
		g.dispose();
	}

	public void move() {
		Graphics g = box.getGraphics();
		g.setXORMode(box.getBackground());
		g.setColor(color);
		g.fillOval(x, y, XSIZE, YSIZE);
		x += dx;
		y += dy;
		Dimension d = box.getSize();
		if (x < 0) {
			x = inicialX;
			dx = -dx;
		}
		if (x + XSIZE >= d.width) {
			x = d.width - XSIZE;
			dx = -dx;
		}
		if (y < 0) {
			y = inicialY;
			dy = -dy;
		}
		if (y + YSIZE >= d.height) {
			y = d.height - YSIZE;
			dy = -dy;
		}
		g.fillOval(x, y, XSIZE, YSIZE);
		g.dispose();

	}

	public abstract void run();
}