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
import br.com.salesordersapp.model.Produto;

public class ProdutoSpinnerAdapter extends ArrayAdapter<Produto> {

    public ProdutoSpinnerAdapter(Context context, List<Produto> produtos) {
        super(context, android.R.layout.simple_spinner_item, produtos);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        Produto produto = getItem(position);
        if (produto != null) {
            textView.setText(produto.getDescricao()); // Substitua com o método apropriado para obter o nome do cliente
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        Produto produto = getItem(position);
        if (produto != null) {
            textView.setText(produto.getDescricao()); // Substitua com o método apropriado para obter o nome do cliente
        }

        return convertView;
    }
}
