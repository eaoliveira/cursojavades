package Rede.exemplos.cliente;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaExemplo extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField textMessage;
	private JButton btnEnviar;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton btnConectar;
	private JButton btnDesconectar;
	private JButton btnSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaExemplo frame = new TelaExemplo();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaExemplo() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Mensagem");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(10, 11, 149, 19);
		contentPane.add(lblNewLabel);
		
		textMessage = new JTextField();
		textMessage.addActionListener(this);
		textMessage.setBounds(10, 31, 304, 23);
		contentPane.add(textMessage);
		textMessage.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(this);
		btnEnviar.setBounds(324, 31, 89, 23);
		contentPane.add(btnEnviar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 403, 132);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(this);
		btnConectar.setBounds(10, 208, 89, 23);
		contentPane.add(btnConectar);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.addActionListener(this);
		btnDesconectar.setBounds(166, 208, 102, 23);
		contentPane.add(btnDesconectar);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(this);
		btnSair.setBounds(324, 208, 89, 23);
		contentPane.add(btnSair);
	}
	public void actionPerformed(ActionEvent ev) {
		
		Object opcao = ev.getSource();
		
		if (opcao.equals(textMessage)||opcao.equals(btnEnviar)){
			
		}else if(opcao.equals(btnConectar)){
				
		}else if (opcao.equals(btnDesconectar)){
			
		} else {System.exit(0);}
		
    }
}
