package Rede.exemplos.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Rede.exemplos.cliente.TelaExemplo.Lertexto;

public class Servidor {

	private static Socket s;
	public static int i=0;
	// private static InputStreamReader is;
	// private static BufferedReader in;
	// private static PrintWriter out;

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(1234);

		while (true) {
			s = server.accept();
			Escrever a = new Escrever(s);
			Thread b = new Thread(a);
			b.start();
			// Obtendo o canal de leitura do socket
		}
		
	}
}

class Escrever extends Thread {
	private String linha;
	public int i=0;

	Socket s;

	public Escrever(Socket s) {
		this.s = s;
	}

	public void run() {
		try {
			i=+1;
			do {
				InputStreamReader is = new InputStreamReader(s.getInputStream());
				BufferedReader in = new BufferedReader(is);
				PrintWriter out = new PrintWriter(s.getOutputStream());
				//out.println("Bem vindo:" + s.getInetAddress());
				out.println("Bem vindo:" + i);
				out.flush();
				out.println("S:" + in.readLine());
				out.flush();
				linha = in.readLine();
			} while (!linha.endsWith("SAIR"));
			s.close();
		} catch (Exception e) {
		}
	}
}
