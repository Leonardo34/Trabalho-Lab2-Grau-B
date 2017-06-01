package br.com.unisinos.trabGrauB;

import java.io.Serializable;

public class ValueCell extends Cell implements Serializable {
	private double valor;
	
	public ValueCell(double valor, String id) {
		super(id);
		this.valor = valor;
	}
	
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
