package br.edu.utfpr.td.tsi.sistema.rh.simplificado.exceptions;

@SuppressWarnings("serial")
public class FuncionarioComSalarioMenorOuIgualAZeroException extends RuntimeException {
	public FuncionarioComSalarioMenorOuIgualAZeroException(String msg) {
		super(msg);
	}
}
