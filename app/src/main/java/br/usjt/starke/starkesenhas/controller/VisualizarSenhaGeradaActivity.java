package br.usjt.starke.starkesenhas.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.model.Senha;

import static br.usjt.starke.starkesenhas.model.AsyncTasks.SENHA_GERADA;
import static br.usjt.starke.starkesenhas.model.Senha.DF_SENHA;

public class VisualizarSenhaGeradaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_senha_gerada);

        Intent i = getIntent();
        Senha senha = (Senha) i.getSerializableExtra(SENHA_GERADA);


        TextView senhaNome, senhaServico, senhaTipo, senhaFila, senhaFinal;
        senhaNome = findViewById(R.id.visualizar_senha_nome);
        senhaServico = findViewById(R.id.visualizar_senha_servico);
        senhaTipo = findViewById(R.id.visualizar_senha_tipo);
        senhaFila = findViewById(R.id.visualizar_senha_datagroup_fila);
        senhaFinal = findViewById(R.id.visualizar_senha_datagroup_final);

        senhaNome.setText(senha.getNome());
        senhaServico.setText(senha.getServico().getNome());
        senhaTipo.setText(senha.getTipo());
        senhaFila.setText(DF_SENHA.format(senha.getEstimativaFila()));
        senhaFinal.setText(DF_SENHA.format(senha.getEstimativaFila()));

    }
}
