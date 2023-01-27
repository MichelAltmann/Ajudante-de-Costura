package com.example.ajudantedecostura.modelDominio;

import java.io.Serializable;

public class Medidas implements Serializable {

    private float pescoco;
    private float ombro;
    private float busto;
    private float bico;
    private float cintura;
    private float quadris;
    private float manga;
    private float punho;
    private float larguraBraco;
    private float larguraCoxa;
    private float larguraCostas;
    private float alturaFrente;
    private float alturaCostas;
    private float alturaBusto;
    private float alturaQuadris;
    private float alturaGancho;
    private float alturaJoelho;
    private float comprimentoCalca;
    private float comprimentoBlusa;
    private float comprimentoSaia;

    public Medidas(float pescoco, float ombro, float busto, float bico, float cintura, float quadris, float manga, float punho, float larguraBraco, float larguraCoxa, float larguraCostas, float alturaFrente, float alturaCostas, float alturaBusto, float alturaQuadris, float alturaGancho, float alturaJoelho, float comprimentoCalca, float comprimentoBlusa, float comprimentoSaia) {
        this.pescoco = pescoco;
        this.ombro = ombro;
        this.busto = busto;
        this.bico = bico;
        this.cintura = cintura;
        this.quadris = quadris;
        this.manga = manga;
        this.punho = punho;
        this.larguraBraco = larguraBraco;
        this.larguraCoxa = larguraCoxa;
        this.larguraCostas = larguraCostas;
        this.alturaFrente = alturaFrente;
        this.alturaCostas = alturaCostas;
        this.alturaBusto = alturaBusto;
        this.alturaQuadris = alturaQuadris;
        this.alturaGancho = alturaGancho;
        this.alturaJoelho = alturaJoelho;
        this.comprimentoCalca = comprimentoCalca;
        this.comprimentoBlusa = comprimentoBlusa;
        this.comprimentoSaia = comprimentoSaia;
    }

    public float getPescoco() {
        return pescoco;
    }

    public void setPescoco(float pescoco) {
        this.pescoco = pescoco;
    }

    public float getOmbro() {
        return ombro;
    }

    public void setOmbro(float ombro) {
        this.ombro = ombro;
    }

    public float getBusto() {
        return busto;
    }

    public void setBusto(float busto) {
        this.busto = busto;
    }

    public float getBico() {
        return bico;
    }

    public void setBico(float bico) {
        this.bico = bico;
    }

    public float getCintura() {
        return cintura;
    }

    public void setCintura(float cintura) {
        this.cintura = cintura;
    }

    public float getQuadris() {
        return quadris;
    }

    public void setQuadris(float quadris) {
        this.quadris = quadris;
    }

    public float getManga() {
        return manga;
    }

    public void setManga(float manga) {
        this.manga = manga;
    }

    public float getPunho() {
        return punho;
    }

    public void setPunho(float punho) {
        this.punho = punho;
    }

    public float getLarguraBraco() {
        return larguraBraco;
    }

    public void setLarguraBraco(float larguraBraco) {
        this.larguraBraco = larguraBraco;
    }

    public float getLarguraCoxa() {
        return larguraCoxa;
    }

    public void setLarguraCoxa(float larguraCoxa) {
        this.larguraCoxa = larguraCoxa;
    }

    public float getLarguraCostas() {
        return larguraCostas;
    }

    public void setLarguraCostas(float larguraCostas) {
        this.larguraCostas = larguraCostas;
    }

    public float getAlturaFrente() {
        return alturaFrente;
    }

    public void setAlturaFrente(float alturaFrente) {
        this.alturaFrente = alturaFrente;
    }

    public float getAlturaCostas() {
        return alturaCostas;
    }

    public void setAlturaCostas(float alturaCostas) {
        this.alturaCostas = alturaCostas;
    }

    public float getAlturaBusto() {
        return alturaBusto;
    }

    public void setAlturaBusto(float alturaBusto) {
        this.alturaBusto = alturaBusto;
    }

    public float getAlturaQuadris() {
        return alturaQuadris;
    }

    public void setAlturaQuadris(float alturaQuadris) {
        this.alturaQuadris = alturaQuadris;
    }

    public float getAlturaGancho() {
        return alturaGancho;
    }

    public void setAlturaGancho(float alturaGancho) {
        this.alturaGancho = alturaGancho;
    }

    public float getAlturaJoelho() {
        return alturaJoelho;
    }

    public void setAlturaJoelho(float alturaJoelho) {
        this.alturaJoelho = alturaJoelho;
    }

    public float getComprimentoCalca() {
        return comprimentoCalca;
    }

    public void setComprimentoCalca(float comprimentoCalca) {
        this.comprimentoCalca = comprimentoCalca;
    }

    public float getComprimentoBlusa() {
        return comprimentoBlusa;
    }

    public void setComprimentoBlusa(float comprimentoBlusa) {
        this.comprimentoBlusa = comprimentoBlusa;
    }

    public float getComprimentoSaia() {
        return comprimentoSaia;
    }

    public void setComprimentoSaia(float comprimentoSaia) {
        this.comprimentoSaia = comprimentoSaia;
    }

    @Override
    public String toString() {
        return "Medidas{" + "pescoco=" + pescoco + ", ombro=" + ombro + ", busto=" + busto + ", bico=" + bico + ", cintura=" + cintura + ", quadris=" + quadris + ", manga=" + manga + ", punho=" + punho + ", larguraBraco=" + larguraBraco + ", larguraCoxa=" + larguraCoxa + ", larguraCostas=" + larguraCostas + ", alturaFrente=" + alturaFrente + ", alturaCostas=" + alturaCostas + ", alturaBusto=" + alturaBusto + ", alturaQuadris=" + alturaQuadris + ", alturaGancho=" + alturaGancho + ", alturaJoelho=" + alturaJoelho + ", comprimentoCalca=" + comprimentoCalca + ", comprimentoBlusa=" + comprimentoBlusa + ", comprimentoSaia=" + comprimentoSaia + '}';
    }

}
