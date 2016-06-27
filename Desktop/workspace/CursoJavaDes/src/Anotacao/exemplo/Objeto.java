package Anotacao.exemplo;

public class Objeto {
	static {
		System.out.println("na Classe");
	}
	
	{
		System.out.println("no Objeto");
	}
	
	public Objeto() {
		System.out.println("no Construtor");
	}
	
	public void imprime() {
		System.out.println("no Método");
	}
	
	public static void main(String[] args) {
		new Objeto().imprime();
		new Objeto().imprime();
		
		Objeto o = new Objeto();
		o.imprime();
	}
}
