package br.com.salesordersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.salesordersapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Button btnCliente;
    private Button btnPedido;
    private Button btnProduto;
    private Button btnEndereco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCliente = findViewById(R.id.btnCliente);
        btnPedido = findViewById(R.id.btnPedido);
        btnProduto = findViewById(R.id.btnProduto);
        btnEndereco = findViewById(R.id.btnEndereco);

        // Agora você pode acessar os elementos de interface do usuário diretamente usando o ViewBinding
        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadClienteActivity.class);
                startActivity(intent);
            }
        });

        btnProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadProdutoActivity.class);
                startActivity(intent);
            }
        });

        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadPedidoActivity.class);
                startActivity(intent);
            }
        });

        btnEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CadEnderecoActivity.class);
                startActivity(intent);
            }
        });
    }
}
