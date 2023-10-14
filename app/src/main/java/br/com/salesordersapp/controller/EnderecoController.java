package br.com.salesordersapp.controller;

import android.content.Context;
import java.util.ArrayList;
import br.com.salesordersapp.dao.EnderecoDAO;
import br.com.salesordersapp.model.Endereco;

public class EnderecoController {
    private EnderecoDAO enderecoDAO;
    private static EnderecoController instancia;

    private Context context;

    private EnderecoController(Context context) {
        enderecoDAO = EnderecoDAO.getInstancia(context);
    }

    public static EnderecoController getInstancia(Context context) {
        if (instancia == null) {
            instancia = new EnderecoController(context);
        }
        return instancia;
    }

    public long inserirEndereco(Endereco endereco) {
        return enderecoDAO.insert(endereco);
    }

    public long atualizarEndereco(Endereco endereco) {
        return enderecoDAO.update(endereco);
    }

    public long deletarEndereco(Endereco endereco) {
        return enderecoDAO.delete(endereco);
    }

    public ArrayList<Endereco> obterTodosEnderecos() {
        return EnderecoDAO.getInstancia(context).getAll();
    }

    public Endereco obterEnderecoPorId(int id) {
        return enderecoDAO.getById(id);
    }

    public Endereco obterEnderecoPorIdCliente(int id) {
        return enderecoDAO.getByClienteId(id);
    }
}
