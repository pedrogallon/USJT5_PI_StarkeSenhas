package br.usjt.starke.starkesenhas.model;

public class Subservico {


    private int id;
    private Servico servico;
    private int ordem;
    private String nome;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    @Override
    public String toString() {
        return "Subservico [id=" + id + ", servico=" + servico + ", ordem=" + ordem + ", nome=" + nome + "]";
    }

}
