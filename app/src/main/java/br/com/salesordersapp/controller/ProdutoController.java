package br.com.salesordersapp.controller;

import android.content.Context;
import java.util.ArrayList;
import br.com.salesordersapp.dao.ProdutoDAO;
import br.com.salesordersapp.model.Produto;

public class ProdutoController {
    private ProdutoDAO produtoDAO;
    private Context context;
    private static ProdutoController instancia;

    private ProdutoController(Context context) {
        produtoDAO = ProdutoDAO.getInstance(context);
    }

    public static ProdutoController getInstancia(Context context) {
        if (instancia == null) {
            instancia = new ProdutoController(context);
        }
        return instancia;
    }

    public long inserirProduto(Produto produto) {
        return produtoDAO.insert(produto);
    }

    public long atualizarProduto(Produto produto) {
        return produtoDAO.update(produto);
    }

    public long deletarProduto(Produto produto) {
        return produtoDAO.delete(produto);
    }

    public ArrayList<Produto> obterTodosProdutos() {
        return ProdutoDAO.getInstance(context).getAll();
    }

    public Produto obterProdutoPorId(int id) {
        return produtoDAO.getById(id);
    }
}
