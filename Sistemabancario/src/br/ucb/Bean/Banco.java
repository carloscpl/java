package br.ucb.Bean;

import java.io.Serializable;

public class Banco implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long idBanco;
	private String banco;
	
	public Banco(){
		
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

}
