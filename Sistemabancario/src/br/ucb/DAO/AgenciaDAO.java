package br.ucb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.Bean.Agencia;

public class AgenciaDAO {
	private Connection con;

	public AgenciaDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}

	/*
	 * 1.0-Metodo Incluir 
	 */
	public int incluir(Agencia agencia) throws SQLException {
		if (agencia == null)
			return 0;
		String sql = "INSERT INTO agencia (nomeAgencia, idBanco) values (?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, agencia.getNomeAgencia());
		stmt.setLong(2, agencia.getBanco().getIdBanco());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	/*
	 * 2.0-Metodo Listar 
	 */
	public List<Agencia> listar() throws SQLException {
		String sql = "SELECT * FROM agencia;";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		ResultSet resposta = stmt.executeQuery();
		List<Agencia> agencias = new ArrayList<Agencia>();
		while (resposta.next()) {
			Agencia agencia = new Agencia();
			agencia.setIdAgencia(resposta.getLong("idAgencia"));
			agencia.setNomeAgencia(resposta.getString("nomeAgencia"));
			agencia.setBanco(new BancoDAO().consultar(resposta.getLong("idBanco")));
			agencias.add(agencia);
		}
		resposta.close();
		stmt.close();
		return agencias;
	}

	/*
	 * 3.0-Metodo Consultar 
	 */
	public Agencia consultar(Long idAgencia) throws SQLException {
		String sql = "SELECT * FROM agencia WHERE idAgencia=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, idAgencia);
		ResultSet resposta = stmt.executeQuery();
		Agencia agencia = null;
		if (resposta.next()) {
			agencia = new Agencia();
			agencia.setIdAgencia(resposta.getLong("idAgencia"));
			agencia.setNomeAgencia(resposta.getString("nomeAgencia"));
			agencia.setBanco(new BancoDAO().consultar(resposta.getLong("idBanco")));

		}
		resposta.close();
		stmt.close();
		return agencia;
	}
	
	/* 3.0-Metodo para listar todos 
	*/
	public List<Agencia> listar(Long idAgencia) throws SQLException {
		String sql = "SELECT * FROM agencia WHERE idAgencia=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, idAgencia);
		ResultSet resposta = stmt.executeQuery();
	    List<Agencia> agencias = new ArrayList<Agencia>();
	    while (resposta.next()) {
		    Agencia agencia = new Agencia();
			agencia.setIdAgencia(resposta.getLong("idAgencia"));
			agencia.setNomeAgencia(resposta.getString("nomeAgencia"));
			agencia.setBanco(new BancoDAO().consultar(resposta.getLong("idBanco")));
			agencias.add(agencia);
		}
		resposta.close();
		stmt.close();
		return agencias;	
	}
	
	//3.0-Metodo Consultar
	public List<Agencia> listar(String filtro) throws SQLException {
		String sql = "SELECT * FROM agencia WHERE nomeAgencia LIKE ?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setString(1, filtro + "%");
		ResultSet resposta = stmt.executeQuery();
		List<Agencia> agencias = new ArrayList<Agencia>();
		while (resposta.next()) {
			Agencia agencia = new Agencia();
			agencia.setIdAgencia(resposta.getLong("idAgencia"));
			agencia.setNomeAgencia(resposta.getString("nomeAgencia"));
			agencia.setBanco(new BancoDAO().consultar(resposta.getLong("idBanco")));
			agencias.add(agencia);
		}
		resposta.close();
		stmt.close();
		return agencias;	
	}
	
	// 4.0-Metodo Alterar 
	public int alterar(Agencia agencia) throws SQLException {
		if (agencia == null)
			return 0;

		String sql = "UPDATE agencia SET nomeAgencia=?, idBanco=? WHERE idAgencia=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setString(1, agencia.getNomeAgencia());
		stmt.setLong(2, agencia.getBanco().getIdBanco());
		stmt.setLong(3, agencia.getIdAgencia());

		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	// Excluir registros
	public int excluir(Agencia agencia) throws SQLException {
		if (agencia == null)
			return 0;

		String sql = "DELETE FROM agencia WHERE idAgencia=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, agencia.getIdAgencia());

		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

}
