package com.rafaalt.jogodamemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class JogoActivity extends AppCompatActivity {
    int[] imagens;
    ArrayList<Integer> arrayRandom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        Random random = new Random();
        GridLayout tabuleiro = (GridLayout) findViewById(R.id.gridLayout);
        int tamanho = 4;
        arrayRandom = new ArrayList<>();
        for(int i = 0;i<tamanho*2;i++){
            arrayRandom.add(i);
            arrayRandom.add(i);
        }
        inicializarImagens();
        tabuleiro.setRowCount(tamanho);
        tabuleiro.setColumnCount(tamanho);
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                int valor = random.nextInt(arrayRandom.size());
                int tag = arrayRandom.remove(valor);
                tabuleiro.addView(createCell(tamanho, tag));
            }
        }

    }
    public ImageView createCell(int tamanho, int tag){
        ImageView cell = new ImageView(this);
        int tam = 300/tamanho;

        float density = this.getResources().getDisplayMetrics().density;
        int maxWidthInPixels = (int) (tam * density + 0.5f);
        int maxHeightInPixels = (int) (tam * density + 0.5f);
        Log.d("CHEGOU", tag+"");
        cell.setImageResource(this.imagens[tag+1]);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(maxWidthInPixels, maxHeightInPixels);
        cell.setLayoutParams(params);
        cell.setTag(tag);
        cell.setOnClickListener(view -> {
            jogar(cell);
        });
        return cell;
    }
    public void jogar(ImageView cell){

    }
    public void inicializarImagens(){
        this.imagens = new int[10];
        imagens[0] = R.drawable.time0;
        imagens[1] = R.drawable.time1;
        imagens[2] = R.drawable.time2;
        imagens[3] = R.drawable.time3;
        imagens[4] = R.drawable.time4;
        imagens[5] = R.drawable.time5;
        imagens[6] = R.drawable.time6;
        imagens[7] = R.drawable.time7;
        imagens[8] = R.drawable.time8;
    }
}