package br.usjt.starke.starkesenhas.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StarkeNetwork {
    public static Senha criarSenha(String url) throws IOException {



        Senha senha = new Senha();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();
        Log.d("DBG", json);

        try {
            JSONObject objSenha = new JSONObject(json);

            senha.setId(objSenha.getInt("id"));
            senha.setTipo(objSenha.getString("tipo"));
            senha.setNome(objSenha.getString("nome"));
            senha.setStatus(objSenha.getString("status"));

            long longFila = (objSenha.getLong("estimativaFila"));
            long longFinal = (objSenha.getLong("estimativaAtendimento"));
            senha.setEstimativaFila(new Date(longFila));
            senha.setEstimativaAtendimento(new Date(longFinal));

            Servico servico = new Servico();
            JSONObject objServico = objSenha.getJSONObject("servico");
            servico.setId(objServico.getString("id"));
            servico.setNome(objServico.getString("nome"));



            senha.setServico(servico);
            /** CASO PRECISE DO SUBSERVICO
            Subservico subservico = new Subservico();
            JSONObject objSubservico = objSenha.getJSONObject("subservico");
            subservico.setId(objSubservico.getInt("id"));
            subservico.setNome(objSubservico.getString("nome"));
            subservico.setOrdem(objSubservico.getInt("ordem"));
            subservico.setServico(servico);
             **/

        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        return senha;
    }



    /**
     *
     *
     *
     *
     {
     "id": 20,
     "servico": {
     "id": "CN",
     "nome": "Renovacao CNH"
     },
     "subservico": {
     "id": 7,
     "servico": {
     "id": "CN",
     "nome": "Renovacao CNH"
     },
     "ordem": 1,]
     "nome": "Entregar Documentos"
     },
     "tipo": "comum",
     "nome": "CN009",
     "status": "aguardando",
     "dataEntrada": 1524083191666,
     "dataSaida": null,
     "estimativaFila": 1524085111660,
     "estimativaAtendimento": 1524089251660
     }
     */
    public static ArrayList<Servico> listarServico(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ArrayList<Servico> servicos = new ArrayList<>();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        try {
            JSONArray x = (JSONArray) new JSONArray(json);
            x.length();
            for (int i = 0; i < new JSONArray(json).length(); i++) {
                JSONObject item = (JSONObject) x.get(i);
                Servico servico = new Servico();
                servico.setId(item.getString("id"));
                servico.setNome(item.getString("nome"));
                servicos.add(servico);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return servicos;

    }

    public static ArrayList<Senha> listarSenha(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        ArrayList<Senha> senhas = new ArrayList<>();

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        try {
            JSONArray lista = new JSONArray(json);
            // TODO: 18/04/18 Inserir todos os dados p Senha, Servico e Subservico
            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject) lista.get(i);
                Senha senha = new Senha();
                senha.setId(item.getInt("id"));

                String sDataAbertura = (item.getString("dataAbertura"));
                try {
                    senha.setDataEntrada(formatter.parse(sDataAbertura));
                } catch (ParseException e) {
                    senha.setDataEntrada(null);
                    e.printStackTrace();
                }

                // Adcionando Servico
                JSONObject servicoJSON = item.getJSONObject("servico");
                Servico servico = new Servico();
                servico.setId(servicoJSON.getString("id"));
                senha.setServico(servico);

                // Adcionando Subservico
                JSONObject subservicoJSON = item.getJSONObject("subservico");
                Subservico subservico = new Subservico();
                subservico.setId(subservicoJSON.getInt("id"));
                senha.setSubservico(subservico);


                senhas.add(senha);

            }

        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }


        return senhas;
    }
}
