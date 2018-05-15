package br.usjt.starke.starkesenhas.model;

import android.widget.TextView;

public class SenhaViewHolder {
    private TextView nome, estimativa, status;

    public TextView getNome() {
        return nome;
    }

    public void setNome(TextView nome) {
        this.nome = nome;
    }

    public TextView getEstimativa() {
        return estimativa;
    }

    public void setEstimativa(TextView estimativa) {
        this.estimativa = estimativa;
    }

    public TextView getStatus() {
        return status;
    }

    public void setStatus(TextView status) {
        this.status = status;
    }
}
