package br.usjt.starke.starkesenhas.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.model.AsyncTaskParams;
import br.usjt.starke.starkesenhas.model.AsyncTasks;
import br.usjt.starke.starkesenhas.model.Senha;

import static br.usjt.starke.starkesenhas.model.AsyncTasks.SENHA_GERADA;
import static br.usjt.starke.starkesenhas.model.Senha.DF_SENHA;
import static br.usjt.starke.starkesenhas.model.StarkeNetwork.ENDERECO_REST;

public class VisualizarSenhaGeradaActivity extends Activity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_senha_gerada);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_selecionar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        Intent i = getIntent();

        Senha senha = (Senha) i.getSerializableExtra(SENHA_GERADA);

        TextView senhaNome, senhaServico, senhaTipo, senhaFila, senhaFinal;
        senhaNome = findViewById(R.id.visualizar_senha_nome);
        senhaServico = findViewById(R.id.visualizar_senha_servico);
        senhaTipo = findViewById(R.id.visualizar_senha_tipo);
        senhaFila = findViewById(R.id.visualizar_senha_datagroup_fila);
        senhaFinal = findViewById(R.id.visualizar_senha_datagroup_final);
        new AsyncTasks.getServicosSenha(context).execute(
                new AsyncTaskParams(context, ENDERECO_REST+"/servicos/"+ senha.getNome() +"/"));

        senhaNome.setText(senha.getNome());
        senhaServico.setText(senha.getServico().getNome());
        senhaTipo.setText(senha.getTipo());
        senhaFila.setText(DF_SENHA.format(senha.getEstimativaFila()));
        senhaFinal.setText(DF_SENHA.format(senha.getEstimativaAtendimento()));

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
        AsyncTaskParams params = new AsyncTaskParams(this, ENDERECO_REST + "servicos/");
        new AsyncTasks.getServicos(context).execute(params);
    }

    public void activityListarSenha() {
        AsyncTaskParams params = new AsyncTaskParams(this, ENDERECO_REST + "senhas/");
        new AsyncTasks.getSenhas(context).execute(params);
    }

    public void activityVisualizarSenha() {
        this.startActivity(new Intent(this, SelecionarSenhaActivity.class));
    }
}
