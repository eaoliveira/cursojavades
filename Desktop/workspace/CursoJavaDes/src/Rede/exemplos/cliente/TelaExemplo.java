package Rede.exemplos.cliente;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class TelaExemplo extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField textMessage;
	private JButton btnEnviar;
	private JScrollPane scrollPane;
	protected JTextArea textArea;
	private JButton btnConectar;
	private JButton btnDesconectar;
	private JButton btnSair;
	protected Socket skt;
	protected BufferedReader in, console;
	private PrintWriter out;
	private String linha;
	private Lertexto a;
	private Thread b;
	
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
		textMessage.setEnabled(false);
		textMessage.addActionListener(this);
		textMessage.setBounds(10, 31, 304, 23);
		contentPane.add(textMessage);
		textMessage.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setEnabled(false);
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
		btnDesconectar.setEnabled(false);
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
		try{
		if (opcao.equals(textMessage)||opcao.equals(btnEnviar)){
			// le da console
			//String texto = console.readLine();
			String texto = textMessage.getText();
			// grava no servidor
			out.println(texto);
			out.flush();	
			textMessage.setText(null);

		}else if(opcao.equals(btnConectar)){
			// conectando no servidor
			skt = new Socket("127.0.0.1", 1234);
			// Obtendo o canal de leitura do socket
			in = new BufferedReader( new InputStreamReader( skt.getInputStream() ) );
			// Obtendo o canal de gravaÃ§Ã£o do socket
			out = new PrintWriter(skt.getOutputStream());
			// conectanto no canal de leitura da console
			//console = new BufferedReader( new InputStreamReader( System.in ) );
		
			a = new Lertexto();
			b = new Thread(a);
			b.start();
			
			textMessage.setEnabled(true);
			btnEnviar.setEnabled(true);
			btnDesconectar.setEnabled(true);
			btnConectar.setEnabled(false);
		}else if (opcao.equals(btnDesconectar)){
			Lertexto.interrupted();
			skt.close();
			textMessage.setEnabled(false);
			btnConectar.setEnabled(true);
			btnEnviar.setEnabled(false);
			btnDesconectar.setEnabled(false);
			textArea.setText(null);
			textMessage.setText(null);
		} else {System.exit(0);} 
		}catch(Exception e){JOptionPane.showMessageDialog(null, "Não foi possivel conectar" + e);}
		
    }
		
		class Lertexto extends Thread {
			public void run(){
				try{
					while (!interrupted()){
						if (in.ready()){
							// lÃª linha do servidor
							String linha = in.readLine();
							textArea.setText(textArea.getText() + linha + "\n");
						}else Thread.sleep(5);
					}
				}catch(Exception e) {JOptionPane.showMessageDialog(null, "Não foi possivel ler" + e);}}
		}
}

	


