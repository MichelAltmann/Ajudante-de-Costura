package com.example.ajudantedecostura.modelDominio;

import java.io.Serializable;
import java.util.Date;

public class Costureira extends Pessoa implements Serializable {

    private String senha;
    private byte[] imagem;
    private int autorizacao;

    public Costureira(String senha, byte[] imagem, String cpf, String nome, String email, String telefone, Date dataNascimento, int cep, String estado, String cidade, String rua, int numero, int autorizacao) {
        super(cpf, nome, email, telefone, dataNascimento, cep, estado, cidade, rua, numero);
        this.senha = senha;
        this.imagem = imagem;
        this.autorizacao = autorizacao;
    }

    public Costureira(String senha, byte[] imagem, int idPessoa, String cpf, String nome, String email, String telefone, Date dataNascimento, int cep, String estado, String cidade, String rua, int numero, int autorizacao) {
        super(idPessoa, cpf, nome, email, telefone, dataNascimento, cep, estado, cidade, rua, numero);
        this.senha = senha;
        this.imagem = imagem;
        this.autorizacao = autorizacao;
    }

    public Costureira(String senha, String cpf, String email) {
        super(cpf, email);
        this.senha = senha;
    }

    public Costureira(int idPessoa) {
        super(idPessoa);
    }



    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public int getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(int autorizacao) {
        this.autorizacao = autorizacao;
    }

    @Override
    public String toString() {
        return super.toString() + "Costureira{" + "senha=" + senha + ", imagem=" + imagem + '}';
    }

}