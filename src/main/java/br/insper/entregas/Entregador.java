package br.insper.entregas;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class Entregador {

    @NotBlank
    private String nome;
    @Email
    private String email;
    @NotBlank
    private String documento;
    @NotBlank
    private String veiculo;

    private Boolean ativo;

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }
}
