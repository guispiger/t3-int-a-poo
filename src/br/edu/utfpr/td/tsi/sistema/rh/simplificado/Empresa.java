package br.edu.utfpr.td.tsi.sistema.rh.simplificado;

import br.edu.utfpr.td.tsi.sistema.rh.simplificado.exceptions.LimiteMaxDepartamentosNaEmpresaException;

 public class Empresa {
	private Departamento[] departamentos = new Departamento[3];
	private int index = 0;
	
	// metodo incluido - observacao: codigo do professor
	public void cadastraDepartamentosEspecificos() throws Exception {
		this.cadastrarDepartamento(new Departamento("Gerencia"));
		this.cadastrarDepartamento(new Departamento("Operações"));
		this.cadastrarDepartamento(new Departamento("Pesquisa e Desenvolvimento"));
	}
	
	// metodo refatorado - observacao: codigo do professor
	public void cadastrarDepartamento(Departamento d) throws Exception {
		if (index >= this.departamentos.length) {
			throw new LimiteMaxDepartamentosNaEmpresaException(
					"Limite máximo de departamentos para essa empresa atingido, não é possivel cadastrar mais departamentos\n"
					+ "Departamento não cadastrado: " + d.getNome());
		}
		this.departamentos[index] = d;
		index++;
	}

	public Departamento[] getDepartamentos() {
		return departamentos;
	}

	public Funcionario procurarFuncionario(int matricula) {
		Funcionario f = null;
		for (Departamento departamento : departamentos) {
			Funcionario[] funcionarios = departamento.getFuncionarios();
			for (Funcionario funcionario : funcionarios) {
				if (funcionario != null && funcionario.getMatricula() == matricula) {
					f = funcionario;
					break;
				}
			}
		}
		return f;
	}

}



 
