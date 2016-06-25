package Rede.exemplos.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
	public static int i;
	private static List<PrintWriter> listaCli = new ArrayList<>();

	Socket s;

	public Escrever(Socket s) {
		i=i+1;
		this.s = s;
	}

	public void run() {

		try {
			InputStreamReader is = new InputStreamReader(s.getInputStream());
			BufferedReader in = new BufferedReader(is);
			PrintWriter out = new PrintWriter(s.getOutputStream());
			synchronized (listaCli){
			listaCli.add(out);}
			
			//out.println("Bem vindo:" + s.getInetAddress());
			out.println("Bem vindo:" + i);
			out.flush();

			do {
				linha = in.readLine();
					for(PrintWriter cout : listaCli){
						synchronized (listaCli) {
							
						cout.println(s.getPort()+" : " + linha);
						cout.flush(); 
					}
				}

			} while (!linha.endsWith("SAIR"));

			s.close();
			synchronized (listaCli){
			listaCli.remove(out);}
		} catch (Exception e) {
		}
	}
}
