package br.usjt.starke.starkesenhas.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.model.AsyncTaskParams;
import br.usjt.starke.starkesenhas.model.AsyncTasks;

import static br.usjt.starke.starkesenhas.model.StarkeNetwork.ENDERECO_REST;

public class SplashscreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new AsyncTasks.getServicos().execute(
                new AsyncTaskParams(this, ENDERECO_REST +
                        "servicos/"));

    }
}
