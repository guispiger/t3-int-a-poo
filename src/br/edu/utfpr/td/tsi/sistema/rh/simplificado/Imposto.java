package br.edu.utfpr.td.tsi.sistema.rh.simplificado;

public enum Imposto {
	
	INSS("10"),
	INPS("10"),
	SEGUROOBRIGATORIO("10"),
	SINDICATO("10");
	
	private String valorPercentual;
	
	public String getValorPercentual() {
		return valorPercentual;
	}

	private Imposto(String valorPercentual) {
		this.valorPercentual = valorPercentual;
	}
	
	

}
