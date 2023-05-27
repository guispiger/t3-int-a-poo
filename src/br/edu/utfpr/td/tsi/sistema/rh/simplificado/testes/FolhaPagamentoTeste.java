package br.edu.utfpr.td.tsi.sistema.rh.simplificado.testes;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import br.edu.utfpr.td.tsi.sistema.rh.simplificado.Cargo;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.Departamento;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.Dependente;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.FolhaPagamento;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.Funcionario;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.GrauDependencia;

public class FolhaPagamentoTeste {
	// 3 objetivo -> testa se o funcionario recebe um acrescimo de 105.99 por
	// dependente declarado
	@Test
	public void funcionarioDeveReceberAcrescimoPorDependente() {

		Funcionario f = new Funcionario("nomeFunc", "sobrenomeFunc", "123", LocalDate.now(), new BigDecimal("0.01"),
				Cargo.COBRADOR, new Departamento("dep"));
		Dependente d = new Dependente("nome", GrauDependencia.FILHO, LocalDate.now());
		f.adicionarDependente(d);
		BigDecimal valorPagar = FolhaPagamento.calcularValorPagar(f);
		BigDecimal valorDescontosImpostos = f.getSalario().multiply(new BigDecimal("30").divide(new BigDecimal("100")));
		BigDecimal valorEsperado = new BigDecimal("105.99").add(f.getSalario().subtract(valorDescontosImpostos));
		assertEquals(valorEsperado, valorPagar);
	}

	// 4 objetivo -> testa se o funcionario recebe um acrescimo de 100.00 para cada
	// ano completo de trabalho
	@Test
	public void funcionarioDeveReceberAcrescimoPorAnoTrabalhado() {
		Funcionario f = new Funcionario("nomeFunc", "sobrenomeFunc", "123", LocalDate.now().minusYears(1),
				new BigDecimal("0.01"), Cargo.COBRADOR, new Departamento("dep"));
		BigDecimal valorPagar = FolhaPagamento.calcularValorPagar(f);
		BigDecimal valorDescontosImpostos = f.getSalario().multiply(new BigDecimal("30").divide(new BigDecimal("100")));
		BigDecimal valorEsperado = new BigDecimal("100.00").add(f.getSalario().subtract(valorDescontosImpostos));
		assertEquals(valorEsperado, valorPagar);
	}

	// 5 objetivo -> testa se a folha de pagamento desconta o percentual de impostos
	// do salario dos funcionários
	@Test
	public void folhaPgtoDeveDescontarPercentualDeImpostosDoSalario() {
		Funcionario f = new Funcionario("nomeFunc", "sobrenomeFunc", "123", LocalDate.now(), new BigDecimal("0.01"),
				Cargo.COBRADOR, new Departamento("dep"));
		BigDecimal valorPagar = FolhaPagamento.calcularValorPagar(f);
		assertTrue(valorPagar.compareTo(f.getSalario()) < 0);
	}

	// 6 objetivo -> testa se folha de pagamento desconta do funcionario os impostos
	// INSS(10% do salario), INPS(10% do salario), SEGUROOBRIGATORIO(10% do salario)
	@Test
	public void folhaPgtoDeveDescontarInssInpsSeguroObrigatorio() {
		Funcionario f = new Funcionario("nomeFunc", "sobrenomeFunc", "123", LocalDate.now(), new BigDecimal("0.01"),
				Cargo.COBRADOR, new Departamento("dep"));
		BigDecimal valorPagar = FolhaPagamento.calcularValorPagar(f);
		BigDecimal valorDescontosImpostos = f.getSalario().multiply(new BigDecimal("30").divide(new BigDecimal("100")));
		BigDecimal valorEsperado = f.getSalario().subtract(valorDescontosImpostos);
		assertEquals(valorEsperado, valorPagar);
	}

	// 7 objetivo -> testa se funcionarios com o cargo Engenheiro tem desconto
	// adicional do imposto de sindicato(10% do salario)
	@Test
	public void funcionarioCargoEngenehreiroDevePagarSindicato() {
		Funcionario f = new Funcionario("nomeFunc", "sobrenomeFunc", "123", LocalDate.now(), new BigDecimal("0.01"),
				Cargo.ENGENHEIRO, new Departamento("dep"));
		BigDecimal valorPagar = FolhaPagamento.calcularValorPagar(f);
		BigDecimal valorDescontosImpostos = f.getSalario().multiply(new BigDecimal("40").divide(new BigDecimal("100")));
		BigDecimal valorEsperado = f.getSalario().subtract(valorDescontosImpostos);
		assertEquals(valorEsperado, valorPagar);
	}
}
