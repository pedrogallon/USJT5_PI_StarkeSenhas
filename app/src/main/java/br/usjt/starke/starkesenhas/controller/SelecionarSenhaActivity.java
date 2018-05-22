package br.usjt.starke.starkesenhas.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.model.AsyncTaskParams;
import br.usjt.starke.starkesenhas.model.AsyncTasks;

import static br.usjt.starke.starkesenhas.model.StarkeNetwork.ENDERECO_REST;

public class SelecionarSenhaActivity extends Activity {
    public static String SENHA = "br.usjt.starke.starkesenhas.controller.ListarSenhaActivity.senha";
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_senha);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_selecionar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
        context = this;
    }

    public void getSenhaNome(View view){
        EditText txt = findViewById(R.id.selecionar_senha_nome);
        String nome = txt.getText().toString().toUpperCase();
        new AsyncTasks.getSenha(context).execute(new AsyncTaskParams(context, ENDERECO_REST+"/senha/"+nome+"/"));
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
