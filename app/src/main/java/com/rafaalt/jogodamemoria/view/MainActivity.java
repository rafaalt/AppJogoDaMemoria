package com.rafaalt.jogodamemoria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.rafaalt.jogodamemoria.R;

public class MainActivity extends AppCompatActivity {
    public int tamanhoTabuleiro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tamanhoTabuleiro = 4;
        Button btnJogar = (Button) findViewById(R.id.mainBtnJogar);
        btnJogar.setOnClickListener(view -> {
            Intent intent = new Intent(this, JogoActivity.class);
            intent.putExtra("tamanho", tamanhoTabuleiro);
            startActivity(intent);
        });
    }
    public void onClickedTamanho(View view){
        RadioButton btn4x4 = (RadioButton) findViewById(R.id.radioBtn4x4);
        RadioButton btn6x6 = (RadioButton) findViewById(R.id.radioBtn6x6);
        if(btn4x4.isChecked()) {
            this.tamanhoTabuleiro = 4;
            //Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
        }
        else if(btn6x6.isChecked()){
            this.tamanhoTabuleiro = 6;
            //Toast.makeText(this, "6", Toast.LENGTH_SHORT).show();
        }
    }
}