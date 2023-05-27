package br.edu.utfpr.td.tsi.sistema.rh.simplificado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Programa {

	public static void main(String[] args) {
		Empresa empresa = new Empresa();

		// inicio bloco refatorado - observacao: codigo do professor
		try {
			empresa.cadastraDepartamentosEspecificos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		// fim bloco refatorado


		boolean fechar = false;
		while (fechar == false) {
			String operacao = JOptionPane.showInputDialog(
					"Qual operação deseja executar?(1=cadastrar funcionario, 2=gerar folha de pagamento, 3=fechar)");
			if (operacao.equals("1")) {
				Funcionario f = obterDadosFuncionario(empresa);
				JOptionPane.showMessageDialog(null,
						String.format("cadastrado com sucesso.\n matricula gerada: %s", f.getMatricula()));

			} else if (operacao.equals("2")) {
				BigDecimal valor = new BigDecimal("0");
				String opcao2 = JOptionPane
						.showInputDialog("Calcular valor para?(1=Empresa, 2=Departamento, 3=Funcionario)");
				if (opcao2.equals("1")) {
					valor = FolhaPagamento.calcularValorPagar(empresa);

				} else if (opcao2.equals("2")) {
					String opcaoDepartamento = JOptionPane
							.showInputDialog("Escolha um departamento: (1=Gerencia, 2=Operações, 3=P&D)");
					if (opcaoDepartamento.equals("1")) {
						valor = FolhaPagamento.calcularValorPagar(empresa.getDepartamentos()[0]);
					} else if (opcaoDepartamento.equals("2")) {
						valor = FolhaPagamento.calcularValorPagar(empresa.getDepartamentos()[1]);
					} else if (opcaoDepartamento.equals("3")) {
						valor = FolhaPagamento.calcularValorPagar(empresa.getDepartamentos()[2]);
					}
				} else if (opcao2.equals("3")) {
					String numeroMatricula = JOptionPane.showInputDialog("Matrícula do funcionario");
					Funcionario f = empresa.procurarFuncionario(Integer.parseInt(numeroMatricula));
					valor = FolhaPagamento.calcularValorPagar(f);
				}
				JOptionPane.showMessageDialog(null, String.format("Valor total a pagar: %s", valor));

			} else {
				fechar = true;
			}
		}
	}

	// metodo refatorado - observacao: codigo do professor
	private static Funcionario obterDadosFuncionario(Empresa empresa) {
		String nome = JOptionPane.showInputDialog("Nome");
		String sobrenome = JOptionPane.showInputDialog("Sobrenome");
		String cpf = JOptionPane.showInputDialog("CPF");
		String dataContratacao = JOptionPane.showInputDialog("Data Contratacação");
		String salario = JOptionPane.showInputDialog("Salário");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = formatter.parse(dataContratacao, LocalDate::from);

		Cargo[] values = Cargo.values();
		String cargos = "";
		for (Cargo cargo : values) {
			cargos = cargos + cargo + "\n";
		}
		String cargo = JOptionPane.showInputDialog("Escolha um cargo: \n" + cargos);
		cargo = cargo.toUpperCase();

		// inicio bloco refatorado - observacao: codigo do professor
		Departamento departamento = null;

		boolean opcaoValida = false;
		while (!opcaoValida) {
			String opcaoDepartamento = JOptionPane
					.showInputDialog("Escolha um departamento: (1=Gerencia, 2=Operações, 3=P&D)");

			if (!opcaoDepartamento.equals("1") && !opcaoDepartamento.equals("2") && !opcaoDepartamento.equals("3")) {
				JOptionPane.showMessageDialog(null,
						"Opção de departamento inválida, por favor escolha uma opção valida");
			} else {
				int opcao = Integer.parseInt(opcaoDepartamento);
				departamento = empresa.getDepartamentos()[opcao - 1];
				opcaoValida = true;
			}
		}

		Funcionario f = new Funcionario(nome, sobrenome, cpf, data, new BigDecimal(salario), Cargo.valueOf(cargo),
				departamento);
		departamento.alocarFuncionario(f);
		System.out.println(String.format("Funcionário matricula: %s cadastrado no departamento: %s", f.getMatricula(),
				departamento.getNome()));
		// fim bloco refatorado - observacao: codigo do professor

		boolean cadastrarDependentes = true;
		while (cadastrarDependentes && f.getQuantidadeDependentes() < 2) {
			int dep = JOptionPane.showConfirmDialog(null, "Deseja cadastrar um dependente?");
			if (dep == 0) {
				Dependente dependente = obterDadosDependente();
				try {
					f.adicionarDependente(dependente);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			} else {
				cadastrarDependentes = false;
			}
		}

		return f;
	}

	private static Dependente obterDadosDependente() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String nomeDep = JOptionPane.showInputDialog("Nome");
		GrauDependencia[] valuesG = GrauDependencia.values();
		String graus = "";
		for (GrauDependencia g : valuesG) {
			graus = graus + g + "\n";
		}
		String escolhaGrau = JOptionPane.showInputDialog("Escolha o grau de dependência: \n" + graus);
		escolhaGrau = escolhaGrau.toUpperCase();
		String nascDep = JOptionPane.showInputDialog("Data de Nascimento");
		LocalDate dataDep = formatter.parse(nascDep, LocalDate::from);
		Dependente dependente = new Dependente(nomeDep, GrauDependencia.valueOf(escolhaGrau), dataDep);
		return dependente;
	}
}
