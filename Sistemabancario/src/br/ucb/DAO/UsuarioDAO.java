package br.ucb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.Bean.Usuario;
import br.ucb.enumerador.Perfil;


public class UsuarioDAO {
	private Connection con;

	public UsuarioDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}
	
	public int incluir(Usuario usuario) throws SQLException {
		if (usuario == null)
			return 0;
		String sql="INSERT INTO usuario (nome, usuario, senha, perfil) values (?, ?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, usuario.getNome());
		stmt.setString(2, usuario.getUsuario());
		stmt.setString(3, usuario.getSenha());
		stmt.setString(4, usuario.getPerfil().toString());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	/* 3.0-Metodo Consultar
	*  3.1-Comando sql para Consultar
	*  3.2-consultar por nomes
	*/
	
	public List<Usuario> listar(Usuario usuario2, String filtro ) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE nome LIKE ?";
		if(usuario2.getPerfil().equals("USUARIO")){
			sql =sql+" and perfil = 'USUARIO' "; 
		}
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setString(1, filtro + "%");
		ResultSet resposta = stmt.executeQuery();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		while (resposta.next()) {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(resposta.getLong("idUsuario"));
			usuario.setNome(resposta.getString("nome"));
			usuario.setUsuario(resposta.getString("usuario"));
			usuario.setSenha(resposta.getString("senha"));
			usuario.setPerfil(Perfil.valueOf(resposta.getString("perfil")));
			usuarios.add(usuario);
		}
		resposta.close();
		stmt.close();
		return usuarios;	
	}
	
	public List<Usuario> listar(Usuario usuario2) throws SQLException {
		int incremento = 0;  
		int limite = incremento;  
		int index = limite + 5; 
		String sql = "SELECT * FROM usuario LIMIT "+ limite + ", " + index;
		System.out.println(usuario2.getPerfil());
		/*if(usuario2.getPerfil().equals("USUARIO")){
			sql =sql+" and perfil = 'USUARIO' "; 
		}*/
		PreparedStatement stmt = this.con.prepareStatement(sql);
		ResultSet resposta = stmt.executeQuery();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		while (resposta.next()) {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(resposta.getLong("idUsuario"));
			usuario.setNome(resposta.getString("nome"));
			usuario.setUsuario(resposta.getString("usuario"));
			usuario.setSenha(resposta.getString("senha"));
			usuario.setPerfil(Perfil.valueOf(resposta.getString("perfil")));
			usuarios.add(usuario);
		}
		resposta.close();
		stmt.close();
		return usuarios;	
	}
	
	public List<Usuario> listarUsuario() throws SQLException {
		String sql = "SELECT * FROM usuario limit 3;";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		ResultSet resposta = stmt.executeQuery();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		while (resposta.next()) {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(resposta.getLong("idUsuario"));
			usuario.setNome(resposta.getString("nome"));
			usuario.setUsuario(resposta.getString("usuario"));
			usuario.setSenha(resposta.getString("senha"));
			usuario.setPerfil(Perfil.valueOf(resposta.getString("perfil")));
			usuarios.add(usuario);
		}
		resposta.close();
		stmt.close();
		return usuarios;
	}


	public Usuario consultar(Long idusuario) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE idUsuario=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, idusuario);
		ResultSet resposta = stmt.executeQuery();
		Usuario usuario = null;
		if (resposta.next()) {
			usuario = new Usuario();
			usuario.setIdUsuario(resposta.getLong("idUsuario"));
			usuario.setUsuario(resposta.getString("usuario"));
			usuario.setSenha(resposta.getString("senha"));
			usuario.setNome(resposta.getString("nome"));
			usuario.setPerfil(Perfil.valueOf(resposta.getString("perfil")));
		}
		resposta.close();
		stmt.close();
		return usuario;	
	}

	public Usuario consultar(String user) throws SQLException {
		try{
		String sql = "SELECT * FROM usuario WHERE usuario=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);	
		stmt.setString(1, user);
		ResultSet resposta = stmt.executeQuery();
		
		Usuario usuario = null;
		if (resposta.next()) {
			usuario = new Usuario();
			usuario.setIdUsuario(resposta.getLong("idUsuario"));
			usuario.setNome(resposta.getString("nome"));
			usuario.setUsuario(resposta.getString("usuario"));
			usuario.setSenha(resposta.getString("senha"));
			usuario.setPerfil(Perfil.valueOf(resposta.getString("perfil")));
		}
		resposta.close();
		stmt.close();
		return usuario;	
		}catch(NullPointerException e){
			e.printStackTrace();
			System.out.println("Erro");
		}
		return null;
	}
	
	public Usuario autenticar(String user, String password) throws SQLException {
		try{
		String sql = "SELECT * FROM usuario WHERE usuario=? and senha=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);	
		stmt.setString(1, user);
		stmt.setString(2,password);
		ResultSet resposta = stmt.executeQuery();
		
		Usuario usuario = null;
		if (resposta.next()) {
			usuario = new Usuario();
			usuario.setIdUsuario(resposta.getLong("idUsuario"));
			usuario.setNome(resposta.getString("nome"));
			usuario.setUsuario(resposta.getString("usuario"));
			usuario.setSenha(resposta.getString("senha"));
			usuario.setPerfil(Perfil.valueOf(resposta.getString("perfil")));
		}
		resposta.close();
		stmt.close();
		return usuario;	
		}catch(NullPointerException e){
			e.printStackTrace();
			System.out.println("Erro");
		}
		return null;
	}
	

	// Nao altera usuario (coluna usuario = username) e senha
	public int alterar(Usuario usuario) throws SQLException {
		if (usuario == null)
			return 0;
		String sql="UPDATE usuario SET nome=?, perfil=? WHERE idUsuario=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, usuario.getNome());
		stmt.setString(2, usuario.getPerfil().toString());
		stmt.setLong(3, usuario.getIdUsuario());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	// Nao altera usuario (coluna usuario = username) e senha
	public int trocaSenha(Usuario usuario) throws SQLException {
		if (usuario == null)
			return 0;
		String sql="UPDATE usuario SET senha=? WHERE idUsuario=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, usuario.getSenha());
		stmt.setLong(2, usuario.getIdUsuario());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	public int excluir(Usuario usuario) throws SQLException {
		if (usuario == null)
			return 0;
		String sql = "DELETE FROM usuario WHERE idUsuario=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, usuario.getIdUsuario());
		int retorno = stmt.executeUpdate();		
		stmt.close();
		return retorno;
	}


}
