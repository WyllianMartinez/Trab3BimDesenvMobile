package br.com.salesordersapp.controller;

import android.content.Context;
import java.util.ArrayList;
import br.com.salesordersapp.dao.ClienteDAO;
import br.com.salesordersapp.model.Cliente;

public class ClienteController {
    private ClienteDAO clienteDAO;

    private Context context;
    private static ClienteController instancia;

    private ClienteController(Context context) {
        clienteDAO = ClienteDAO.getInstancia(context);
    }

    public static ClienteController getInstancia(Context context) {
        if (instancia == null) {
            instancia = new ClienteController(context);
        }
        return instancia;
    }

    public long inserirCliente(Cliente cliente) {
        return clienteDAO.insert(cliente);
    }

    public long atualizarCliente(Cliente cliente) {
        return clienteDAO.update(cliente);
    }

    public long deletarCliente(Cliente cliente) {
        return clienteDAO.delete(cliente);
    }





    public ArrayList<Cliente> obterTodosClientes() {
        return ClienteDAO.getInstancia(context).getAll();
    }

    public Cliente obterClientePorId(int id) {
        return clienteDAO.getById(id);
    }
}
