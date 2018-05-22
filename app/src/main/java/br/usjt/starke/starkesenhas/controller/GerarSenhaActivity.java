package br.usjt.starke.starkesenhas.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.model.AsyncTaskParams;
import br.usjt.starke.starkesenhas.model.AsyncTasks;
import br.usjt.starke.starkesenhas.model.Servico;

import static br.usjt.starke.starkesenhas.model.AsyncTasks.SERVICOS;
import static br.usjt.starke.starkesenhas.model.StarkeNetwork.ENDERECO_REST;

public class GerarSenhaActivity extends AppCompatActivity {
    Context context;
    ArrayList<Servico> listaServicos = new ArrayList<>();
    Spinner spinnerTipo;
    Spinner spinnerServico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_senha);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_gerar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        context = this;

        Intent intent = getIntent();
        listaServicos = (ArrayList<Servico>) intent.getSerializableExtra(SERVICOS);
        ArrayList<String> nomesServicos = new ArrayList<>();

        for (Servico s : listaServicos) {
            nomesServicos.add(s.getNome());
        }

        spinnerTipo = findViewById(R.id.gerar_senha_spinner_tipo);
        String[] tipos = getResources().getStringArray(R.array.gerar_senha_tipo_spinner);;
        ArrayAdapter<String> adtTipo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tipos);
        spinnerTipo.setAdapter(adtTipo);

        spinnerServico = findViewById(R.id.gerar_senha_spinner_servico);
        ArrayAdapter<String> adptServico = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nomesServicos);
        spinnerServico.setAdapter(adptServico);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_criar:
                    activityGerarSenha();
                    return true;
                case R.id.action_listar:
                    activityListarSenha();
                    return true;
                case R.id.action_consultar:
                    activityVisualizarSenha();
                    return true;
            }
            return true;
        }
    };

    public void activityGerarSenha() {
        AsyncTaskParams params = new AsyncTaskParams(context, ENDERECO_REST + "servicos/");
        new AsyncTasks.getServicos(context).execute(params);
    }

    public void activityListarSenha() {
        AsyncTaskParams params = new AsyncTaskParams(context, ENDERECO_REST + "senhas/");
        new AsyncTasks.getSenhas(context).execute(params);
    }

    public void activityVisualizarSenha() {
        this.startActivity(new Intent(this, SelecionarSenhaActivity.class));
    }

    public void criarSenha(View view) {

        String tipo = spinnerTipo.getSelectedItem().toString().toLowerCase();
        int pos = spinnerServico.getSelectedItemPosition();
        String idServico = listaServicos.get(pos).getId();

        new AsyncTasks.createSenha(context).execute(
                new AsyncTaskParams(context,
                        ENDERECO_REST + "criar_senha/" + tipo + "/" + idServico + "/"));
    }

}
