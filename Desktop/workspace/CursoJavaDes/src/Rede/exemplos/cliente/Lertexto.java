package Rede.exemplos.cliente;

import javax.swing.JOptionPane;

public class Lertexto extends TelaExemplo implements Runnable {
	public void run(){
		try{
		// lê linha do servidor
		String linha = in.readLine();
		textArea.setText(textArea.getText() + linha + "\n");
		}catch(Exception e) {JOptionPane.showMessageDialog(null, "N�o foi possivel ler" + e);}
	}
}
