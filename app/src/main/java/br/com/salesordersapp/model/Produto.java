package br.com.salesordersapp.model;

public class Produto {

    private int id;
    private String descricao;
    private double vlrUnitario;
    private String undMedida;

    public Produto(int id, String descricao, String undMedida, double vlrUnitario){

    }

    public Produto(){

    }

    public Produto(int id, String descricao, double vlrUnitario, String undMedida) {
        this.id = id;
        this.descricao = descricao;
        this.vlrUnitario = vlrUnitario;
        this.undMedida = undMedida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getVlrUnitario() {
        return vlrUnitario;
    }

    public void setVlrUnitario(double vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }

    public String getUndMedida() {
        return undMedida;
    }

    public void setUndMedida(String undMedida) {
        this.undMedida = undMedida;
    }
}
