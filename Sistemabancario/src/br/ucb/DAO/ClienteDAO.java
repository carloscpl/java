package br.ucb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.Bean.Cliente;

public class ClienteDAO {
	
	private Connection con;

	public ClienteDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}

	public int incluir(Cliente cliente) throws SQLException {
		if (cliente == null) return 0;
		PreparedStatement stmt = con.prepareStatement("INSERT INTO cliente (nome,rg,cpf) values (?,?,?)");
		stmt.setString(1, cliente.getNome());
		stmt.setString(2, cliente.getRg());
		stmt.setString(3, cliente.getCpf());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}		
	
	/* 3.0-Metodo Consultar
	*/
	public Cliente consultar(Long idCliente) throws SQLException {
		String sql = "SELECT * FROM cliente WHERE idCliente=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, idCliente);
		ResultSet resposta = stmt.executeQuery();
		Cliente cliente = null;
		if (resposta.next()) {
			cliente = new Cliente();
			cliente.setIdCliente(resposta.getLong("idCliente"));
			cliente.setNome(resposta.getString("nome"));
			cliente.setRg(resposta.getString("rg"));
			cliente.setCpf(resposta.getString("cpf"));
		}
		
		resposta.close();
		stmt.close();
		return cliente;
	}
	
	
	//Metodo para listar
	public List<Cliente> listar() throws SQLException {
		String sql = "select * from cliente;";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		ResultSet resposta = stmt.executeQuery();
		List<Cliente> clientes = new ArrayList<Cliente>();
		while (resposta.next()) {
			Cliente cliente = new Cliente();
			cliente.setIdCliente(resposta.getLong("idCliente"));
			cliente.setNome(resposta.getString("nome"));
			cliente.setRg(resposta.getString("rg"));
			cliente.setCpf(resposta.getString("cpf"));
			clientes.add(cliente);
		}
		resposta.close();
		stmt.close();
		return clientes;
	}
	
	/* 3.0-Metodo para listar todos os bancos
	*/
	public List<Cliente> listarCliente(Long idCliente) throws SQLException {
		String sql = "SELECT * FROM cliente WHERE idCliente=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, idCliente);
		ResultSet resposta = stmt.executeQuery();
	    List<Cliente> clientes = new ArrayList<Cliente>();
	    while (resposta.next()) {
	    	Cliente cliente = new Cliente();
	    	cliente.setIdCliente(resposta.getLong("idCliente"));
			cliente.setNome(resposta.getString("nome"));
			cliente.setRg(resposta.getString("rg"));
			cliente.setCpf(resposta.getString("cpf"));
			clientes.add(cliente);
		}
		resposta.close();
		stmt.close();
		return clientes;	
	}
	
	/* 3.0-Metodo para listar Like
	*/
	public List<Cliente> listar(String filtro) throws SQLException {
		String sql = "SELECT * FROM cliente WHERE nome LIKE ?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setString(1, filtro + "%");
		ResultSet resposta = stmt.executeQuery();
		List<Cliente> clientes = new ArrayList<Cliente>();
		while (resposta.next()) {
			Cliente cliente = new Cliente();
			cliente.setIdCliente(resposta.getLong("idCliente"));
			cliente.setNome(resposta.getString("nome"));
			cliente.setRg(resposta.getString("rg"));
			cliente.setCpf(resposta.getString("cpf"));
			clientes.add(cliente);
		}
		resposta.close();
		stmt.close();
		return clientes;	
	}
	
	 //4.0-Metodo Alterar 
	
	public int alterar(Cliente cliente) throws SQLException {
		if (cliente == null)
			return 0;

		String sql = "UPDATE cliente SET nome=?, rg=?, cpf=? WHERE idCliente=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setString(1, cliente.getNome());
		stmt.setString(2, cliente.getRg());
		stmt.setString(3, cliente.getCpf());
		stmt.setLong(4, cliente.getIdCliente());
		
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	/*
	 * 5.0-Metodo Excluir 
	 */
	public int excluir(Cliente cliente) throws SQLException {
		if (cliente == null)
			return 0;

		String sql = "DELETE FROM cliente WHERE idCliente=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, cliente.getIdCliente());

		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

}
