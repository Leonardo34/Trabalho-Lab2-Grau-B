package br.com.unisinos.trabGrauB;

public abstract class Cell {
	private String id;
	
	public Cell(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public abstract double eval();
	
	@Override
	public boolean equals(Object obj) {
		Cell otherCell = (Cell)obj;
		return this.id.equals(otherCell.id);
	}
}
