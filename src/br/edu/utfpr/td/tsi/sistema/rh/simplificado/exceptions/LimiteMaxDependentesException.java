package br.edu.utfpr.td.tsi.sistema.rh.simplificado.exceptions;

@SuppressWarnings("serial")
public class LimiteMaxDependentesException extends RuntimeException {
	public LimiteMaxDependentesException(String msg) {
		super(msg);
	}
}
