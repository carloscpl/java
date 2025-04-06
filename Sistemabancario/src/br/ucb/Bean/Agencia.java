package br.ucb.Bean;

import java.io.Serializable;

public class Agencia implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long idAgencia;
	private String nomeAgencia;
	private Banco banco;
	
	public Agencia(){
		this.banco = new Banco();
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

}
