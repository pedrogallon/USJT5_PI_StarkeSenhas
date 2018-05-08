package br.usjt.starke.starkesenhas.controller;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.model.Senha;
import br.usjt.starke.starkesenhas.model.SenhaAdapter;

import static br.usjt.starke.starkesenhas.model.AsyncTasks.SENHAS;

public class ListarSenhaActivity extends Activity {
    ArrayList<Senha> senhas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_senha);
        senhas = (ArrayList<Senha>) getIntent().getSerializableExtra(SENHAS);
        ListView lista = findViewById(R.id.listar_senha_lista);
        SenhaAdapter adapter = new SenhaAdapter(this, senhas);
        lista.setAdapter(adapter);



    }
}
