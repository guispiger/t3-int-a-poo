package br.edu.utfpr.td.tsi.sistema.rh.simplificado.testes;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import br.edu.utfpr.td.tsi.sistema.rh.simplificado.Cargo;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.Departamento;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.Dependente;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.Funcionario;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.GrauDependencia;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.exceptions.DependenteAcimaLimiteMaxIdadeException;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.exceptions.FuncionarioComSalarioMenorOuIgualAZeroException;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.exceptions.LimiteMaxDependentesException;

public class FuncionarioTeste {
	// 1 objetivo -> testar se um funcionario não pode declarar mais de dois
	// dependentes
	@Test
	public void naoDeveTerMaisDeDoisDependentes() {
		assertThrows(LimiteMaxDependentesException.class, () -> {
			Funcionario f = new Funcionario("nomeFunc", "sobrenomeFunc", "123", LocalDate.now(), new BigDecimal("0.01"),
					Cargo.COBRADOR, new Departamento("dep"));
			Dependente d1 = new Dependente("nomeDepen1", GrauDependencia.FILHO, LocalDate.now());
			Dependente d2 = new Dependente("nomeDepen2", GrauDependencia.FILHO, LocalDate.now());
			Dependente d3 = new Dependente("nomeDepen3", GrauDependencia.FILHO, LocalDate.now());
			f.adicionarDependente(d1);
			f.adicionarDependente(d2);
			f.adicionarDependente(d3);
		});
	}

	// 2 objetivo -> testar se um funcionario não pode declarar um dependente com
	// grau de dependencia FILHO e maior de 18 anos
	@Test
	public void naoDeveCadastrarDependeteFilhoIdadeMaiorQue18() {
		assertThrows(DependenteAcimaLimiteMaxIdadeException.class, () -> {
			Funcionario f = new Funcionario("nomeFunc", "sobrenomeFunc", "123", LocalDate.now(), new BigDecimal("0.01"),
					Cargo.COBRADOR, new Departamento("dep"));
			Dependente d = new Dependente("nome", GrauDependencia.FILHO, LocalDate.now().minusYears(19));
			f.adicionarDependente(d);
		});
	}

	// 5-6-7 objetivo -> testa se um funcionario nao pode ser criado com salario
	// menor ou igual a 0
	@Test
	public void naoDeveCriarFuncionarioComSalaraioMenorOuIgualAZero() {
		assertThrows(FuncionarioComSalarioMenorOuIgualAZeroException.class, () -> {
			Funcionario f = new Funcionario("nomeFunc", "sobrenomeFunc", "123", LocalDate.now(), new BigDecimal("0"),
					Cargo.COBRADOR, new Departamento("dep"));
		});
	}
	
	
	// 9 objetivo -> testa se o funcionario é obrigatoriamente vinculado a um
	// departamento
	@Test
	public void funcionarioDeveSerAlocadoEmUmDepartamento() {
		Funcionario f = new Funcionario("nomeFunc", "sobrenomeFunc", "123", LocalDate.now(), new BigDecimal("0.01"),
				Cargo.COBRADOR, new Departamento("dep"));
		assertNotNull(f.getTrabalhaEm());
	}

}
