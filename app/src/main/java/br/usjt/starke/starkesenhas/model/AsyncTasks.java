package br.usjt.starke.starkesenhas.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.controller.GerarSenhaActivity;
import br.usjt.starke.starkesenhas.controller.ListarSenhaActivity;
import br.usjt.starke.starkesenhas.controller.MainActivity;
import br.usjt.starke.starkesenhas.controller.VisualizarSenhaGeradaActivity;

public class AsyncTasks {
    public static final String SERVICOS = "br.usjt.starke.starkesenhas.model.AsyncTasks.servicos";
    public static final String SENHA_GERADA = "br.usjt.starke.starkesenhas.model.AsyncTasks.senha_gerada";
    public static final String SENHAS = "br.usjt.starke.starkesenhas.model.AsyncTasks.senhas";

    public static class getServicosSenha extends AsyncTask<AsyncTaskParams, Void, ArrayList<Senha>> {
        Context context;
        private ProgressDialog dialog;

        public getServicosSenha(Context activity){
            dialog = new ProgressDialog(activity);
        }

        protected void onPreExecute() {
            dialog.setMessage("Carregando...");
            dialog.show();
        }

        @Override
        protected ArrayList<Senha> doInBackground(AsyncTaskParams... params) {
            ArrayList<Senha> servicos = new ArrayList<>();
            context = params[0].context;

            try {
                servicos = StarkeNetwork.getServicos(params[0].url);
            } catch (IOException e) {
                servicos = null;
                e.printStackTrace();
            }
            return servicos;
        }

        @Override
        protected void onPostExecute(ArrayList<Senha> servicos) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if(servicos == null){
                new AlertDialog.Builder(context)
                    .setTitle("SERVIDOR INDISPONIVEL")
                    .setMessage("Tente novamente mais tarde.")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    context.startActivity(new Intent(context, MainActivity.class));
                                }
                            })
                    .show();
            }else{
                ListView lista = (ListView)((Activity)context).findViewById(R.id.lista_servicos);
                ServicosAdapter adapter = new ServicosAdapter(context, servicos);
                lista.setAdapter(adapter);
            }
        }
    }

    public static class getServicos extends AsyncTask<AsyncTaskParams, Void, ArrayList<Servico>> {
        Context context;
        private ProgressDialog dialog;

        public getServicos(Context activity){
            dialog = new ProgressDialog(activity);
        }

        protected void onPreExecute() {
            dialog.setMessage("Carregando...");
            dialog.show();
        }

        @Override
        protected ArrayList<Servico> doInBackground(AsyncTaskParams... params) {
            ArrayList<Servico> servicos = new ArrayList<>();
            context = params[0].context;

            try {
                servicos = StarkeNetwork.listarServico(params[0].url);
            } catch (IOException e) {
                servicos = null;
                e.printStackTrace();
            }
            return servicos;
        }

        @Override
        protected void onPostExecute(ArrayList<Servico> servicos) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if(servicos == null){
                new AlertDialog.Builder(context)
                    .setTitle("SERVIDOR INDISPONIVEL")
                    .setMessage("Tente novamente mais tarde.")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    context.startActivity(new Intent(context, MainActivity.class));
                                }
                            })
                    .show();
            }else{
                Intent i = new Intent(context, GerarSenhaActivity.class);
                i.putExtra(SERVICOS, servicos);
                context.startActivity(i);
            }
        }

    }

    public static class createSenha extends AsyncTask<AsyncTaskParams, Void, Senha> {
        Context context;
        private ProgressDialog dialog;

        public createSenha(Context activity){
            dialog = new ProgressDialog(activity);
        }

        protected void onPreExecute() {
            dialog.setMessage("Carregando...");
            dialog.show();
        }

        @Override
        protected Senha doInBackground(AsyncTaskParams... params) {
            Senha senha = new Senha();
            context = params[0].context;
            try {
                senha = StarkeNetwork.criarSenha(params[0].url);

            } catch (IOException e) {
                senha = null;
                e.printStackTrace();
            }
            return senha;
        }

        @Override
        protected void onPostExecute(Senha senha) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if(senha == null){
                new AlertDialog.Builder(context)
                        .setTitle("SERVIDOR INDISPONIVEL")
                        .setMessage("Tente novamente mais tarde.")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        context.startActivity(new Intent(context, MainActivity.class));
                                    }
                                })
                        .show();
            }else {
                Intent intent = new Intent(context, VisualizarSenhaGeradaActivity.class);
                intent.putExtra(SENHA_GERADA, senha);
                context.startActivity(intent);
            }
        }
    }

    public static class getSenhas extends AsyncTask<AsyncTaskParams, Void, ArrayList<Senha>> {
        Context context;
        private ProgressDialog dialog;

        public getSenhas(Context activity){
            dialog = new ProgressDialog(activity);
        }

        protected void onPreExecute() {
            dialog.setMessage("Carregando...");
            dialog.show();
        }

        @Override
        protected ArrayList<Senha> doInBackground(AsyncTaskParams... params) {
            ArrayList<Senha> senhas = new ArrayList<>();
            context = params[0].context;

            try {
                senhas = StarkeNetwork.listarSenha(params[0].url);
            } catch (IOException e) {
                senhas = null;
                e.printStackTrace();
            }
            return senhas;
        }

        @Override
        protected void onPostExecute(ArrayList<Senha> senhas) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if(senhas == null){
                new AlertDialog.Builder(context)
                        .setTitle("SERVIDOR INDISPONIVEL")
                        .setMessage("Tente novamente mais tarde.")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        context.startActivity(new Intent(context, MainActivity.class));
                                    }
                                })
                        .show();
            }else {
                Intent i = new Intent(context, ListarSenhaActivity.class);
                i.putExtra(SENHAS, senhas);
                context.startActivity(i);
            }
        }

    }

    public static class getSenha extends AsyncTask<AsyncTaskParams, Void, Senha> {
        Context context;
        private ProgressDialog dialog;

        public getSenha(Context activity){
            dialog = new ProgressDialog(activity);
        }

        protected void onPreExecute() {
            dialog.setMessage("Carregando...");
            dialog.show();
        }

        @Override
        protected Senha doInBackground(AsyncTaskParams... params) {
            context = params[0].context;

            try {
                return StarkeNetwork.getSenha(params[0].url);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Senha senha) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if (senha != null) {
                Intent i = new Intent(context, VisualizarSenhaGeradaActivity.class);
                i.putExtra(SENHA_GERADA, senha);
                context.startActivity(i);
            } else {

                Toast.makeText(context, "Senha Inexistente!", Toast.LENGTH_LONG).show();

            }
        }

    }

}
