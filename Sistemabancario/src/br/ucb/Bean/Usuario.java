package br.ucb.Bean;

import java.io.Serializable;

import br.ucb.enumerador.Perfil;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long idUsuario;
	private String nome;
	private String senha;
	private Perfil perfil;
	private String usuario;

	public Usuario() {
		
	}

	public boolean validarSenha(String senha) {
		return this.senha.equals(senha);
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
