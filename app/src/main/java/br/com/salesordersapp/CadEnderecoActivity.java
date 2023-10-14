package br.com.salesordersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.salesordersapp.adapter.ClienteAdapter;
import br.com.salesordersapp.adapter.ClienteSpinnerAdapter;
import br.com.salesordersapp.adapter.EnderecoAdapter;
import br.com.salesordersapp.controller.ClienteController;
import br.com.salesordersapp.controller.EnderecoController;
import br.com.salesordersapp.databinding.ActivityCadEnderecoBinding;
import br.com.salesordersapp.model.Cliente;
import br.com.salesordersapp.model.Endereco;

public class CadEnderecoActivity extends AppCompatActivity {

    private ActivityCadEnderecoBinding binding;
    private ClienteController clienteController;
    private List<Cliente> clientes = new ArrayList<>();
    private EnderecoController enderecoController;
    private int id_cliente;

// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCadEnderecoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        clienteController = ClienteController.getInstancia(this);

        enderecoController = EnderecoController.getInstancia(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Endereço");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        List<Cliente> clientes = clienteController.obterTodosClientes();
        ClienteSpinnerAdapter clienteAdapter = new ClienteSpinnerAdapter(this, clientes);
        binding.spClientes.setAdapter(clienteAdapter);

        binding.spClientes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Cliente clienteSelecionado = (Cliente) parentView.getItemAtPosition(position);
                id_cliente = clienteSelecionado.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });


        ArrayList<Endereco> listaEnderecos = enderecoController.obterTodosEnderecos();
        EnderecoAdapter adapter = new EnderecoAdapter(this, listaEnderecos, true);
        binding.gvEndereco.setAdapter(adapter);

        binding.btnSalvarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String logradouro = binding.edtLogradouro.getText().toString();
                String bairro = binding.edtBairro.getText().toString();
                String numero = binding.edtNumero.getText().toString();
                String cidade = binding.edtCidade.getText().toString();
                String uf = binding.edtUF.getText().toString();

                Endereco endereco = new Endereco();
                endereco.setLogradouro(logradouro);
                endereco.setBairro(bairro);
                endereco.setNumero(numero);
                endereco.setCidade(cidade);
                endereco.setUf(uf);
                endereco.setIdCliente(Integer.valueOf(id_cliente));

                long resultado = enderecoController.inserirEndereco(endereco);

                if (resultado != -1) {
                    Toast.makeText(CadEnderecoActivity.this, "Cliente inserido com sucesso!", Toast.LENGTH_SHORT).show();
                    // Limpar os campos após a inserção
                    binding.edtLogradouro.setText("");
                    binding.edtBairro.setText("");
                    binding.edtCidade.setText("");
                    binding.edtNumero.setText("");
                    binding.edtUF.setText("");
                    binding.spClientes.setSelection(0);

                    atualizar();


                } else {
                    Toast.makeText(CadEnderecoActivity.this, "Erro ao inserir cliente.", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


    public void atualizar() {
        ArrayList<Endereco> listaEndereco = enderecoController.obterTodosEnderecos();
        EnderecoAdapter adapter = new EnderecoAdapter(this, listaEndereco, true);
        binding.gvEndereco.setAdapter(adapter);
    }
}