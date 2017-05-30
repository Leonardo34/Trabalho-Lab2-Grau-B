package br.com.unisinos.trabGrauB;

public abstract class Cell {
	private int id;
	
	public Cell(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public abstract double eval();
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Cell otherCell = (Cell)obj;
		return this.id == otherCell.id;
	}
}
