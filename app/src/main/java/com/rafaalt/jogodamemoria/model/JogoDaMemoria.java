package com.rafaalt.jogodamemoria.model;

import com.rafaalt.jogodamemoria.R;

import java.util.ArrayList;
import java.util.Random;

public class JogoDaMemoria {
    private int[] imagens;
    private int[] grid;
    private ArrayList<Integer> arrayRandom;
    private Random random;

    private int cartasAbertas;
    private int ultimaPosicao;
    public JogoDaMemoria(int tamanho) {
        inicializarImagens(tamanho);
        this.grid = new int[tamanho*tamanho];
        this.random = new Random();
        this.arrayRandom = new ArrayList<>();
        gerarArrayAleatorio(tamanho);
        this.cartasAbertas = 0;
        this.ultimaPosicao = 0;
    }

    private void inicializarImagens(int tamanho){
        this.imagens = new int[20];
        imagens[0] = R.drawable.time0;
        imagens[1] = R.drawable.time1;
        imagens[2] = R.drawable.time2;
        imagens[3] = R.drawable.time3;
        imagens[4] = R.drawable.time4;
        imagens[5] = R.drawable.time5;
        imagens[6] = R.drawable.time6;
        imagens[7] = R.drawable.time7;
        imagens[8] = R.drawable.time8;
        imagens[9] = R.drawable.time9;
        imagens[10] = R.drawable.time10;
        imagens[11] = R.drawable.time11;
        imagens[12] = R.drawable.time12;
        imagens[13] = R.drawable.time13;
        imagens[14] = R.drawable.time14;
        imagens[15] = R.drawable.time15;
        imagens[16] = R.drawable.time16;
        imagens[17] = R.drawable.time17;
        imagens[18] = R.drawable.time18;
    }
    public boolean jogar(int posicao){
        if(this.cartasAbertas == 0){//Vai abrir a primeira carta
            this.ultimaPosicao = grid[posicao];
            cartasAbertas++;
            return false;
        }
        else if(this.cartasAbertas == 1){
            if(this.ultimaPosicao == grid[posicao]){//Acertou!
                grid[posicao] = 0;
                grid[ultimaPosicao] = 0;
                cartasAbertas = 0;
                return true;
            }
            return false;
        }
        return false;
    }
    public int getUltimaPosicao(){
        return this.ultimaPosicao;
    }
    public void gerarArrayAleatorio(int tamanho){
        for(int i = 1;i<=tamanho*tamanho/2;i++){
            this.arrayRandom.add(i);
            this.arrayRandom.add(i);
        }
    }

    public int getValorAleatorio(int posicao){
        int valor = random.nextInt(arrayRandom.size());
        int tag = arrayRandom.remove(valor);
        grid[posicao] = tag;
        return tag;
    }

    public int getIdImagem(int tag){
        return this.imagens[tag];
    }
}
