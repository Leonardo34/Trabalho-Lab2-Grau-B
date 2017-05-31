package br.com.unisinos.trabGrauB;

public class TestClass {
	public static void main(String[] args) {
		DoublyLinkedList<Cell> lista = new DoublyLinkedList<>();
		Cell celula = new FormulaCell("=A1 A2 +", lista, "Pi");
		Cell celula2 = new FormulaCell("=A2 5 +", lista , "A1");
		Cell celula3 = new ValueCell(5, "A2");
		lista.insertFirst(celula);
		lista.insertLast(celula2);
		lista.insertLast(celula3);
		System.out.println("testeeee");
		System.out.println(celula.eval());
		//System.out.println("+".matches("[-+*/^]"));
	}
}
