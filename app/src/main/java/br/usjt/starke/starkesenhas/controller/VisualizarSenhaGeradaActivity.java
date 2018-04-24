package br.usjt.starke.starkesenhas.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.usjt.starke.starkesenhas.R;
import br.usjt.starke.starkesenhas.model.Senha;

import static br.usjt.starke.starkesenhas.controller.GerarSenhaActivity.SENHA_GERADA;

public class VisualizarSenhaGeradaActivity extends Activity {
    private final DateFormat df = new SimpleDateFormat("HH:mm");

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
        senhaFila.setText(df.format(senha.getEstimativaFila()));
        senhaFinal.setText(df.format(senha.getEstimativaFila()));

    }
}
