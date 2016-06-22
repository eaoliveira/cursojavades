package Rede.exemplos.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class IoTeste {
	public static void main(String[] args) throws Exception {

		// conectando no servidor
		Socket skt = new Socket("10.84.144.250", 1234);

		// Obtendo o canal de leitura do socket
		BufferedReader in = new BufferedReader( new InputStreamReader( skt.getInputStream() ) );

		// Obtendo o canal de gravação do socket
		PrintWriter out = new PrintWriter(skt.getOutputStream());

		// conectanto no canal de leitura da console
		BufferedReader console = new BufferedReader( new InputStreamReader( System.in ) );

		// lê linha do servidor
		String linha = in.readLine();

		while (linha != null) {
			// mostra na tela
			System.out.println(linha);

			// le da console
			String texto = console.readLine();
			// grava no servidor
			out.println(texto);
			out.flush();

			// lê linha do servidor
			linha = in.readLine();

			if (linha.endsWith("SAIR"))
				break;
		}

		// fechando a conexão
		skt.close();
	}
}
