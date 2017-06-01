package br.com.unisinos.trabGrauB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ComandoFactory {
	
	public static Comando criarComando(int opcao,
			DoublyLinkedList<Cell> planilha) {
		if (opcao == 1) {
			return new ComandoInclusaoCelula(planilha);
		} 
		if (opcao == 2) {
			return new ComandoAvaliacaoCelula(planilha);
		}
		if (opcao == 3) {
			return new ComandoAvaliacaoTodasCelulas(planilha);
		}
		if (opcao == 4) {
			return new ComandoVisualizacaoTodasCelulas(planilha);
		}
		if (opcao == 5) {
			return new ComandoGravacaoArquivo(planilha);
		}
		if (opcao == 6 ) {
			return new ComandoLeituraArquivo(planilha);
		}
		return new ComandoInvalido();
	}

	public static class ComandoInclusaoCelula extends Comando {
		private DoublyLinkedList<Cell> planilha;
		
		public ComandoInclusaoCelula(DoublyLinkedList<Cell> planilha) {
			this.planilha = planilha;
		}
		
		@Override
		public void executar() {
			Teclado tecladoReader = new Teclado();
			String id = tecladoReader.leString("Digite o id da nova celula: ");
			String valor = tecladoReader.leString("Digite o valor da celula");
			if (valor.charAt(0) == '=') {
				planilha.insertLast(new FormulaCell(valor, planilha, id));
			} else {
				planilha.insertLast(new ValueCell(Double.parseDouble(valor), id));
			}
		}
	}
	
	public static class ComandoAvaliacaoCelula extends Comando {
		private DoublyLinkedList<Cell> planilha;
		
		public ComandoAvaliacaoCelula(DoublyLinkedList<Cell> planilha) {
			this.planilha = planilha;
		}
		
		@Override
		public void executar() {
			Teclado tecladoReader = new Teclado();
			String id = tecladoReader.leString("Digite o id da celula a avaliar");
			int index = planilha.search(new ValueCell(0, id));
			if (index >= 0) {
				System.out.println("Valor da celula: " + planilha.get(index).eval());
			} else {
				System.out.println("A planilha não contem uma célula com este id");
			}
		}
	}
	
	public static class ComandoAvaliacaoTodasCelulas extends Comando {
		private DoublyLinkedList<Cell> planilha;
		
		public ComandoAvaliacaoTodasCelulas(DoublyLinkedList<Cell> planilha) {
			this.planilha = planilha;
		}
		
		@Override
		public void executar() {
			for (int i = 0; i < planilha.numElements(); i++) {
				Cell celula = planilha.get(i);
				System.out.println(celula.getId() + " " + celula.eval());
			}
		}
	}
	
	public static class ComandoVisualizacaoTodasCelulas extends Comando {
		private DoublyLinkedList<Cell> planilha;
		
		public ComandoVisualizacaoTodasCelulas(DoublyLinkedList<Cell> planilha) {
			this.planilha = planilha;
		}
		
		@Override
		public void executar() {
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
		}
	}
	
	public static class ComandoGravacaoArquivo extends Comando {
		private DoublyLinkedList<Cell> planilha;
		
		public ComandoGravacaoArquivo(DoublyLinkedList<Cell> planilha) {
			this.planilha = planilha;
		}
		
		@Override
		public void executar() {
			try {
				Teclado tecladoReader = new Teclado();
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
	}
	
	public static class ComandoLeituraArquivo extends Comando {
		private DoublyLinkedList<Cell> planilha;
		
		public ComandoLeituraArquivo(DoublyLinkedList<Cell> planilha) {
			this.planilha = planilha;
		}
		
		@Override
		public void executar() {
			try {
				Teclado tecladoReader = new Teclado();
				File file = new File(tecladoReader.leString("Digite o diretorio do arquivo:"));
				FileInputStream fos = new FileInputStream(file);
				ObjectInputStream os = new ObjectInputStream(fos);
				while (true) {
					try {
						Cell cell = (Cell)os.readObject();
						System.out.println(cell.getId());
						if (cell instanceof FormulaCell) {
							((FormulaCell)cell).setPlanilha(planilha);
						}
						planilha.insertLast(cell);
					} catch (IOException e) {
						break;
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static class ComandoInvalido extends Comando {
		public void executar() {
			System.out.println("Comando invalido");
		}
	}
}
