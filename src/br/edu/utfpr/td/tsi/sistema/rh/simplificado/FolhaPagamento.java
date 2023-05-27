package br.edu.utfpr.td.tsi.sistema.rh.simplificado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class FolhaPagamento {

	public static BigDecimal calcularValorPagar(Empresa empresa) {
		BigDecimal totalPagar = new BigDecimal("0");
		Departamento[] departamentos = empresa.getDepartamentos();
		for (Departamento departamento : departamentos) {
			Funcionario[] funcionarios = departamento.getFuncionarios();
			for (Funcionario funcionario : funcionarios) {
				if(funcionario != null) {
					totalPagar = totalPagar.add(calcularValorPagar(funcionario));
				}
			}			
		}
		return totalPagar;
	}

	public static BigDecimal calcularValorPagar(Departamento departamento) {
		BigDecimal totalPagar = new BigDecimal("0");
		Funcionario[] funcionarios = departamento.getFuncionarios();
		for (Funcionario funcionario : funcionarios) {
			if(funcionario != null) {
				totalPagar = totalPagar.add(calcularValorPagar(funcionario));
			}
		}
		return totalPagar;
	}

	public static BigDecimal calcularValorPagar(Funcionario[] funcionarios) {
		return null;
	}

	public static BigDecimal calcularValorPagar(Funcionario funcionario) {
		BigDecimal totalPagar = funcionario.getSalario();

		BigDecimal percentualImpostos = new BigDecimal("0");
		percentualImpostos = percentualImpostos.add(new BigDecimal(Imposto.INSS.getValorPercentual()));
		percentualImpostos = percentualImpostos.add(new BigDecimal(Imposto.SEGUROOBRIGATORIO.getValorPercentual()));
		percentualImpostos = percentualImpostos.add(new BigDecimal(Imposto.INPS.getValorPercentual()));

		if (funcionario.getCargo() == Cargo.ENGENHEIRO) {
			percentualImpostos = percentualImpostos.add(new BigDecimal(Imposto.SINDICATO.getValorPercentual()));
		}
		
		BigDecimal valorTotalImpostos = (funcionario.getSalario().multiply(percentualImpostos)).divide(new BigDecimal("100"));
		totalPagar = totalPagar.subtract(valorTotalImpostos);
		
		totalPagar = totalPagar.add(calcularValorAcrescimoPorDependentes(funcionario));
		totalPagar = totalPagar.add(calcularValorAcrescimoPorTempo(funcionario));
		
		return totalPagar;
	}

	private static BigDecimal calcularValorAcrescimoPorDependentes(Funcionario funcionario) {
		BigDecimal totalPagar = new BigDecimal("0");
		int quantidadeDependentes = funcionario.getQuantidadeDependentes();
		if(quantidadeDependentes > 0) {
			BigDecimal acrescimoDep = new BigDecimal("105.99").multiply(new BigDecimal(quantidadeDependentes));
			totalPagar = totalPagar.add(acrescimoDep);
		}
		return totalPagar;
	}
	
	private static BigDecimal calcularValorAcrescimoPorTempo(Funcionario funcionario) {
		BigDecimal totalPagar = new BigDecimal("0");
		int years = Period.between(funcionario.getDataContratacao(), LocalDate.now()).getYears();
		if(years>= 1) {
			BigDecimal acrescimoTemp = new BigDecimal("100").multiply(new BigDecimal(years));
			totalPagar = totalPagar.add(acrescimoTemp);
		}
		return totalPagar;
	}

}
