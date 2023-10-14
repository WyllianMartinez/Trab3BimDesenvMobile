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
import br.com.salesordersapp.controller.EnderecoController;
import br.com.salesordersapp.controller.ProdutoController;
import br.com.salesordersapp.model.Endereco;
import br.com.salesordersapp.model.Produto;

public class ProdutoAdapter extends ArrayAdapter<Produto> {

    private ProdutoController produtoController;
    private boolean showDeleteIcon;

    public ProdutoAdapter(Context context, ArrayList<Produto> produtos, boolean showDeleteIcon) {
        super(context, 0, produtos);
        this.showDeleteIcon = showDeleteIcon;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Produto produto = getItem(position);

        produtoController = ProdutoController.getInstancia(getContext());

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_produto, parent, false);
        }

        TextView textViewProduto = convertView.findViewById(R.id.tvProduto);
        if (produto != null) {
            textViewProduto.setText(produto.getId() + " - " + produto.getDescricao() + " - " + produto.getUndMedida());
        }

        ImageView imgDelatarProduto = convertView.findViewById(R.id.imgDelatarProduto);

        imgDelatarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produto produto = getItem(position);

                if (produto != null) {
                    produtoController.deletarProduto(produto);
                    remove(produto);
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }
}