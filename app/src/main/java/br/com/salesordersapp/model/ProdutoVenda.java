package br.com.salesordersapp.model;

public class ProdutoVenda {
    private int id;
    private int id_pedido;
    private int id_item;
    private int quantidade;
    private double vlrUnitario;

    public ProdutoVenda() {
    }

    public ProdutoVenda(int id, int id_pedido, int id_item, int quantidade, double vlrUnitario) {
        this.id = id;
        this.id_pedido = id_pedido;
        this.id_item = id_item;
        this.quantidade = quantidade;
        this.vlrUnitario = vlrUnitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_venda) {
        this.id_pedido = id_venda;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getVlrUnitario() {
        return vlrUnitario;
    }

    public void setVlrUnitario(double vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }
}
