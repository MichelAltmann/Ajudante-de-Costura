package com.example.ajudantedecostura.home.pedido;

import android.annotation.SuppressLint;

import java.util.ArrayList;

import modelDominio.Medida;
import modelDominio.Medidas;

public class TransformaEmMedidas {
    
    public static Medidas transformaEmMedidas(ArrayList<Medida> listaMedidas) {

        float pescoco = 0;
        float ombro = 0;
        float busto = 0;
        float bico = 0;
        float cintura = 0;
        float quadris = 0;
        float manga = 0;
        float punho = 0;
        float larguraBraco = 0;
        float larguraCoxa = 0;
        float larguraCostas = 0;
        float alturaFrente = 0;
        float alturaCostas = 0;
        float alturaBusto = 0;
        float alturaQuadris = 0;
        float alturaGancho = 0;
        float alturaJoelho = 0;
        float comprimentoCalca = 0;
        float comprimentoBlusa = 0;
        float comprimentoSaia = 0;

        Medidas medidas = null;

        for (int i = 1; i <= listaMedidas.size(); i++) {
            switch (listaMedidas.get(i - 1).getPos()) {
                case 1:
                    pescoco = listaMedidas.get(i - 1).getMedida();
                    break;
                case 2:
                    ombro = listaMedidas.get(i - 1).getMedida();
                case 3:
                    busto = listaMedidas.get(i - 1).getMedida();
                    break;
                case 4:
                    bico = listaMedidas.get(i - 1).getMedida();
                    break;
                case 5:
                    cintura = listaMedidas.get(i - 1).getMedida();
                    break;
                case 6:
                    quadris = listaMedidas.get(i - 1).getMedida();
                    break;
                case 7:
                    manga = listaMedidas.get(i - 1).getMedida();
                    break;
                case 8:
                    punho = listaMedidas.get(i - 1).getMedida();
                    break;
                case 9:
                    larguraBraco = listaMedidas.get(i - 1).getMedida();
                    break;
                case 10:
                    larguraCoxa = listaMedidas.get(i - 1).getMedida();
                    break;
                case 11:
                    larguraCostas = listaMedidas.get(i - 1).getMedida();
                    break;
                case 12:
                    alturaFrente = listaMedidas.get(i - 1).getMedida();
                    break;
                case 13:
                    alturaCostas = listaMedidas.get(i - 1).getMedida();
                    break;
                case 14:
                    alturaBusto = listaMedidas.get(i - 1).getMedida();
                    break;
                case 15:
                    alturaQuadris = listaMedidas.get(i - 1).getMedida();
                    break;
                case 16:
                    alturaGancho = listaMedidas.get(i - 1).getMedida();
                    break;
                case 17:
                    alturaJoelho = listaMedidas.get(i - 1).getMedida();
                    break;
                case 18:
                    comprimentoCalca = listaMedidas.get(i - 1).getMedida();
                    break;
                case 19:
                    comprimentoBlusa = listaMedidas.get(i - 1).getMedida();
                    break;
                case 20:
                    comprimentoSaia = listaMedidas.get(i - 1).getMedida();
                    break;
                default:
                    break;
            }
        }

        medidas = new Medidas(pescoco,
                ombro,
                busto,
                bico,
                cintura,
                quadris,
                manga,
                punho,
                larguraBraco,
                larguraCoxa,
                larguraCostas,
                alturaFrente,
                alturaCostas,
                alturaBusto,
                alturaQuadris,
                alturaGancho,
                alturaJoelho,
                comprimentoCalca,
                comprimentoBlusa,
                comprimentoSaia);

        return medidas;
    }

    public static ArrayList<Medida> transformaEmLista(Medidas medidas) {
        ArrayList<Medida> result = new ArrayList<>();
        if (medidas.getPescoco() > 0) {
            result.add(new Medida(1,"Pescoço", medidas.getPescoco()));
        }
        if (medidas.getOmbro() > 0) {
            result.add(new Medida(2,"Ombro", medidas.getOmbro()));
        }
        if (medidas.getBusto() > 0) {
            result.add(new Medida(3,"Busto", medidas.getBusto()));
        }
        if (medidas.getBico() > 0) {
            result.add(new Medida(4,"Bico", medidas.getBico()));
        }
        if (medidas.getCintura() > 0) {
            result.add(new Medida(5,"Cintura", medidas.getCintura()));
        }
        if (medidas.getQuadris() > 0) {
            result.add(new Medida(6,"Quadris", medidas.getQuadris()));
        }
        if (medidas.getManga() > 0) {
            result.add(new Medida(7,"Manga", medidas.getManga()));
        }
        if (medidas.getPunho() > 0) {
            result.add(new Medida(8,"Punho", medidas.getPunho()));
        }
        if (medidas.getLarguraBraco() > 0) {
            result.add(new Medida(9,"largura do Braco", medidas.getLarguraBraco()));
        }
        if (medidas.getLarguraCoxa() > 0) {
            result.add(new Medida(10,"Largura da Coxa", medidas.getLarguraCoxa()));
        }
        if (medidas.getLarguraCostas() > 0) {
            result.add(new Medida(11,"Largura das Costas", medidas.getLarguraCostas()));
        }
        if (medidas.getAlturaFrente() > 0) {
            result.add(new Medida(12,"Altura da Frente", medidas.getAlturaFrente()));
        }
        if (medidas.getAlturaCostas() > 0) {
            result.add(new Medida(13,"Altura das Costas", medidas.getAlturaCostas()));
        }
        if (medidas.getAlturaBusto() > 0) {
            result.add(new Medida(14,"Altura do Busto", medidas.getAlturaBusto()));
        }
        if (medidas.getAlturaQuadris() > 0) {
            result.add(new Medida(15,"Altura dos Quadris", medidas.getAlturaQuadris()));
        }
        if (medidas.getAlturaGancho() > 0) {
            result.add(new Medida(16,"Altura do Gancho", medidas.getAlturaGancho()));
        }
        if (medidas.getAlturaJoelho() > 0) {
            result.add(new Medida(17,"Altura do Joelho", medidas.getAlturaJoelho()));
        }
        if (medidas.getComprimentoCalca() > 0) {
            result.add(new Medida(18,"Comprimento da Calca", medidas.getComprimentoCalca()));
        }
        if (medidas.getComprimentoBlusa() > 0) {
            result.add(new Medida(19,"Comprimento da Blusa", medidas.getComprimentoBlusa()));
        }
        if (medidas.getComprimentoSaia() > 0) {
            result.add(new Medida(20,"Comprimento da Saia", medidas.getComprimentoSaia()));
        }
        return result;
    }

}
