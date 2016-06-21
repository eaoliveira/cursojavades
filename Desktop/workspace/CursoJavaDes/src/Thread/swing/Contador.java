package Thread.swing;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Contador extends Thread{
	private JProgressBar pg = null;
	private int value = 0;
	private int interval = 5;
	private String nome = "Contador";

	public Contador(String nome, int value) {
		pg = new JProgressBar(0, value);
		setValue(value);
		setNome(nome);
	}

	public Contador(String nome, int value, int interval) {
		pg = new JProgressBar(0, value);
		setValue(value);
		setNome(nome);
		setInterval(interval);
	}

	private void setNome(String nome) {
		this.nome = nome;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public JProgressBar getPg() {
		return pg;
	}

	public JPanel getBar() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 3, 5, 3));
		panel.add(new JLabel(nome));
		panel.add(pg);
		pg.setStringPainted(true);

		return panel;
	}

	public void run() {
		try {
			for (int i = 1; i <= value;) {
				Thread.sleep(interval);
				pg.setValue(i++);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
