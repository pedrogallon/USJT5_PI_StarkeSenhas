package br.usjt.starke.starkesenhas.controller;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.model.AsyncTaskParams;
import br.usjt.starke.starkesenhas.model.Senha;
import br.usjt.starke.starkesenhas.model.SenhaAdapter;
import br.usjt.starke.starkesenhas.model.StarkeNetwork;

import static br.usjt.starke.starkesenhas.model.AsyncTasks.SENHAS;
import static br.usjt.starke.starkesenhas.model.StarkeNetwork.ENDERECO_REST;

public class ListarSenhaActivity extends Activity {
    ArrayList<Senha> senhas;
    Context context= this;
    ListView lista;
    Timer timer = new Timer();
    TimerTask reloadTask = new TimerTask() {
        @Override
        public void run() {
            reload();
        }
    };

    private void reload(){
        AsyncTaskParams params = new AsyncTaskParams(context, ENDERECO_REST + "senhas/");
        new getSenhasReload().execute(params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_senha);
        senhas = (ArrayList<Senha>) getIntent().getSerializableExtra(SENHAS);
        lista = findViewById(R.id.listar_senha_lista);
        SenhaAdapter adapter = new SenhaAdapter(this, senhas);
        lista.setAdapter(adapter);

        timer.schedule(reloadTask, 10000);

    }

    private class getSenhasReload extends AsyncTask<AsyncTaskParams, Void, ArrayList<Senha>> {
        Context context;

        @Override
        protected ArrayList<Senha> doInBackground(AsyncTaskParams... params) {
            ArrayList<Senha> senhas = new ArrayList<>();
            context = params[0].getContext();

            try {
                senhas = StarkeNetwork.listarSenha(params[0].getUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return senhas;
        }
        @Override
        protected void onPostExecute(ArrayList<Senha> senhas) {
            SenhaAdapter adapter = new SenhaAdapter(context, senhas);
            lista.setAdapter(adapter);
            timer.cancel();
            timer.purge();
            timer = new Timer();
            reloadTask = new TimerTask() {
                @Override
                public void run() {
                    reload();
                }
            };
            timer.schedule(reloadTask, 10000);

        }

    }
}
