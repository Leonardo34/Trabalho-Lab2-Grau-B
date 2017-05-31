package br.com.unisinos.trabGrauB;

public class TestClass {
	public static void main(String[] args) {
		DoublyLinkedList<Cell> planilha = new DoublyLinkedList<>();
		Teclado tecladoReader = new Teclado();
		int opcao;
		
		do {
			imprimirMenu();
			opcao = tecladoReader.leInt();
			
			if (opcao == 1) {
				String id = tecladoReader.leString("Digite o id da nova celula: ");
				String valor = tecladoReader.leString("Digite o valor da celula");
				if (valor.charAt(0) == '=') {
					planilha.insertLast(new FormulaCell(valor, planilha, id));
				} else {
					planilha.insertLast(new ValueCell(Double.parseDouble(valor), id));
				}
			}
			
		} while (opcao != 9);
	}
	
	public static void imprimirMenu() {
		System.out.println("Digite a opcao:");
		System.out.println("1 - Incluir c�lula");
		System.out.println("2 - Avaliar c�lula");
		System.out.println("3 - Avaliar todas as c�lulas");
		System.out.println("4 - Conte�do de todas as c�lulas");
		System.out.println("5 - Salvar em arquivo");
		System.out.println("6 - Abrir arquivo");
		System.out.println("9 - Sair");
	}
}
