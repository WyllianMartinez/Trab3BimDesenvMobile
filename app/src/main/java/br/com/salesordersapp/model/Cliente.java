package br.com.salesordersapp.model;

public class Cliente {

    private int id;
    private String nome;
    private String cpf;
    private String dtNascimento;

    public Cliente(){

    }

    public Cliente( String nome, String cpf, String dtNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }


    @Override
    public String toString() {
        return nome;
    }
}
