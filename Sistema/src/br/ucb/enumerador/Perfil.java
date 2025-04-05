package br.ucb.enumerador;

public enum Perfil {
	ADMINISTRADOR,
	USUARIO;
	
	public Perfil[] getValores() {
		return Perfil.values();
	}

}
