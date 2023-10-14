package br.com.salesordersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.salesordersapp.adapter.ClienteAdapter;
import br.com.salesordersapp.controller.ClienteController;
import br.com.salesordersapp.databinding.ActivityCadClienteBinding;
import br.com.salesordersapp.databinding.ActivityCadPedidoBinding;
import br.com.salesordersapp.model.Cliente;

public class CadClienteActivity extends AppCompatActivity {

    private ActivityCadClienteBinding binding;
    private ClienteController clienteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cliente);

        binding = ActivityCadClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cliente");

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        clienteController = ClienteController.getInstancia(this);


        ArrayList<Cliente> listaClientes = clienteController.obterTodosClientes();
        ClienteAdapter adapter = new ClienteAdapter(this, listaClientes, true);
        binding.gvClienteCad.setAdapter(adapter);



        binding.btnSalvarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = binding.edtNome.getText().toString();
                String cpf = binding.edtCPF.getText().toString();
                String dtnascimento = binding.edtNascimento.getText().toString();

                Cliente cliente = new Cliente(nome, cpf, dtnascimento);

                long resultado = clienteController.inserirCliente(cliente);

                if (resultado != -1) {
                    Toast.makeText(CadClienteActivity.this, "Cliente inserido com sucesso!", Toast.LENGTH_SHORT).show();
                    // Limpar os campos após a inserção
                    binding.edtNome.setText("");
                    binding.edtCPF.setText("");
                    binding.edtNascimento.setText("");

                    atualizar();
                } else {
                    Toast.makeText(CadClienteActivity.this, "Erro ao inserir cliente.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        binding.btnCancelarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.edtNome.setText("");
                binding.edtCPF.setText("");
                binding.edtNascimento.setText("");

            }
        });



    }


    public void atualizar() {
        ArrayList<Cliente> listaClientes = clienteController.obterTodosClientes();
        ClienteAdapter adapter = new ClienteAdapter(this, listaClientes, true);
        binding.gvClienteCad.setAdapter(adapter);
    }



}