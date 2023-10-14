package br.com.salesordersapp.controller;

import android.content.Context;

import java.util.ArrayList;
import br.com.salesordersapp.dao.PedidoDAO;
import br.com.salesordersapp.model.Pedido;

public class PedidoController {
    private PedidoDAO pedidoDAO;
    private static PedidoController instancia;

    private PedidoController(Context context) {
        pedidoDAO = PedidoDAO.getInstance(context);
    }

    public static PedidoController getInstancia(Context context) {
        if (instancia == null) {
            instancia = new PedidoController(context);
        }
        return instancia;
    }

    public long inserirPedido(Pedido pedido) {
        return pedidoDAO.insert(pedido);
    }

    public long atualizarPedido(Pedido pedido) {
        return pedidoDAO.update(pedido);
    }

    public long deletarPedido(Pedido pedido) {
        return pedidoDAO.delete(pedido);
    }

    public ArrayList<Pedido> obterTodosPedidos() {
        return pedidoDAO.getAll();
    }

    public Pedido obterPedidoPorId(int id) {
        return pedidoDAO.getById(id);
    }
}
