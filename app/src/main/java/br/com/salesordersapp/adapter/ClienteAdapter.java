package br.com.salesordersapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import br.com.salesordersapp.R;
import br.com.salesordersapp.controller.ClienteController;
import br.com.salesordersapp.model.Cliente;

public class ClienteAdapter extends ArrayAdapter<Cliente> {

    private ClienteController clienteController;
    private boolean showDeleteIcon;

    public ClienteAdapter(Context context, ArrayList<Cliente> clientes, boolean showDeleteIcon) {
        super(context, 0);
        this.showDeleteIcon = showDeleteIcon;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Cliente cliente = getItem(position);

        clienteController = ClienteController.getInstancia(getContext());

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_cliente, parent, false);
        }

        TextView textViewCliente = convertView.findViewById(R.id.tvCliente);
        if (cliente != null) {
            textViewCliente.setText(cliente.getNome() + " - " + cliente.getCpf());
        }

        ImageView imgDelatarCliente = convertView.findViewById(R.id.imgDelatarCliente);

        if (imgDelatarCliente != null) {
            imgDelatarCliente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cliente cliente = getItem(position);

                    if (cliente != null) {
                        clienteController.deletarCliente(cliente);
                        remove(cliente);
                        notifyDataSetChanged();
                    }
                }
            });
        }

        return convertView;
    }
}