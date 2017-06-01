package br.com.unisinos.trabGrauB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestClass {
	public static void main(String[] args) {
		DoublyLinkedList<Cell> planilha = new DoublyLinkedList<>();
		Teclado tecladoReader = new Teclado();
		int opcao;

		do {
			imprimirMenu();
			opcao = tecladoReader.leInt();
			Comando comando = ComandoFactory.criarComando(opcao, planilha);
			if (opcao != 9) {
				comando.executar();
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
