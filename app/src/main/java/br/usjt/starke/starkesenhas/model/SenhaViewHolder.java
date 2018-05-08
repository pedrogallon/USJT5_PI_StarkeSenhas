package br.usjt.starke.starkesenhas.model;

import android.widget.TextView;

public class SenhaViewHolder {
    private TextView nome, estimativa1, estimativa2;

    public TextView getNome() {
        return nome;
    }

    public void setNome(TextView nome) {
        this.nome = nome;
    }

    public TextView getEstimativa1() {
        return estimativa1;
    }

    public void setEstimativa1(TextView estimativa1) {
        this.estimativa1 = estimativa1;
    }

    public TextView getEstimativa2() {
        return estimativa2;
    }

    public void setEstimativa2(TextView estimativa2) {
        this.estimativa2 = estimativa2;
    }
}
