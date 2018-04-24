package br.usjt.starke.starkesenhas.model;

import java.io.Serializable;

public class Servico implements Serializable {

    private String id;
    private String nome;

    public Servico(){};

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
