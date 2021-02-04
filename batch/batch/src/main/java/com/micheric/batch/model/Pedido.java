package com.micheric.batch.model;

public class Pedido {

	private int numeroMesa;
	private String nomePrato;
	private int quantidade;
	private boolean cupom;

	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Pedido(int numeroMesa, String nomePrato, int quantidade, boolean cupom, double valor) {
		this.numeroMesa = numeroMesa;
		this.nomePrato = nomePrato;
		this.quantidade = quantidade;
		this.cupom = cupom;
		this.valor = valor;
	}

	public boolean isCupom() {
		return cupom;
	}

	public void setCupom(boolean cupom) {
		this.cupom = cupom;
	}

	private double valor;

	public int getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(int numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public String getNomePrato() {
		return nomePrato;
	}

	public void setNomePrato(String nomePrato) {
		this.nomePrato = nomePrato;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Pedido [numeroMesa=" + numeroMesa + ", nomePrato=" + nomePrato + ", quantidade=" + quantidade
				+ ", cupom=" + cupom + ", valor=" + valor + "]";
	}

}
