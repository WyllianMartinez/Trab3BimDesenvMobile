package br.com.salesordersapp.model;

public class Pedido {

    private int id;
    private int id_Cliente;
    private String produtos;
    private double vlrTotal;
    private String condPgto;
    private int qtdParcelas;
    private double vlrParcelas;
    private int id_EnderecoEntrega;

    public Pedido(){

    }

    public Pedido(int id, int id_Cliente, String produtos, double vlrTotal, String condPgto, int qtdParcelas, double vlrParcelas, int id_EnderecoEntrega) {
        this.id = id;
        this.id_Cliente = id_Cliente;
        this.produtos = produtos;
        this.vlrTotal = vlrTotal;
        this.condPgto = condPgto;
        this.qtdParcelas = qtdParcelas;
        this.vlrParcelas = vlrParcelas;
        this.id_EnderecoEntrega = id_EnderecoEntrega;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

    public double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public String getCondPgto() {
        return condPgto;
    }

    public void setCondPgto(String condPgto) {
        this.condPgto = condPgto;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public double getVlrParcelas() {
        return vlrParcelas;
    }

    public void setVlrParcelas(double vlrParcelas) {
        this.vlrParcelas = vlrParcelas;
    }

    public int getId_EnderecoEntrega() {
        return id_EnderecoEntrega;
    }

    public void setId_EnderecoEntrega(int id_EnderecoEntrega) {
        this.id_EnderecoEntrega = id_EnderecoEntrega;
    }
}
