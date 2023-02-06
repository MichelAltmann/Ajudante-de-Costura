package modelDominio;

import java.io.Serializable;

public class Medida implements Serializable {

    Integer pos;
    private String nome;
    private float medida;


    public Medida(Integer pos, String nome, float medida) {
        this.pos = pos;
        this.nome = nome;
        this.medida = medida;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getMedida() {
        return medida;
    }

    public void setMedida(float medida) {
        this.medida = medida;
    }
}
