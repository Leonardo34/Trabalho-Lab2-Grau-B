package br.com.unisinos.trabGrauB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
			} else if (opcao == 2) {
				String id = tecladoReader.leString("Digite o id da celula a avaliar");
				int index = planilha.search(new ValueCell(0, id));
				if (index >= 0) {
					System.out.println("Valor da celula: " + planilha.get(index).eval());
				} else {
					System.out.println("A planilha não contem uma célula com este id");
				}
			} else if (opcao == 3) {
				for (int i = 0; i < planilha.numElements(); i++) {
					Cell celula = planilha.get(i);
					System.out.println(celula.getId() + " " + celula.eval());
				}
			} else if (opcao == 4) {
				for (int i = 0; i < planilha.numElements(); i++) {
					Cell celula = planilha.get(i);
					if (celula instanceof ValueCell) {
						System.out.println(celula.getId() + " " + 
								((ValueCell) celula).getValor());
					} else {
						System.out.println(celula.getId() + " " + 
								((FormulaCell) celula).getFormula());
					}
				}
			} else if (opcao == 5) {
				try {
					File file = new File(tecladoReader.leString("Digite o diretorio do arquivo: "));
					FileOutputStream fos = new FileOutputStream(file);
					ObjectOutputStream os = new ObjectOutputStream(fos);
					for (int i = 0; i < planilha.numElements(); i++) {
						os.writeObject(planilha.get(i));
					}
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} while (opcao != 9);
	}

	public static void imprimirMenu() {
		System.out.println("Digite a opcao:");
		System.out.println("1 - Incluir célula");
		System.out.println("2 - Avaliar célula");
		System.out.println("3 - Avaliar todas as células");
		System.out.println("4 - Conteúdo de todas as células");
		System.out.println("5 - Salvar em arquivo");
		System.out.println("6 - Abrir arquivo");
		System.out.println("9 - Sair");
	}
}
