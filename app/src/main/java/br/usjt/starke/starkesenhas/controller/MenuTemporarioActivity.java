package br.usjt.starke.starkesenhas.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.model.AsyncTasks;
import br.usjt.starke.starkesenhas.model.AsyncTaskParams;

import static br.usjt.starke.starkesenhas.model.StarkeNetwork.ENDERECO_REST;

public class MenuTemporarioActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_temporario);
    }

    public void activityGerarSenha(View view) {
        AsyncTaskParams params = new AsyncTaskParams(this, ENDERECO_REST + "servicos/");
        new AsyncTasks.getServicos().execute(params);
    }

    public void activityListarSenha(View view) {
        AsyncTaskParams params = new AsyncTaskParams(this, ENDERECO_REST + "senhas/");
        new AsyncTasks.getSenhas().execute(params);
    }

    public void activityVisualizarSenha(View view) {
        this.startActivity(new Intent(this, SelecionarSenhaActivity.class));
    }


}
