package br.usjt.starke.starkesenhas.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.model.AsyncTaskParams;
import br.usjt.starke.starkesenhas.model.AsyncTasks;
import static br.usjt.starke.starkesenhas.model.StarkeNetwork.ENDERECO_REST;

public class MainActivity extends AppCompatActivity {
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
                                                                                                                                                                                                                                                                    bottomNavigationView.getMenu().getItem(0).setChecked(true);
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
