package com.rafaalt.jogodamemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnJogar = (Button) findViewById(R.id.mainBtnJogar);
        btnJogar.setOnClickListener(view -> {
            Intent intent = new Intent(this, JogoActivity.class);
            startActivity(intent);
        });
    }
}