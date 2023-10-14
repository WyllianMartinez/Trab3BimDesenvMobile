package br.com.salesordersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.salesordersapp.adapter.ProdutoAdapter;
import br.com.salesordersapp.controller.ProdutoController;
import br.com.salesordersapp.databinding.ActivityCadProdutoBinding;
import br.com.salesordersapp.model.Produto;

public class CadProdutoActivity extends AppCompatActivity {

    private ActivityCadProdutoBinding binding;
    private ProdutoController produtoController;
    private List<Produto> produtos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produto);


        binding = ActivityCadProdutoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        produtoController = ProdutoController.getInstancia(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Produto");

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        List<Produto> listaProdutos =  produtoController.obterTodosProdutos();
        ProdutoAdapter adapter = new ProdutoAdapter(this, new ArrayList<>(listaProdutos), true);
        binding.gvProduto.setAdapter(adapter);

        binding.btnSalvarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String descricao = binding.edtDescricao.getText().toString();
                String vlrUnitario = binding.edtVlrUnitario.getText().toString();
                String undMedida = binding.edtUndMedida.getText().toString();

                Produto produto = new Produto();
                produto.setDescricao(descricao);
                produto.setVlrUnitario(Integer.valueOf(vlrUnitario));
                produto.setUndMedida(undMedida);

                long resultado = produtoController.inserirProduto(produto);

                if(resultado != -1){
                    Toast.makeText(CadProdutoActivity.this, "Produto inserido com sucesso!", Toast.LENGTH_SHORT).show();

                    binding.edtDescricao.setText("");
                    binding.edtVlrUnitario.setText("");
                    binding.edtUndMedida.setText("");

                    atualizar();
                } else {
                    Toast.makeText(CadProdutoActivity.this, "Erro ao inserir Produto.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void atualizar() {
        ArrayList<Produto> listaProdutos = produtoController.obterTodosProdutos();
        ProdutoAdapter adapter = new ProdutoAdapter(this, listaProdutos, true);
        binding.gvProduto.setAdapter(adapter);
    }
}