package br.usjt.starke.starkesenhas.model;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.starke.starkesenhas.controller.GerarSenhaActivity;
import br.usjt.starke.starkesenhas.controller.ListarSenhaActivity;
import br.usjt.starke.starkesenhas.controller.MenuTemporarioActivity;
import br.usjt.starke.starkesenhas.controller.VisualizarSenhaGeradaActivity;

public class AsyncTasks {
    public static final String SERVICOS = "br.usjt.starke.starkesenhas.model.AsyncTasks.servicos";
    public static final String SENHA_GERADA = "br.usjt.starke.starkesenhas.model.AsyncTasks.senha_gerada";
    public static final String SENHAS = "br.usjt.starke.starkesenhas.model.AsyncTasks.senhas";

    public static class getServicos extends AsyncTask<AsyncTaskParams, Void, ArrayList<Servico>> {
        private Context context;

        @Override
        protected ArrayList<Servico> doInBackground(AsyncTaskParams... params) {
            ArrayList<Servico> servicos = new ArrayList<>();
            this.context = params[0].context;

            try {
                servicos = StarkeNetwork.listarServico(params[0].url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return servicos;
        }
        @Override
        protected void onPostExecute(ArrayList<Servico> servicos) {

            Intent i = new Intent(context, GerarSenhaActivity.class);
            i.putExtra(SERVICOS, servicos);
            context.startActivity(i);
        }

    }

    public static class createSenha extends AsyncTask<AsyncTaskParams, Void, Senha> {
        private Context context;

        @Override
        protected Senha doInBackground(AsyncTaskParams... params) {
            Senha senha = new Senha();
            this.context = params[0].context;
            try {
                senha = StarkeNetwork.criarSenha(params[0].url);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return senha;
        }

        @Override
        protected void onPostExecute(Senha senha) {
            Intent intent = new Intent(context, VisualizarSenhaGeradaActivity.class);
            intent.putExtra(SENHA_GERADA, senha);
            context.startActivity(intent);
        }

    }

    public static class getSenhas extends AsyncTask<AsyncTaskParams, Void, ArrayList<Senha>> {
        private Context context;

        @Override
        protected ArrayList<Senha> doInBackground(AsyncTaskParams... params) {
            ArrayList<Senha> senhas = new ArrayList<>();
            this.context = params[0].context;

            try {
                senhas = StarkeNetwork.listarSenha(params[0].url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return senhas;
        }
        @Override
        protected void onPostExecute(ArrayList<Senha> senhas) {

            Intent i = new Intent(context, ListarSenhaActivity.class);
            i.putExtra(SENHAS, senhas);
            context.startActivity(i);
        }

    }

}
