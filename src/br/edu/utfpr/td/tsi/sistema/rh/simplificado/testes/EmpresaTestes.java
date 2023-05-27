package br.edu.utfpr.td.tsi.sistema.rh.simplificado.testes;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import br.edu.utfpr.td.tsi.sistema.rh.simplificado.Departamento;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.Empresa;
import br.edu.utfpr.td.tsi.sistema.rh.simplificado.exceptions.LimiteMaxDepartamentosNaEmpresaException;

public class EmpresaTestes {
	// 8 objetivo -> testa se empresa não pode possuir mais de 3 departamentos
	@Test
	public void empresaNaoDeveTerMaisDeTresDepartamentos() {
		assertThrows(LimiteMaxDepartamentosNaEmpresaException.class, () -> {
			Empresa e = new Empresa();
			Departamento d1 = new Departamento("d1");
			Departamento d2 = new Departamento("d2");
			Departamento d3 = new Departamento("d3");
			Departamento d4 = new Departamento("d4");

			e.cadastrarDepartamento(d1);
			e.cadastrarDepartamento(d2);
			e.cadastrarDepartamento(d3);
			e.cadastrarDepartamento(d4);
		});

	}

	// 8 objetivo -> testa se empresa cadastra 3 departamentos (Gerencia, Operações,
	// Pesquisa e Desenvolvimento)
	@Test
	public void empresaDeveCadastrarTresDepartamentos() {
		assertDoesNotThrow(() -> {
			Empresa e = new Empresa();
			Departamento d1 = new Departamento("Gerencia");
			Departamento d2 = new Departamento("Operações");
			Departamento d3 = new Departamento("Pesquisa e Desenvolvimento");

			e.cadastrarDepartamento(d1);
			e.cadastrarDepartamento(d2);
			e.cadastrarDepartamento(d3);
		});
	}

	// 8 objetivo -> testa se empresa possui exatamente 3 departamentos especificos
	// (Gerencia, Operações, Pesquisa e Desenvolvimento)
	@Test
	public void empresaDevePossuirTresDepartamentosEspecificos() {
		Empresa e = new Empresa();
		try {
			e.cadastraDepartamentosEspecificos();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Departamento[] departamentosEsperados = new Departamento[3];

		departamentosEsperados[0] = new Departamento("Gerencia");
		departamentosEsperados[1] = new Departamento("Operações");
		departamentosEsperados[2] = new Departamento("Pesquisa e Desenvolvimento");

		Boolean[] departamentoEsperadoExiste = new Boolean[3];
		Arrays.fill(departamentoEsperadoExiste, false);

		for (int i = 0; i < departamentosEsperados.length; i++) {
			for (int j = 0; j < e.getDepartamentos().length; j++) {
				if (departamentosEsperados[i].getNome().equals(e.getDepartamentos()[j].getNome())) {
					departamentoEsperadoExiste[i] = true;
					break;
				}
			}
		}

		Boolean[] resultadoEsperado = new Boolean[3];
		Arrays.fill(resultadoEsperado, true);

		assertArrayEquals(resultadoEsperado, departamentoEsperadoExiste);
	}
}
