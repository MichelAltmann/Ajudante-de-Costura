package modelDominio;

import java.io.Serializable;


public class Administrador implements Serializable {
    private String login;
    private String senha;

    public Administrador(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Administrador{" + "login=" + login + ", senha=" + senha + '}';
    }

}
