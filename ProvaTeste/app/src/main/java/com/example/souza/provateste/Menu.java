package com.example.souza.provateste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.souza.provateste.AdicionarActivity;
import com.example.souza.provateste.ListarActivity;
import com.example.souza.provateste.R;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void adicionar(View view) {

        Intent i = new Intent(this, AdicionarActivity.class);
        startActivity(i);

    }

    public void listar(View view){
        startActivity(new Intent(this,ListarActivity.class));
    }

    public void sair(View view){
        finish();
    }
    public void pesquisar(View view) {
        Intent i = new Intent(this, PesquisarActivity.class);
        startActivity(i);
    }
}
