package br.ucb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.ucb.Bean.Conta;

public class ContaDAO {
	private Connection con;
	
	public ContaDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}

	/*
	 * 1.0-Metodo Incluir 
	 */
	public int incluir(Conta conta) throws SQLException {
		if (conta == null)
			return 0;
		String sql = "INSERT INTO conta (idAgencia, operacao, conta, tipoConta, idCliente) values (?, ?, ?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, conta.getAgencia().getIdAgencia());
		stmt.setLong(2, conta.getOperacao());
		stmt.setLong(3, conta.getConta());
		stmt.setString(4, conta.getTipoConta());
		stmt.setLong(5, conta.getCliente().getIdCliente());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	/*
	 * 2.0-Metodo Listar 
	 */
	public List<Conta> listar() throws SQLException {
		String sql = "SELECT * FROM conta;";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		ResultSet resposta = stmt.executeQuery();
		List<Conta> contas = new ArrayList<Conta>();
		while (resposta.next()) {
			Conta conta = new Conta();
			conta.setIdConta(resposta.getLong("idConta"));
			conta.setAgencia(new AgenciaDAO().consultar(resposta.getLong("idAgencia")));
			conta.setOperacao(resposta.getLong("operacao"));
			conta.setConta(resposta.getLong("conta"));
			conta.setTipoConta(resposta.getString("tipoConta"));
			conta.setCliente(new ClienteDAO().consultar(resposta.getLong("idCliente")));
			contas.add(conta);
		}
		resposta.close();
		stmt.close();
		return contas;
	}

	/*
	 * 3.0-Metodo Consultar 
	 */
	public Conta consultar(Long idConta) throws SQLException {
		String sql = "SELECT * FROM conta WHERE idConta=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, idConta);
		ResultSet resposta = stmt.executeQuery();
		Conta conta = null;
		if (resposta.next()) {
			conta = new Conta();
			conta.setIdConta(resposta.getLong("idConta"));
			conta.setAgencia(new AgenciaDAO().consultar(resposta.getLong("idAgencia")));
			conta.setOperacao(resposta.getLong("operacao"));
			conta.setConta(resposta.getLong("conta"));
			conta.setTipoConta(resposta.getString("tipoConta"));
			conta.setCliente(new ClienteDAO().consultar(resposta.getLong("idCliente")));

		}
		resposta.close();
		stmt.close();
		return conta;
	}
	
	/* 3.0-Metodo para listar todos 
	*/
	public List<Conta> listar(Long idConta) throws SQLException {
		String sql = "SELECT * FROM conta WHERE idConta=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, idConta);
		ResultSet resposta = stmt.executeQuery();
	    List<Conta> contas = new ArrayList<Conta>();
	    while (resposta.next()) {
		    Conta conta = new Conta();
		    conta.setIdConta(resposta.getLong("idConta"));
			conta.setAgencia(new AgenciaDAO().consultar(resposta.getLong("idAgencia")));
			conta.setOperacao(resposta.getLong("operacao"));
			conta.setConta(resposta.getLong("conta"));
			conta.setTipoConta(resposta.getString("tipoConta"));
			conta.setCliente(new ClienteDAO().consultar(resposta.getLong("idCliente")));
			contas.add(conta);
		}
		resposta.close();
		stmt.close();
		return contas;	
	}
	
	//3.0-Metodo Consultar
	public List<Conta> listar(String filtro) throws SQLException {
		String sql = "select conta.* from cliente inner join conta on conta.idCliente = cliente.idCliente where cliente.nome LIKE ?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setString(1, filtro + "%");
		ResultSet resposta = stmt.executeQuery();
		List<Conta> contas = new ArrayList<Conta>();
		while (resposta.next()) {
			Conta conta = new Conta();
			conta.setIdConta(resposta.getLong("idConta"));
			conta.setAgencia(new AgenciaDAO().consultar(resposta.getLong("idAgencia")));
			conta.setOperacao(resposta.getLong("operacao"));
			conta.setConta(resposta.getLong("conta"));
			conta.setTipoConta(resposta.getString("tipoConta"));
			conta.setCliente(new ClienteDAO().consultar(resposta.getLong("idCliente")));
			contas.add(conta);
		}
		resposta.close();
		stmt.close();
		return contas;	
	}
	
	// 4.0-Metodo Alterar 
	public int alterar(Conta conta) throws SQLException {
		if (conta == null)
			return 0;

		String sql = "UPDATE conta SET idAgencia=?, operacao=?, conta=?, tipoConta=?, idCliente=? WHERE idConta=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, conta.getAgencia().getIdAgencia());
		stmt.setLong(2, conta.getOperacao());
		stmt.setLong(3, conta.getConta());
		stmt.setString(4, conta.getTipoConta());
		stmt.setLong(5, conta.getCliente().getIdCliente());
		stmt.setLong(6, conta.getIdConta());

		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	// Excluir registros
	public int excluir(Conta conta) throws SQLException {
		if (conta == null)
			return 0;

		String sql = "DELETE FROM conta WHERE idConta=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, conta.getIdConta());

		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}


}
