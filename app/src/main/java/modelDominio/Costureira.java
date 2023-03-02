package modelDominio;

import java.io.Serializable;
import java.util.Date;

public class Costureira extends Pessoa implements Serializable {

    private String senha;
    private int autorizacao;

    public Costureira(String senha, int autorizacao, String cpf, String nome, String email, String telefone, Date dataNascimento, byte[] imagem, int cep, String estado, String cidade, String rua, int numero) {
        super(cpf, nome, email, telefone, dataNascimento, imagem, cep, estado, cidade, rua, numero);
        this.senha = senha;
        this.autorizacao = autorizacao;
    }

    public Costureira(String senha, int autorizacao, String idPessoa, String cpf, String nome, String email, String telefone, Date dataNascimento, byte[] imagem, int cep, String estado, String cidade, String rua, int numero) {
        super(idPessoa, cpf, nome, email, telefone, dataNascimento, imagem, cep, estado, cidade, rua, numero);
        this.senha = senha;
        this.autorizacao = autorizacao;
    }

    public Costureira(String senha, String email) {
        super(email);
        this.senha = senha;
    }

    public Costureira(String idPessoa) {
        super(idPessoa);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(int autorizacao) {
        this.autorizacao = autorizacao;
    }

    @Override
    public String toString() {
        return super.toString() + "Costureira{" + "senha=" + senha + ", autorizacao=" + autorizacao + '}';
    }

    public String getAutorizacaoLiteral() {
        String autorizacaoLiteral = "";

        if (this.getAutorizacao() == 0) {
            autorizacaoLiteral = "Não autorizada";
        } else if (this.getAutorizacao() == 1) {
            autorizacaoLiteral = "Autorizada";
        }
        return autorizacaoLiteral;
    }

}
