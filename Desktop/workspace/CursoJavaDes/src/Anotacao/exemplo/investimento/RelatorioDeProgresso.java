package Anotacao.exemplo.investimento;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import Anotacao.exemplo.investimento.anotacoes.EmProgresso;

public class RelatorioDeProgresso {
	public static void main(String[] args) {
		String pkg = "Anotacao.exemplo.investimento.";
		List<String> classes = Arrays.asList("Investimento", "Super", "Sub");

		String msg = "";
		for (String item : classes) {
			try {
				Class<?> clazz = Class.forName(pkg + item);
				msg += "A Class " + pkg + item + " está " +
						(clazz.isAnnotationPresent(EmProgresso.class) ? "Em Progresso" : "Concluído") + "\n";
			} catch (ClassNotFoundException ex) {
				System.err.println("A Classe " + pkg + item + " não foi encontrada");
			}
		}
		JOptionPane.showMessageDialog(null, "Resumo das atividades\n\n" + msg);
	}
}
