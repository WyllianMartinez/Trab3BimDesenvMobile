package br.com.salesordersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import br.com.salesordersapp.adapter.ClienteSpinnerAdapter;
import br.com.salesordersapp.adapter.EnderecoAdapter;
import br.com.salesordersapp.adapter.EnderecoSpinnerAdapter;
import br.com.salesordersapp.adapter.PedidoAdapter;
import br.com.salesordersapp.adapter.ProdutoAdapter;
import br.com.salesordersapp.adapter.ProdutoSpinnerAdapter;
import br.com.salesordersapp.controller.ClienteController;
import br.com.salesordersapp.controller.EnderecoController;
import br.com.salesordersapp.controller.PedidoController;
import br.com.salesordersapp.controller.ProdutoController;
import br.com.salesordersapp.databinding.ActivityCadPedidoBinding;
import br.com.salesordersapp.model.Cliente;
import br.com.salesordersapp.model.Endereco;
import br.com.salesordersapp.model.Pedido;
import br.com.salesordersapp.model.Produto;

public class CadPedidoActivity extends AppCompatActivity {

    private ActivityCadPedidoBinding binding;
    private ClienteController clienteController;
    private EnderecoController enderecoController;
    private ProdutoController produtoController;
    private PedidoController pedidoController;
    private List<Produto> produtos = new ArrayList<>();
    private int id_endereco;
    private int vlrTotalProduto;
    private int vlrTotalPedido;
    private int vlrFrete;
    private int id_cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadPedidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        clienteController = ClienteController.getInstancia(this);
        enderecoController = EnderecoController.getInstancia(this);
        produtoController = ProdutoController.getInstancia(this);
        pedidoController = PedidoController.getInstancia(this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pedido");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        setupClienteSpinner();
        setupEnderecoSpinner();
        setupCondPgtoSpinner();
        setupQtdParcelasSpinner();
        setupProdutoSpinner();
        setupPedidoGrid();

        ArrayList<Produto> listaProdutos = produtoController.obterTodosProdutos();
        ProdutoAdapter adapter = new ProdutoAdapter(this, listaProdutos, true);
        binding.gvProdutos.setAdapter(adapter);

        binding.btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarItemAoPedido();
            }
        });

        binding.btnSalvarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concluirPedido();
            }
        });

        binding.btnCancelarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.spCliente.setSelection(0);
                binding.spEndereco.setSelection(0);
                binding.spProdutos.setSelection(0);
                binding.gvProdutos.removeAllViews();
                binding.spCondPgto.setSelection(0);
                binding.spQtdParcelas.setSelection(0);

            }
        });






    }

    private void calcularVlrTotal(){

        if(binding.spCondPgto.toString() == "A vista"){

            int valorTotal = vlrTotalPedido + vlrFrete;

            binding.edtVlrParcela.setText(valorTotal);
            binding.edtVlrParcela.setText(String.valueOf(valorTotal));


        }else{

            calcularVltParcela();

            int valorTotal = vlrTotalPedido + vlrFrete;

            binding.edtVlrParcela.setText(String.valueOf(valorTotal));


        }

    }


    private  void calcularVltParcela(){

        if(binding.spQtdParcelas.toString() == "1") {

            int parcelas = vlrTotalProduto + vlrFrete;
            binding.edtVlrParcela.setText(parcelas);


        } if(binding.spQtdParcelas.toString() == "2"){

            int parcelas = vlrTotalProduto + vlrFrete / 2;
            binding.edtVlrParcela.setText(parcelas);

        } if (binding.spQtdParcelas.toString() =="3") {

            int parcelas = vlrTotalProduto + vlrFrete / 3;
            binding.edtVlrParcela.setText(parcelas);
        }

    }
    private void setupPedidoGrid(){

        ArrayList<Pedido> listaPedidos = pedidoController.obterTodosPedidos();
        PedidoAdapter adapter = new PedidoAdapter(this, listaPedidos, true);
        binding.gvPedidos.setAdapter(adapter);
    }

    private void setupProdutoSpinner(){

        List<Produto> listaProdutos =  produtoController.obterTodosProdutos();
        ProdutoSpinnerAdapter adapter = new ProdutoSpinnerAdapter(this, new ArrayList<>(listaProdutos));
        binding.spProdutos.setAdapter(adapter);
    }

    private void setupClienteSpinner() {
        List<Cliente> clientes = clienteController.obterTodosClientes();
        ClienteSpinnerAdapter clienteAdapter = new ClienteSpinnerAdapter(this, clientes);
        binding.spCliente.setAdapter(clienteAdapter);

        binding.spCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Cliente clienteSelecionado = (Cliente) parentView.getItemAtPosition(position);
                id_cliente = clienteSelecionado.getId();
                setupEnderecoSpinner(); // Atualize os endereços quando o cliente for selecionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private void setupEnderecoSpinner() {
        List<Endereco> listaEnderecos = Collections.singletonList(enderecoController.obterEnderecoPorIdCliente(id_cliente));

        if (listaEnderecos != null && !listaEnderecos.isEmpty()) {
            EnderecoSpinnerAdapter adapterEndereco = new EnderecoSpinnerAdapter(this, listaEnderecos);
            binding.spEndereco.setAdapter(adapterEndereco);     binding.spEndereco.setAdapter(adapterEndereco);

            binding.spEndereco.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    Endereco enderecoSelecionado = (Endereco) parentView.getItemAtPosition(position);
                    if (enderecoSelecionado != null) {
                        id_endereco = enderecoSelecionado.getId();
                    } else {

                    }
                }


                @Override
                public void onNothingSelected(AdapterView<?> parentView) {

                }
            });

            binding.spEndereco.setEnabled(true);
        } else {

            binding.spEndereco.setEnabled(false);

        }
    }





    private void setupCondPgtoSpinner() {
        String[] condPgto = {"A vista", "A prazo"};
        ArrayAdapter<String> adapterCondPgto = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, condPgto);
        adapterCondPgto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spCondPgto.setAdapter(adapterCondPgto);

        binding.spCondPgto.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    notifySpinnerClicked();
                }
                return false;
            }
        });

        binding.spCondPgto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue = parentView.getItemAtPosition(position).toString();
                if ("A vista".equals(selectedValue) && position == 0) {
                    binding.spQtdParcelas.setSelection(0);
                    calcularVlrTotal();
                } else if ("A prazo".equals(selectedValue)) {
                    binding.spQtdParcelas.setSelection(1);
                    calcularVlrTotal();

                }

                binding.spQtdParcelas.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

        private void notifySpinnerClicked () {

        }


        private void setupQtdParcelasSpinner() {
        String[] qtdParcelas = {"1", "2"};
        ArrayAdapter<String> adapterQtdParcelas = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, qtdParcelas);
        adapterQtdParcelas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spQtdParcelas.setAdapter(adapterQtdParcelas);
    }

    private void adicionarItemAoPedido() {
        Produto produtoSelecionado = (Produto) binding.spProdutos.getSelectedItem();

    }


    private void concluirPedido() {
        String condPagamento = binding.spCondPgto.getSelectedItem().toString();

        if ("À vista".equals(condPagamento)) {
            vlrTotalPedido *= 0.95; // Desconto de 5%
        } else {
            vlrTotalPedido *= 1.05; // Acréscimo de 5%
        }

        Toast.makeText(this, "Pedido de venda cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
    }


}
