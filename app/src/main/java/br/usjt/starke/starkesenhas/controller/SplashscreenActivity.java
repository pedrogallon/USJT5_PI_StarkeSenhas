package br.usjt.starke.starkesenhas.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import java.io.IOException;
import java.util.ArrayList;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.model.Servico;
import br.usjt.starke.starkesenhas.model.StarkeNetwork;

public class SplashscreenActivity extends Activity {
    Context context;
    public static final String SERVICOS = "br.usjt.starke.starkesenhas.controller.SplashscreenActivity.servicos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        context = this;
        new SplashscreenActivity.GetJsonServicos().execute("http://192.168.15.110:8081/" +
                "starke_project/rest/servicos/");

    }

    private class GetJsonServicos extends AsyncTask<String, Void, ArrayList<Servico>> {

        @Override
        protected ArrayList<Servico> doInBackground(String... strings) {
            ArrayList<Servico> servicos = new ArrayList<>();
            try {
                servicos = StarkeNetwork.listarServico(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return servicos;
        }

        protected void onPostExecute(ArrayList<Servico> servicos){

            Intent i = new Intent(context, GerarSenhaActivity.class);
            i.putExtra(SERVICOS, servicos);
            startActivity(i);
        }

    }
}
