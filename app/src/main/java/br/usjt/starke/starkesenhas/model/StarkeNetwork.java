package br.usjt.starke.starkesenhas.model;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StarkeNetwork {
    public static String ENDERECO_REST = "http://10.0.100.12:8080/starke_project/rest/";

    public static Senha criarSenha(String url) throws IOException {

        Senha senha = new Senha();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();


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

    public static Senha getSenha(String url) throws IOException {

        Senha senha = new Senha();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();


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
             Subservico subservico = new Subservico();
             JSONObject objSubservico = objSenha.getJSONObject("subservico");
             subservico.setId(objSubservico.getInt("id"));
             subservico.setNome(objSubservico.getString("nome"));
             subservico.setOrdem(objSubservico.getInt("ordem"));
             subservico.setServico(servico);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return senha;
    }

    public static ArrayList<Senha> getServicos(String url) throws IOException{
        OkHttpClient client = new OkHttpClient();
        ArrayList<Senha> senhas = new ArrayList<>();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();
        try {
            JSONArray x = (JSONArray) new JSONArray(json);
            x.length();
            for (int i = 0; i < new JSONArray(json).length(); i++) {
                JSONObject item = (JSONObject) x.get(i);
                Log.d("TESTE", item.getString("dataEntrada"));

                Senha senha = new Senha();
                JSONObject senhaObj = (JSONObject) item.getJSONObject("subservico");
                senha.setId(senhaObj.getInt("id"));
                senha.setNome(senhaObj.getString("nome"));
                if(item.getString("dataEntrada") != "null") {
                    senha.setDataEntrada(new Date(item.getLong("dataEntrada")));
                }
                if(item.getString("dataSaida") != "null") {
                    senha.setDataSaida(new Date(item.getLong("dataSaida")));
                }
                senhas.add(senha);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return senhas;
    }

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
/*
        [
        {
            "id": 5,
                "servico": {
                    "id": "CJ",
                    "nome": "Tirar CNPJ"
        },
            "subservico": {
                "id": 6,
                "servico": {
                    "id": "CJ",
                    "nome": "Tirar CNPJ"
                },
                "ordem": 3,
                "nome": "Pagar Taxa"
        },
            "tipo": "preferencial",
             "nome": "CJ003",
             "status": "aguardando",
             "dataEntrada": 1523998402000,
             "dataSaida": null,
             "estimativaFila": 1523998462000,
             "estimativaAtendimento": 1523998462000
        }]*/
        try {
            JSONArray lista = new JSONArray(json);
            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject) lista.get(i);
                Senha senha = new Senha();
                senha.setId(item.getInt("id"));
                senha.setNome(item.getString("nome"));
                senha.setStatus(item.getString("status"));
                senha.setTipo(item.getString("tipo"));
                try {
                    senha.setDataEntrada(new Date(item.getLong("dataEntrada")));
                } catch (Exception e) {
                    senha.setDataEntrada(null);
                }

                try {
                    senha.setDataSaida(new Date(item.getLong("dataSaida")));
                } catch (Exception e){
                    senha.setDataSaida(null);
                }

                try {
                senha.setEstimativaFila(new Date(item.getLong("estimativaFila")));
                } catch (Exception e){
                    senha.setEstimativaFila(null);
                }

                try{
                senha.setEstimativaAtendimento(new Date(item.getLong("estimativaAtendimento")));
                } catch (Exception e){
                    senha.setEstimativaAtendimento(null);
                }
                // Adcionando Servico
                JSONObject servicoJSON = item.getJSONObject("servico");
                Servico servico = new Servico();
                servico.setId(servicoJSON.getString("id"));
                servico.setNome(servicoJSON.getString("nome"));
                senha.setServico(servico);

                // Adcionando Subservico
                JSONObject subservicoJSON = item.getJSONObject("subservico");
                Subservico subservico = new Subservico();
                subservico.setId(subservicoJSON.getInt("id"));
                subservico.setNome(subservicoJSON.getString("nome"));
                subservico.setOrdem(subservicoJSON.getInt("ordem"));
                subservico.setServico(servico);
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
