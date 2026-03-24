package br.insper.entregas;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class Entrega {

    @NotBlank
    private String id;
    private LocalDateTime dataSolicitacao;
    private Cliente cliente;
    private Entregador entregador;
    private Boolean ativo;

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Entrega(String id, LocalDateTime dataSolicitacao, Cliente cliente, boolean ativo, Entregador entregador) {
        this.id = id;
        this.dataSolicitacao = dataSolicitacao;
        this.cliente = cliente;
        this.entregador = entregador;
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }
}
