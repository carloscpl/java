package br.ucb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.Bean.Banco;


public class BancoDAO {
	private Connection con;

	public BancoDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}

	public int incluir(Banco banco) throws SQLException {
		if (banco == null) return 0;
		PreparedStatement stmt = con.prepareStatement("INSERT INTO banco (banco) values (?)");
		stmt.setString(1, banco.getBanco());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}		
	
	/* 3.0-Metodo Consultar
	*/
	public Banco consultar(Long idBanco) throws SQLException {
		String sql = "SELECT * FROM banco WHERE idBanco=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, idBanco);
		ResultSet resposta = stmt.executeQuery();
		Banco banco = null;
		if (resposta.next()) {
			banco = new Banco();
			banco.setIdBanco(resposta.getLong("idBanco"));
			banco.setBanco(resposta.getString("banco"));
		}
		
		resposta.close();
		stmt.close();
		return banco;
	}
	
	
	//Metodo para listar
	public List<Banco> listar() throws SQLException {
		String sql = "select * from banco;";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		ResultSet resposta = stmt.executeQuery();
		List<Banco> bancos = new ArrayList<Banco>();
		while (resposta.next()) {
			Banco banco = new Banco();
			banco.setIdBanco(resposta.getLong("idBanco"));
			banco.setBanco(resposta.getString("banco"));
			bancos.add(banco);
		}
		resposta.close();
		stmt.close();
		return bancos;
	}
	
	/* 3.0-Metodo para listar todos os bancos
	*/
	public List<Banco> listarBanco(Long idBanco) throws SQLException {
		String sql = "SELECT * FROM banco WHERE idBanco=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, idBanco);
		ResultSet resposta = stmt.executeQuery();
	    List<Banco> clientes = new ArrayList<Banco>();
	    while (resposta.next()) {
	    	Banco banco = new Banco();
	    	banco.setIdBanco(resposta.getLong("idBanco"));
			banco.setBanco(resposta.getString("banco"));
			clientes.add(banco);
		}
		resposta.close();
		stmt.close();
		return clientes;	
	}
	
	/* 3.0-Metodo para listar Like
	*/
	public List<Banco> listar(String filtro) throws SQLException {
		String sql = "SELECT * FROM banco WHERE banco LIKE ?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setString(1, filtro + "%");
		ResultSet resposta = stmt.executeQuery();
		List<Banco> bancos = new ArrayList<Banco>();
		while (resposta.next()) {
			Banco banco = new Banco();
			banco.setIdBanco(resposta.getLong("idBanco"));
			banco.setBanco(resposta.getString("banco"));
			bancos.add(banco);
		}
		resposta.close();
		stmt.close();
		return bancos;	
	}
	
	 //4.0-Metodo Alterar 
	
	public int alterar(Banco banco) throws SQLException {
		if (banco == null)
			return 0;

		String sql = "UPDATE banco SET banco=? WHERE idBanco=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setString(1, banco.getBanco());
		stmt.setLong(2, banco.getIdBanco());
		
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	/*
	 * 5.0-Metodo Excluir 
	 */
	public int excluir(Banco banco) throws SQLException {
		if (banco == null)
			return 0;

		String sql = "DELETE FROM banco WHERE idBanco=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, banco.getIdBanco());

		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}
}
