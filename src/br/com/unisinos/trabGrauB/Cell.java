package br.com.unisinos.trabGrauB;

public abstract class Cell {
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public abstract double eval();
}
