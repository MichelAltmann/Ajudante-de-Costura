package com.example.ajudantedecostura.modelDominio;

import java.io.Serializable;

public class Cliente extends Pessoa implements Serializable {

    private Costureira costureira;

    public Cliente(Costureira costureira, String cpf, String nome, String email, String telefone, Date dataNascimento, int cep, String estado, String cidade, String rua, int numero) {
        super(cpf, nome, email, telefone, dataNascimento, cep, estado, cidade, rua, numero);
        this.costureira = costureira;
    }

    public Cliente(Costureira costureira, int idPessoa, String cpf, String nome, String email, String telefone, Date dataNascimento, int cep, String estado, String cidade, String rua, int numero) {
        super(idPessoa, cpf, nome, email, telefone, dataNascimento, cep, estado, cidade, rua, numero);
        this.costureira = costureira;
    }

    public Cliente(Costureira costureira, int idPessoa) {
        super(idPessoa);
        this.costureira = costureira;
    }

    public Costureira getCostureira() {
        return costureira;
    }

    public void setCostureira(Costureira costureira) {
        this.costureira = costureira;
    }

}