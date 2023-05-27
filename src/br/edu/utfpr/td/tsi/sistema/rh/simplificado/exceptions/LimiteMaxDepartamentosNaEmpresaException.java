package br.edu.utfpr.td.tsi.sistema.rh.simplificado.exceptions;

@SuppressWarnings("serial")
public class LimiteMaxDepartamentosNaEmpresaException extends RuntimeException {
	public LimiteMaxDepartamentosNaEmpresaException (String msg) {
		super(msg);
	}
}
