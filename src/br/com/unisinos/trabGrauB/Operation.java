package br.com.unisinos.trabGrauB;

public class Operation {
	private String type;
	private Double oper1;
	private Double oper2;
	
	public Operation(String type, Double oper2, Double oper1) {
		this.type = type;
		this.oper1 = oper1;
		this.oper2 = oper2;
	}
	
	public Double eval() {
		if (type.equals("+")) {
			return oper1 + oper2;
		} else if (type.equals("-")) {
			return oper1 - oper2;
		} else if (type.equals("*")) {
			return oper1 * oper2;
		} else if (type.equals("/")) {
			return oper1 / oper2;
		} else if (type.equals("^")) {
			return Math.pow(oper1, oper2);
		} else {
			return 0.0;
		}
	}
}
