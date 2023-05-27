package br.edu.utfpr.td.tsi.sistema.rh.simplificado;

public class Departamento {

	private String nome;
	private Funcionario[] funcionarios = new Funcionario[3];
	private int quantidadeTrabalhadores = 0; 

	public Departamento(String nome) {
		super();
		this.nome = nome;
	}
	
	/// metodo refatorado - observacao: codigo do professor
	public void alocarFuncionario(Funcionario f) {
		this.funcionarios[this.quantidadeTrabalhadores] = f;
		quantidadeTrabalhadores++;
	}

	public String getNome() {
		return nome;
	}

	public Funcionario[] getFuncionarios() {
		return funcionarios;
	}

}
