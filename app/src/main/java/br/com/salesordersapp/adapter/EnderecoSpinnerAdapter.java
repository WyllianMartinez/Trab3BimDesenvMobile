// ClienteSpinnerAdapter.java
package br.com.salesordersapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.salesordersapp.model.Cliente;
import br.com.salesordersapp.model.Endereco;

public class EnderecoSpinnerAdapter extends ArrayAdapter<Endereco> {

    public EnderecoSpinnerAdapter(Context context, List<Endereco> enderecos) {
        super(context, android.R.layout.simple_spinner_item, enderecos);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        Endereco endereco = getItem(position);
        if (endereco != null) {
            textView.setText(endereco.getCidade()+" - "+endereco.getUf()); // Substitua com o método apropriado para obter o nome do cliente
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        Endereco endereco = getItem(position);
        if (endereco != null) {
            textView.setText(endereco.getCidade()+" - "+endereco.getUf()); // Substitua com o método apropriado para obter o nome do cliente
        }

        return convertView;
    }
}
