package com.example.ajudantedecostura.home.pedido;

import android.annotation.SuppressLint;

import java.util.ArrayList;

import modelDominio.Medida;
import modelDominio.Medidas;

public class TransformaEmMedidas {

    @SuppressLint("NotConstructor")
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

}
