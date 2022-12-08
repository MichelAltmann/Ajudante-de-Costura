/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDominio;

import java.io.Serializable;

/**
 *
 * @author aluno
 */
public class Material implements Serializable {

    private int idMaterial;
    private String tipo;
    private float comprimento;
    private float largura;
    private float precoMetro;

    public Material(int idMaterial, String tipo, float comprimento, float largura, float precoMetro) {
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

    public Material(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
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

}
