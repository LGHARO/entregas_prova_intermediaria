package br.insper.entregas;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class EntregaDTO {
    @NotBlank
    private String id;
    private LocalDateTime dataSolicitacao;
    private Cliente cliente;
    private Boolean ativo;

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
