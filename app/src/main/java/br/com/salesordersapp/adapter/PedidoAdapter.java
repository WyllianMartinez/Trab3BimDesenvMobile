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
import br.com.salesordersapp.controller.PedidoController;
import br.com.salesordersapp.controller.ProdutoController;
import br.com.salesordersapp.model.Pedido;
import br.com.salesordersapp.model.Produto;

public class PedidoAdapter extends ArrayAdapter<Pedido> {

    private PedidoController pedidoController;
    private boolean showDeleteIcon;

    public PedidoAdapter(Context context, ArrayList<Pedido> pedidos, boolean showDeleteIcon) {
        super(context, 0, pedidos);
        this.showDeleteIcon = showDeleteIcon;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Pedido pedido = getItem(position);

        pedidoController = PedidoController.getInstancia(getContext());

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_produto, parent, false);
        }

        TextView textViewProduto = convertView.findViewById(R.id.tvProduto);
        if (pedido != null) {
            textViewProduto.setText(pedido.getId() + " - " + pedido.getId_Cliente() + " - " + pedido.getVlrTotal());
        }

        ImageView imgDelatarProduto = convertView.findViewById(R.id.imgDelatarProduto);

        imgDelatarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pedido pedido = getItem(position);

                if (pedido != null) {
                    pedidoController.deletarPedido(pedido);
                    remove(pedido);
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }
}