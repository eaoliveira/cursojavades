package Thread.bank;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SynchBank3 {
	public static final int NACCOUNTS = 10;
	public static final int INITIAL_BALANCE = 10000;
	private Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
	private JLabel infoFrom = new JLabel("");
	private JLabel infoTo = new JLabel("");
	private JLabel infoAmount = new JLabel("");
	private JLabel infoTx = new JLabel("");
	private JLabel infoSum = new JLabel("");

	public SynchBank3() {
		new Tela("Synch Bank 3 Test");
	}

	public void createThread(int i) {
		TransferThread t = new TransferThread(bank, i, INITIAL_BALANCE);
		t.setPriority(Thread.NORM_PRIORITY + i % 2);
		t.start();
	}

	public static void main(String[] args) {
		SynchBank3 ub = new SynchBank3();

		for (int i = 0; i < NACCOUNTS; i++)
			ub.createThread(i);
	}

	class TransferThread extends Thread {
		private Bank bank;
		private int fromAccount;
		private int maxAmount;

		public TransferThread(Bank b, int from, int max) {
			bank = b;
			fromAccount = from;
			maxAmount = max;
		}

		public void run() {
			try {
				while (!interrupted()) {
					int toAccount = (int) (bank.size() * Math.random());
					int amount = (int) (maxAmount * Math.random());

					bank.transfer(fromAccount, toAccount, amount);

					infoFrom.setText(String.valueOf(this.fromAccount));
					infoTo.setText(String.valueOf(toAccount));
					infoAmount.setText(String.valueOf(amount));

					sleep(2);
				}
			} catch (InterruptedException e) {
			}
		}
	}

	class Bank {
		public static final int NTEST = 10000;
		private int[] accounts;
		private long ntransacts = 0;

		public Bank(int n, int initialBalance) {
			accounts = new int[n];

			for (int i = 0; i < accounts.length; i++)
				accounts[i] = initialBalance;
			ntransacts = 0;
		}

		public void transfer(int from, int to, int amount) {
			if (accounts[from] < amount)
				return;
			synchronized (accounts) {
				accounts[from] -= amount;
				accounts[to] += amount;

				ntransacts++;
			}

			if (ntransacts % NTEST == 0)
				test();
		}

		public void test() {
			int sum = 0;

			for (int i = 0; i < accounts.length; i++)
				sum += accounts[i];

			infoTx.setText(String.valueOf(ntransacts));
			infoSum.setText(String.valueOf(sum));
		}

		public int size() {
			return accounts.length;
		}
	}

	@SuppressWarnings("serial")
	class Tela extends JFrame {
		public Tela(String titulo) {
			JPanel p0 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
			p0.add(new JLabel("Transferência da conta:"));
			p0.add(infoFrom);
			p0.add(new JLabel("para:"));
			p0.add(infoTo);
			p0.add(new JLabel("R$"));
			p0.add(infoAmount);

			JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
			p1.add(new JLabel("Transações:"));
			p1.add(infoTx);
			p1.add(new JLabel("Soma R$"));
			p1.add(infoSum);

			JButton fim = new JButton("Parar");
			fim.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					System.exit(0);
				}
			});
			JPanel p2 = new JPanel(new BorderLayout());
			p2.add(Box.createRigidArea(new Dimension(110, 10)), BorderLayout.LINE_START);
			p2.add(fim, BorderLayout.CENTER);
			p2.add(Box.createRigidArea(new Dimension(110, 10)), BorderLayout.LINE_END);

			Container painel = getContentPane();
			painel.setLayout(new BorderLayout());
			painel.add(p0, BorderLayout.NORTH);
			painel.add(p1, BorderLayout.CENTER);
			painel.add(p2, BorderLayout.SOUTH);

			setTitle(titulo);
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		}
	}
}
