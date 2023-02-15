package modelDominio;

import java.io.Serializable;

public class Material implements Serializable {

    private String idMaterial;
    private String tipo;
    private float comprimento;
    private float largura;
    private float precoMetro;

    public Material(String idMaterial, String tipo, float comprimento, float largura, float precoMetro) {
        this.idMaterial = idMaterial;
        this.tipo = tipo;
        this.comprimento = comprimento;
        this.largura = largura;
        this.precoMetro = precoMetro;
    }

    public Material(String tipo, float comprimento, float largura, float precoMetro) {
        this.tipo = tipo;
        this.comprimento = comprimento;
        this.largura = largura;
        this.precoMetro = precoMetro;
    }

    public String getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(String idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getComprimento() {
        return comprimento;
    }

    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float getPreco() {
        return precoMetro;
    }

    public void setPreco(float preco) {
        this.precoMetro = preco;
    }

    private float CalculaArea() {
        float area = 0;
        area = this.largura * this.comprimento;
        return area;
    }

    public float CalculaPrecoTotal() {
        float precoTotal = 0;
        precoTotal = this.precoMetro * this.comprimento;
        return precoTotal;
    }

    @Override
    public String toString() {
        return "Material{" + "idMaterial=" + idMaterial + ", tipo=" + tipo + ", comprimento=" + comprimento + ", largura=" + largura + ", precoMetro=" + precoMetro + '}';
    }

}
