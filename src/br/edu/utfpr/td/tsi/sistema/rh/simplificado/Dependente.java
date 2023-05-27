package br.edu.utfpr.td.tsi.sistema.rh.simplificado;

import java.time.LocalDate;

public class Dependente {
	private String nome;
	private GrauDependencia grauDependencia;
	private LocalDate dataNascimento;
	
	public Dependente(String nome, GrauDependencia grauDependencia, LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.grauDependencia = grauDependencia;
		this.dataNascimento = dataNascimento;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public GrauDependencia getGrauDependencia() {
		return grauDependencia;
	}

	public void setGrauDependencia(GrauDependencia grauDependencia) {
		this.grauDependencia = grauDependencia;
	}

}
