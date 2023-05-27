package br.edu.utfpr.td.tsi.sistema.rh.simplificado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

import br.edu.utfpr.td.tsi.sistema.rh.simplificado.exceptions.DependenteAcimaLimiteMaxIdadeException;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.exceptions.FuncionarioComSalarioMenorOuIgualAZeroException;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.exceptions.LimiteMaxDependentesException;

public class Funcionario {
	private String nome;
	private String sobrenome;
	private String cpf;
	private LocalDate dataContratacao;
	private BigDecimal salario;
	private Cargo cargo;
	private Departamento trabalhaEm;
	private Dependente[] dependetentes = new Dependente[2];
	private int quantidadeDependentes = 0;
	private int matricula = 0;
	private static int contador = 0;

	// metodo refatorado - observacao: codigo do professor
	public Funcionario(String nome, String sobrenome, String cpf, LocalDate dataContratacao, BigDecimal salario,
			Cargo cargo, Departamento departamento) {
		super();
		if (salario.compareTo(new BigDecimal("0")) < 1) {
			throw new FuncionarioComSalarioMenorOuIgualAZeroException(
					"Não é possivel atribuir um salário menor ou igual a zero(0)");
		}
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.dataContratacao = dataContratacao;
		this.salario = salario;
		this.cargo = cargo;
		this.trabalhaEm = departamento;
		contador++;
		this.matricula = contador;
	}

	public int getMatricula() {
		return matricula;
	}

	// metodo refatorado - observacao: codigo do professor
	public void adicionarDependente(Dependente d) {
		if (this.quantidadeDependentes > 1) {
			throw new LimiteMaxDependentesException(
					"Limite máximo de dependentes atingido, não é possível inserir mais dependentes");
		}

		LocalDate hoje = LocalDate.now();
		int idade = Period.between(d.getDataNascimento(), hoje).getYears();
		if (d.getGrauDependencia().equals(GrauDependencia.FILHO)) {
			if (idade > 18) {
				throw new DependenteAcimaLimiteMaxIdadeException("Não é possivel adicionar filhos maiores de 18 anos");
			}
		}

		this.dependetentes[quantidadeDependentes] = d;
		this.quantidadeDependentes++;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public void setTrabalhaEm(Departamento trabalhaEm) {
		this.trabalhaEm = trabalhaEm;
	}

	public Departamento getTrabalhaEm() {
		return trabalhaEm;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Dependente[] getDependetentes() {
		return dependetentes;
	}

	public int getQuantidadeDependentes() {
		return quantidadeDependentes;
	}

	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", sobrenome=" + sobrenome + ", cpf=" + cpf + ", dataContratacao="
				+ dataContratacao + ", salario=" + salario + ", cargo=" + cargo + ", trabalhaEm=" + trabalhaEm
				+ ", dependetentes=" + Arrays.toString(dependetentes) + ", quantidadeDependentes="
				+ quantidadeDependentes + ", matricula=" + matricula + "]";
	}

}
