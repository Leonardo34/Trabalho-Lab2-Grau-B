package br.com.unisinos.trabGrauB;

public class FormulaCell extends Cell {
	private String formula;
	private List<Cell> planilha;
	
	public FormulaCell(String formula, List<Cell> planilha, int id) {
		super(id);
		this.formula = formula;
		this.planilha = planilha;
	}
	
	public String getFormula() {
		return formula;
	}
	
	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	public double eval() {
		return 0;
	}
}
