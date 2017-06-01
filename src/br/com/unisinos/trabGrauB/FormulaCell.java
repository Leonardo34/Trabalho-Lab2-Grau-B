package br.com.unisinos.trabGrauB;

import java.io.Serializable;

public class FormulaCell extends Cell implements Serializable {
	private String formula;
	private List<Cell> planilha;
	
	public FormulaCell(String formula, List<Cell> planilha, String id) {
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
	
	public List<Cell> getPlanilha() {
		return planilha;
	}
	
	public void setPlanilha(List<Cell> planilha) {
		this.planilha = planilha;
	}
	
	public double eval() {
		Stack<Double> stack = new LinkedStack<>();
		String[] formulaSplit = formula.replaceAll("=", "").split(" ");
		for (String each : formulaSplit) {
			if (isNumber(each)) {
				stack.push(Double.parseDouble(each));
			} else if (isOperation(each)) {
				Operation oper = new Operation(each, stack.pop(), stack.pop());
				stack.push(oper.eval());
			} else {
				int index = planilha.search(new ValueCell(0, each));
				stack.push(planilha.get(index).eval());
			}
		}
		return stack.pop();
	}
	
	private boolean isNumber(String expr) {
		return expr.matches("^-?\\d+(\\.\\d+)?$");
	}
	
	private boolean isOperation(String expr) {
		return expr.matches("[-+*/^]");
	}
}
