package Anotacao.exemplo.investimento;

import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import Anotacao.exemplo.investimento.anotacoes.GrupoTODO;

public class RelatorioGeral {
	public static void main(String[] args) throws Exception {
		Class<?> clazz = Investimento.class;
		Method oMetodo = clazz.getMethod("calculoDeInvestimento", float.class, float.class);
					 
		if (oMetodo.isAnnotationPresent(GrupoTODO.class)) {
			GrupoTODO todo = oMetodo.getAnnotation(GrupoTODO.class);
			JOptionPane.showMessageDialog(null, "O Desenvolvimento do código para o\n" +
					"método: calculoDeInvestimento foi Designado a :" + todo.designadoA() +
					"\nem: " + todo.dataDesignacao() + 
					"\ncom a Severidade: " + todo.severity());
		}
	}
}
