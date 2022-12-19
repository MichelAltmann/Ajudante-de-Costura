/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDominio;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aluno
 */
public abstract class Pessoa implements Serializable {

    private int idPessoa;
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private Date dataNascimento;
    private int cep;
    private String estado;
    private String cidade;
    private String rua;
    private int numero;

    public Pessoa(String cpf, String nome, String email, String telefone, Date dataNascimento, int cep, String estado, String cidade, String rua, int numero) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
    }

    public Pessoa(int idPessoa, String cpf, String nome, String email, String telefone, Date dataNascimento, int cep, String estado, String cidade, String rua, int numero) {
        this.idPessoa = idPessoa;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
    }

    public Pessoa(String cpf, String email) {
        this.cpf = cpf;
        this.email = email;
    }

    public Pessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "idPessoa=" + idPessoa + ", cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", dataNascimento=" + dataNascimento + ", cep=" + cep + ", estado=" + estado + ", cidade=" + cidade + ", rua=" + rua + ", numero=" + numero + '}';
    }

    private int calculaIdade() {
        int idade = 0;

        Date dataAtual = new Date();

        long millisAtual = dataAtual.getTime();
        long millisNascimento = this.dataNascimento.getTime();
        long millisIdade = millisAtual - millisNascimento;
        idade = (int) (millisIdade / 1000 / 60 / 60 / 24 / 365);

        return idade;
    }

}
