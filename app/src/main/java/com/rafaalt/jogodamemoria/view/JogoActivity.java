package com.rafaalt.jogodamemoria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rafaalt.jogodamemoria.R;
import com.rafaalt.jogodamemoria.model.JogoDaMemoria;

import java.util.ArrayList;
import java.util.Random;

public class JogoActivity extends AppCompatActivity {
    int cartasAbertas = 0;
    ImageView ultimaCell;
    JogoDaMemoria jogoDaMemoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        GridLayout tabuleiro = (GridLayout) findViewById(R.id.gridLayout);
        int tamanho = 4;
        jogoDaMemoria = new JogoDaMemoria(tamanho);
        tabuleiro.setRowCount(tamanho);
        tabuleiro.setColumnCount(tamanho);
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                int tag = jogoDaMemoria.getValorAleatorio(i*tamanho + j);
                Log.d("AAA", "Tag: " + tag + " | posicao" + (i*tamanho + j));
                tabuleiro.addView(createCell(tamanho, tag, i*tamanho + j));
            }
        }
    }
    public ImageView createCell(int tamanho, int tag, int posicao){
        ImageView cell = new ImageView(this);
        int tam = 300/tamanho;

        float density = this.getResources().getDisplayMetrics().density;
        int maxWidthInPixels = (int) (tam * density + 0.5f);
        int maxHeightInPixels = (int) (tam * density + 0.5f);
        cell.setImageResource(jogoDaMemoria.getIdImagem(0));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(maxWidthInPixels, maxHeightInPixels);
        cell.setLayoutParams(params);
        cell.setTag(tag);
        cell.setOnClickListener(view -> {
            jogar(cell, posicao);
        });
        return cell;
    }
    public void jogar(ImageView cell, int posicao){
        cartasAbertas++;
        boolean ganhou = jogoDaMemoria.jogar(posicao);
        if(cartasAbertas == 2){
            Handler handler = new Handler(Looper.getMainLooper());
            cell.setImageResource(jogoDaMemoria.getIdImagem((int) cell.getTag()));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(ganhou){
                        ultimaCell.setImageResource(jogoDaMemoria.getIdImagem(9));
                        cell.setImageResource(jogoDaMemoria.getIdImagem(9));
                    }
                    else{
                        ultimaCell.setImageResource(jogoDaMemoria.getIdImagem(0));
                        cell.setImageResource(jogoDaMemoria.getIdImagem(0));
                    }
                    cartasAbertas = 0;
                }
            }, 1000);
        }
        else{
            cell.setImageResource(jogoDaMemoria.getIdImagem((int) cell.getTag()));
            ultimaCell = cell;
        }
    }
}