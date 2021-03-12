package conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.table.DefaultTableModel;
import dados.Homem;
import dados.Mulher;
import saida.Visao;

public class PessoaDAO {
	private Connection con = Conexao.getConnection();

	public void cadastrarHomem(Homem homem) {
		String sql = "INSERT INTO pessoa (nome, saude, gestante, idade) VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, homem.getNome().toString());
			preparador.setString(2, homem.getSituacaoSaude().toString());
			preparador.setString(3, null);
			preparador.setInt(4, homem.getIdade());
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexão.");
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}
	}

	public void cadastrarMulher(Mulher mulher) {
		String sql = "INSERT INTO pessoa (nome, saude, gestante, idade) VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, mulher.getNome().toString());
			preparador.setString(2, mulher.getSituacaoSaude().toString());
			preparador.setString(3, mulher.getGestante().toString());
			preparador.setString(4, null);
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexão.");
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}
	}
	
	public void listaPessoa(DefaultTableModel modelo) {
		try {
			PreparedStatement pat;
			pat = con.prepareStatement("SELECT idPessoa, nome, saude, gestante, idade FROM PESSOA");
			ResultSet rsl = pat.executeQuery();
			String gestante = null;
			String saude = null;
			
			while(rsl.next()) {
				if(rsl.getString("saude").equals("T")) {
					saude = "contaminada em tratamento";
				}
				if(rsl.getString("saude").equals("C")) {
					saude = "contaminada curada";
				}
				if(rsl.getString("saude").equals("F")) {
					saude = "contaminada falecida";
				}
				if(rsl.getString("saude").equals("S")) {
						saude = "sem contaminação";
				}
				if(rsl.getString("gestante") == null) {
					gestante = "-";
				}else {
					if(rsl.getString("gestante").equals("S")) {
						gestante = "sim";
					}
					if(rsl.getString("gestante").equals("N")) {
						gestante = "não";
					}
					if(rsl.getString("gestante").equals("T")) {
						gestante = "não tem certeza";
					}
				}
				modelo.addRow(new Object[] {rsl.getString("idPessoa"), rsl.getString("nome").toLowerCase(), saude, rsl.getString("idade"), gestante});
			}	
		}catch(SQLException ex) {
			Visao.mostrarMensagem("ERRO");
		}finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexão.");
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}
	}

	public void mostraPessoa(int id) {
		try {
			PreparedStatement pat;
			pat = con.prepareStatement("SELECT idPessoa, nome, saude, gestante, idade FROM PESSOA WHERE idPessoa = ?");
			pat.setInt(1, id);
			ResultSet rsl = pat.executeQuery();
			if (rsl.next() == false) {
				Visao.mostrarMensagem("ID não existe, tente novamente");
			} else {
				String saude = null;
				String gestante = null;
				String idade;
				if(rsl.getString("idade") == null) {
					idade = " ";
				}else {
					idade = rsl.getString("idade");
				}
				if (rsl.getString("saude").equals("T")) {
					saude = "contaminada em Tratamento";
				}
				if (rsl.getString("saude").equals("C")) {
					saude = "contaminada Curada";
				}
				if (rsl.getString("saude").equals("F")) {
					saude = "contaminada Falecida";
				}
				if (rsl.getString("saude").equals("S")) {
					saude = "sem Contaminação";
				}
				if (rsl.getString("gestante") == null) {
					gestante = " ";
				} else {
					if (rsl.getString("gestante").equals("S")) {
						gestante = "Sim";
					}
					if (rsl.getString("gestante").equals("N")) {
						gestante = "Não";
					}
					if (rsl.getString("gestante").equals("T")) {
						gestante = "não Tem certeza";
					}
					
				}
				Visao.mostrarMensagem("ID: " + rsl.getString("idPessoa") + "\n" + "NOME: " + rsl.getString("nome")
						+ "\n" + "SITUAÇÃO DE SAÚDE: " + saude + "\n" + "GESTANTE: "
						+ gestante + "\n" + "IDADE: " + idade);
			}
		} catch (SQLException ex) {
			Visao.mostrarErro("ERRO");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexão.");
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}
	}

	public void pesquisaPessoa(String nome, DefaultTableModel modelo) {
		try {
			PreparedStatement pat;
			pat = con.prepareStatement("SELECT idPessoa, nome, saude, gestante, idade FROM PESSOA WHERE nome LIKE ? ORDER BY nome");
			pat.setString(1, "%" + nome + "%");
			ResultSet rsl = pat.executeQuery();
			if (rsl.next() == false) {
				Visao.mostrarMensagem("Nome não cadastrado, tente novamente");
			} else {
				modelo.setNumRows(0);
				do {
					String saude = null;
					String gestante = null;
					if (rsl.getString("saude").equals("T")) {
						saude = "contaminada em tratamento";
					}
					if (rsl.getString("saude").equals("C")) {
						saude = "contaminada curada";
					}
					if (rsl.getString("saude").equals("F")) {
						saude = "contaminada falecida";
					}
					if (rsl.getString("saude").equals("S")) {
						saude = "sem contaminação";
					}
					if (rsl.getString("gestante") == null) {
						gestante = "-";
					} else {
						if (rsl.getString("gestante").equals("S")) {
							gestante = "sim";
						}
						if (rsl.getString("gestante").equals("N")) {
							gestante = "não";
						}
						if (rsl.getString("gestante").equals("T")) {
							gestante = "não tem certeza";
						}
					} 
	
					modelo.addRow(new Object[] {rsl.getString("idPessoa"), rsl.getString("nome").toLowerCase(), saude,
							rsl.getString("idade"), gestante});
				} while (rsl.next());			
			}

		} catch (SQLException ex) {
			Visao.mostrarMensagem("ERRO");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					System.out.print("Falha ao fechar a conexão.");
					System.out.println("Causa: " + ex.getMessage());
				}
			}
		}
	}

	public void quantidade() {
		int saude_c = 0;
		int saude_t = 0;
		int saude_f = 0;
		int saude_s_h = 0;
		int saude_s_m = 0;
		NumberFormat format = new DecimalFormat("00");

		try {
			PreparedStatement pat;
			pat = con.prepareStatement("SELECT idPessoa, nome, saude, gestante, idade FROM PESSOA");
			ResultSet rsl = pat.executeQuery();
			while (rsl.next()) {
				if (rsl.getString("saude").equals("C")) {
					saude_c++;
				}
				if (rsl.getString("saude").equals("T")) {
					saude_t++;
				}
				if (rsl.getString("saude").equals("F")) {
					saude_f++;
				}
				if (rsl.getString("saude").equals("S") && rsl.getCharacterStream("gestante") == null) {
					saude_s_h++;
				}
				if (rsl.getString("saude").equals("S") && rsl.getCharacterStream("gestante") != null) {
					saude_s_m++;
				}
			}

			System.out.println("                    " + format.format(saude_c) + " = CONTAMINADOS CURADOS");
			System.out.println("                    " + format.format(saude_t) + " = CONTAMINADOS EM TRATAMENTO");
			System.out.println("                    " + format.format(saude_f) + " = CONTAMINADOS FALECIDOS");
			System.out.println("                    " + format.format(saude_s_h) + " = HOMENS SEM CONTAMINAÇÃO");
			System.out.println("                    " + format.format(saude_s_m) + " = MULHERES SEM CONTAMINAÇÃO");
			System.err.format("                    " + format.format(saude_c + saude_t + saude_f + saude_s_h + saude_s_m)
					+ " = TOTAL DE REGISTROS DE PESSOAS");

		} catch (SQLException ex) {
			Visao.mostrarMensagem("ERRO");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexão.");
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}
	}
}
