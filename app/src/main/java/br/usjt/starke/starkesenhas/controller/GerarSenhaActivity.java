package br.usjt.starke.starkesenhas.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.model.AsyncTaskParams;
import br.usjt.starke.starkesenhas.model.AsyncTasks;
import br.usjt.starke.starkesenhas.model.Servico;

import static br.usjt.starke.starkesenhas.model.AsyncTasks.SERVICOS;
import static br.usjt.starke.starkesenhas.model.StarkeNetwork.ENDERECO_REST;

public class GerarSenhaActivity extends Activity {
    Context context;
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

        for (Servico s : listaServicos) {
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

        new AsyncTasks.createSenha().execute(
                new AsyncTaskParams(this,
                        ENDERECO_REST + "criar_senha/" + tipo + "/" + idServico + "/"));
    }

}
