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
import br.com.salesordersapp.controller.EnderecoController;
import br.com.salesordersapp.model.Cliente;
import br.com.salesordersapp.model.Endereco;

public class EnderecoAdapter extends ArrayAdapter<Endereco> {

    private EnderecoController enderecoController;
    private boolean showDeleteIcon;
    public EnderecoAdapter(Context context, ArrayList<Endereco> enderecos, boolean showDeleteIcon) {
        super(context, 0, enderecos);
        this.showDeleteIcon = showDeleteIcon;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Endereco endereco = getItem(position);

        enderecoController = EnderecoController.getInstancia(getContext());

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_endereco, parent, false);
        }

        TextView textViewCliente = convertView.findViewById(R.id.tvEndereco);
        if (endereco != null) {
            textViewCliente.setText(endereco.getLogradouro()+" - "+ endereco.getBairro()+" - "+endereco.getCidade());
        }

        ImageView imgDelatarEndereco = convertView.findViewById(R.id.imgDelatarEndereco);

        imgDelatarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Endereco endereco = getItem(position);

                if (endereco != null) {
                    enderecoController.deletarEndereco(endereco);
                    remove(endereco);
                    notifyDataSetChanged();
                }
            }
        });


        return convertView;
    }

}
