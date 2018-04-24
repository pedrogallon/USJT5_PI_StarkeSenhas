package br.usjt.starke.starkesenhas.model;

import java.io.Serializable;
import java.util.Date;

public class Senha implements Serializable {

    private int id;
    private Servico servico;
    private Subservico subservico;

    private String tipo;
    private String nome;
    private String status;
    private Date dataEntrada;
    private Date dataSaida;
    private Date estimativaFila;
    private Date estimativaAtendimento;


    public Subservico getSubservico() {
        return subservico;
    }

    public void setSubservico(Subservico subservico) {
        this.subservico = subservico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Date getEstimativaFila() {
        return estimativaFila;
    }

    public void setEstimativaFila(Date estimativaFila) {
        this.estimativaFila = estimativaFila;
    }

    public Date getEstimativaAtendimento() {
        return estimativaAtendimento;
    }

    public void setEstimativaAtendimento(Date estimativaAtendimento) {
        this.estimativaAtendimento = estimativaAtendimento;
    }

    public Senha(){}

    @Override
    public String toString() {
        return "Senha [id=" + id + ", servico=" + servico + ", subservico=" + subservico + ", tipo=" + tipo + ", nome="
                + nome + ", status=" + status + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida
                + ", estimativaFila=" + estimativaFila
                + ", estimativaAtendimento=" + estimativaAtendimento + "]";
    }


}


