package br.ucb.Bean;

import java.io.Serializable;

public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long idConta;
	private Agencia agencia;
	private Long operacao;
	private Long conta;
	private String tipoConta;
	private Cliente cliente;
	
	public Conta(){
		this.agencia = new Agencia();
		this.cliente = new Cliente();
	}

	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Long getOperacao() {
		return operacao;
	}

	public void setOperacao(Long operacao) {
		this.operacao = operacao;
	}

	public Long getConta() {
		return conta;
	}

	public void setConta(Long conta) {
		this.conta = conta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
