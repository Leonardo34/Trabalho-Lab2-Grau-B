package br.com.unisinos.trabGrauB;

public class ValueCell extends Cell {
	private double valor;
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Override
	public double eval() {
		return valor;
	}
}