package br.usjt.starke.starkesenhas.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
        context = this;
    }

    public void getSenhaNome(View view){
        EditText txt = findViewById(R.id.selecionar_senha_nome);
        String nome = txt.getText().toString();
        new AsyncTasks.getSenha().execute(new AsyncTaskParams(context, ENDERECO_REST+"/senha/"+nome+"/"));
    }
}
