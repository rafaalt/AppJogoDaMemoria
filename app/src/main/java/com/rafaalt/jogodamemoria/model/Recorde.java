package com.rafaalt.jogodamemoria.model;

public class Recorde {
    private int tamanho;
    private int recordeJogadas;

    public Recorde(int tamanho, int recordeJogadas) {
        this.tamanho = tamanho;
        this.recordeJogadas = recordeJogadas;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getRecordeJogadas() {
        return recordeJogadas;
    }

    public void setRecordeJogadas(int recordeJogadas) {
        this.recordeJogadas = recordeJogadas;
    }
}
