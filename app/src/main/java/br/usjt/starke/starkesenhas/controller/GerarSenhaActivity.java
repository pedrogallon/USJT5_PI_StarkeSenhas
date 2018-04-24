package br.usjt.starke.starkesenhas.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.model.Senha;
import br.usjt.starke.starkesenhas.model.Servico;
import br.usjt.starke.starkesenhas.model.StarkeNetwork;

import static br.usjt.starke.starkesenhas.controller.SplashscreenActivity.SERVICOS;

public class GerarSenhaActivity extends Activity {
    public static final String SENHA_GERADA = "br.usjt.starke.starkesenhas.controller.GerarSenhaActivity.senha";
    Context context;
    View view;
    ArrayList<Servico> listaServicos = new ArrayList<>();
    Spinner spinnerTipo;
    Spinner spinnerServico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_senha);
        context = this;

        Intent intent = getIntent();
        listaServicos = (ArrayList<Servico>) intent.getSerializableExtra(SERVICOS);
        ArrayList<String> nomesServicos = new ArrayList<>();

        for (Servico s : listaServicos){
            nomesServicos.add(s.getNome());
        }

        spinnerTipo = findViewById(R.id.gerar_senha_spinner_tipo);
        String[] tipos = getResources().getStringArray(R.array.gerar_senha_tipo_spinner);
        ArrayAdapter<String> adtTipo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tipos);
        spinnerTipo.setAdapter(adtTipo);

        spinnerServico = findViewById(R.id.gerar_senha_spinner_servico);
        ArrayAdapter<String> adptServico = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nomesServicos);
        spinnerServico.setAdapter(adptServico);


    }

    public void criarSenha(View view) {


        String tipo = spinnerTipo.getSelectedItem().toString().toLowerCase();
        int pos = spinnerServico.getSelectedItemPosition();
        String idServico = listaServicos.get(pos).getId();

        new GerarSenhaActivity.CreateJsonSenha().execute("http://192.168.15.110:8081/starke_project/rest/criar_senha/"+tipo+"/"+idServico+"/");

    }

    private class CreateJsonSenha extends AsyncTask<String, Void, Senha> {

        @Override
        protected Senha doInBackground(String... strings) {
            Senha senha = new Senha();
            try {
                senha = StarkeNetwork.criarSenha(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return senha;
        }

        protected void onPostExecute(Senha senha){

            Intent intent = new Intent(context, VisualizarSenhaGeradaActivity.class);
            intent.putExtra(SENHA_GERADA, senha);

            startActivity(intent);
        }

    }
}
