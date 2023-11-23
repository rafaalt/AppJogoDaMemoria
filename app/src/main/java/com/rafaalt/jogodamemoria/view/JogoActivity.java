package com.rafaalt.jogodamemoria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rafaalt.jogodamemoria.R;
import com.rafaalt.jogodamemoria.model.FileManager;
import com.rafaalt.jogodamemoria.model.JogoDaMemoria;
import com.rafaalt.jogodamemoria.model.Recorde;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class JogoActivity extends AppCompatActivity {
    int cartasAbertas = 0;
    boolean livre = true;
    ImageView ultimaCell;
    JogoDaMemoria jogoDaMemoria;
    int recorde;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        GridLayout tabuleiro = (GridLayout) findViewById(R.id.gridLayout);
        Intent intent = getIntent();
        int tamanho = intent.getIntExtra("tamanho", 0);
        jogoDaMemoria = new JogoDaMemoria(tamanho);
        TextView txtRecorde = (TextView) findViewById(R.id.jogoTxtRecorde);
        setRecorde(tamanho);
        txtRecorde.setText("Recorde: " + recorde);
        tabuleiro.setRowCount(tamanho);
        tabuleiro.setColumnCount(tamanho);
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                int tag = jogoDaMemoria.getValorAleatorio(i*tamanho + j);
                //Log.d("JogoActivity", "Tag: " + tag + " | posicao" + (i*tamanho + j));
                tabuleiro.addView(createCell(tamanho, tag, i*tamanho + j));
            }
        }
        Button btnReinciar = (Button) findViewById(R.id.jogoBtnReiniciar);
        Button btnVoltar = (Button) findViewById(R.id.jogoBtnVoltar);
        btnReinciar.setOnClickListener(view -> {
            reiniciar(tamanho);
        });
        btnVoltar.setOnClickListener(view -> {
            this.finish();
        });
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
            jogar(cell, posicao, tamanho);
        });
        return cell;
    }
    public void jogar(ImageView cell, int posicao, int tamanhoTabuleiro) {
        if (livre) {
            cartasAbertas++;
            boolean ganhou = jogoDaMemoria.jogar(posicao);
            if (cartasAbertas == 2) {
                Handler handler = new Handler(Looper.getMainLooper());
                cell.setImageResource(jogoDaMemoria.getIdImagem((int) cell.getTag()));
                TextView txtJogadas = (TextView) findViewById(R.id.jogoTxtJogadas);
                txtJogadas.setText("Jogadas: " + jogoDaMemoria.getNumJogadas());
                livre = false;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (ganhou) {
                            ultimaCell.setVisibility(View.INVISIBLE);
                            cell.setVisibility(View.INVISIBLE);
                        } else {
                            ultimaCell.setImageResource(jogoDaMemoria.getIdImagem(0));
                            cell.setImageResource(jogoDaMemoria.getIdImagem(0));
                        }
                        cartasAbertas = 0;
                        livre = true;
                        if(jogoDaMemoria.verificaVencedor()) {
                            if(recorde == 0 || recorde > jogoDaMemoria.getNumJogadas()){
                                atualizarRecorde(tamanhoTabuleiro, jogoDaMemoria.getNumJogadas());
                                recorde = jogoDaMemoria.getNumJogadas();
                            }
                            reiniciar(tamanhoTabuleiro);
                        }
                    }

                }, 1000);
            } else {
                cell.setImageResource(jogoDaMemoria.getIdImagem((int) cell.getTag()));
                ultimaCell = cell;
            }
        }
    }

    public void reiniciar(int tamanho){
        //Toast.makeText(this, "Parabens! Voce venceu!", Toast.LENGTH_SHORT).show();
        GridLayout tabuleiro = (GridLayout) findViewById(R.id.gridLayout);
        jogoDaMemoria = new JogoDaMemoria(tamanho);
        tabuleiro.setRowCount(tamanho);
        tabuleiro.setColumnCount(tamanho);
        TextView txtRecorde = (TextView) findViewById(R.id.jogoTxtRecorde);
        txtRecorde.setText("Recorde: " + recorde);
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                ImageView cell = (ImageView) tabuleiro.getChildAt(i*tamanho + j);
                cell.setVisibility(View.VISIBLE);
                cell.setImageResource(jogoDaMemoria.getIdImagem(0));
                int tag = jogoDaMemoria.getValorAleatorio(i*tamanho + j);
                cell.setTag(tag);
            }
        }
    }
    public void setRecorde(int tamanho){
        int tam = tamanho*tamanho;
        // Exemplo de como carregar dados do arquivo
        ArrayList<Recorde> loadedData = FileManager.loadData(this);
        for (Recorde x : loadedData) {
            if(x.getTamanho() == tam) {
                this.recorde = x.getRecordeJogadas();
                break;
            }
        }
    }
    public void atualizarRecorde(int tamanho, int newRecorde){
        FileManager.saveData(this, new Recorde(tamanho*tamanho, newRecorde));
    }
}