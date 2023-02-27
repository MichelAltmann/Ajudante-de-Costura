package com.example.ajudantedecostura.home.pedido;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.example.ajudantedecostura.login.CadastroActivity.REQUEST_ID_MULTIPLE_PERMISSIONS;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ajudantedecostura.databinding.ActivityCadastroPedidoBinding;
import com.example.ajudantedecostura.home.cliente.CadastroClienteViewModel;
import com.example.ajudantedecostura.home.cliente.adapter.ListaClientesAdapter;
import com.example.ajudantedecostura.home.pedido.adapter.CustomSpinnerAdapter;
import com.example.ajudantedecostura.home.pedido.adapter.MateriaisPedidoAdapter;
import com.example.ajudantedecostura.home.pedido.adapter.MedidasPedidoAdapter;
import com.example.ajudantedecostura.login.DatePickerFragment;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import modelDominio.Cliente;
import modelDominio.Material;
import modelDominio.Medida;
import modelDominio.Medidas;
import modelDominio.Pedido;

public class CadastroPedidoActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener,
        CriaMaterialDialogFragment.CriaMaterialDialogListener,
        CriaMedidaDialogFragment.CriaMedidaDialogListener {


    private ActivityCadastroPedidoBinding binding;
    private MateriaisPedidoAdapter adapterMaterial;
    private MedidasPedidoAdapter adapterMedida;
    private ArrayList<Material> listaMaterial = new ArrayList<>();

    Medidas medidas;
    private ArrayList<Medida> listaMedidas = new ArrayList<>();
    private int dataClicada = 0; // 1 == dataCriada 2 == dataEntrega
    final DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    public static final String CAMERA = "android.permission.CAMERA";
    private Bitmap selectedImageBitmap;
    private InformacoesApp informacoesApp;
    private ArrayList<Cliente> clientes;
    private ArrayList<String> nomesClientes = new ArrayList<>();
    private Cliente cliente = null;

    private CadastroPedidoViewModel viewModel;

    private CadastroClienteViewModel viewModelCliente;


    MateriaisPedidoAdapter.OnMateriaisItemClickListener onMateriaisItemClick = (view, position) -> {
    };

    MedidasPedidoAdapter.OnMedidaItemClickListener onMedidasItemClick = (view, position) -> {
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroPedidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        informacoesApp = (InformacoesApp) getApplicationContext();

        viewModel = new CadastroPedidoViewModel(informacoesApp);

        viewModelCliente = new CadastroClienteViewModel(informacoesApp);

        binding.activityCadastroPedidoTxtDataCriacao.setText(dataFormatada.format(Calendar.getInstance().getTime()));

        adapterMaterial = new MateriaisPedidoAdapter(listaMaterial, onMateriaisItemClick);
        binding.activityCadastroPedidoRecyclerMateriais.setAdapter(adapterMaterial);

        clientes = informacoesApp.getClientes();

        nomesClientes.add("Cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            nomesClientes.add(clientes.get(i).getNome());
        }
        nomesClientes.add("Adicionar apenas nome");

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_dropdown_item, nomesClientes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.activityCadastroPedidoSpinnerNomeCliente.setAdapter(adapter);

        binding.activityCadastroPedidoSpinnerNomeCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i + 1 != nomesClientes.size()) {
                    binding.activityCadastroPedidoTxtNomeCliente.setVisibility(View.GONE);
                    if (i > 0) {
                        cliente = clientes.get(i - 1);
                    }
                } else {
                    binding.activityCadastroPedidoTxtNomeCliente.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.activityCadastroPedidoFabAddMaterial.setOnClickListener(v -> {
            FragmentManager fm = getSupportFragmentManager();
            CriaMaterialDialogFragment criaMaterialDialogFragment = CriaMaterialDialogFragment.newInstance("Add Material", listaMaterial);
            criaMaterialDialogFragment.show(fm, "fragment_add_material");
        });

        binding.activityCadastroPedidoFabAddMedida.setOnClickListener(v -> {
            if (listaMedidas.size() >= 20) {
                Toast.makeText(this, "Número limite de medidas atingido.", Toast.LENGTH_SHORT).show();
            } else {
                FragmentManager fm = getSupportFragmentManager();
                CriaMedidaDialogFragment criaMedidaDialogFragment = CriaMedidaDialogFragment.newInstance("Add Material", listaMedidas);
                criaMedidaDialogFragment.show(fm, "fragment_add_medida");
            }

        });

        adapterMedida = new MedidasPedidoAdapter(listaMedidas, onMedidasItemClick);
        binding.activityCadastroPedidoRecyclerMedidas.setAdapter(adapterMedida);

        binding.activityCadastroPedidoImagem.setOnClickListener(v -> {
            imageChooser();
        });

        binding.activityCadastroPedidoTxtDataCriacao.setInputType(InputType.TYPE_NULL);
        binding.activityCadastroPedidoTxtDataCriacao.setOnClickListener(v -> {
            dataClicada = 1;
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.show(getSupportFragmentManager(), "Date dialog");
        });

        binding.activityCadastroPedidoTxtDataEntrega.setInputType(InputType.TYPE_NULL);
        binding.activityCadastroPedidoTxtDataEntrega.setOnClickListener(v -> {
            dataClicada = 2;
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.show(getSupportFragmentManager(), "Date dialog");
        });

        binding.activityCadastroPedidoBtnCancelar.setOnClickListener(v -> {

            finish();
        });

        binding.activityCadastroPedidoBtnCriar.setOnClickListener(v -> {

            Date dataCriacao;
            Date dataEntrega;
            String titulo = binding.activityCadastroPedidoTxtTitulo.getText().toString();
            String descricao = binding.activityCadastroPedidoTxtDescricao.getText().toString();
            String nomeCliente = binding.activityCadastroPedidoTxtNomeCliente.getText().toString();
            Integer prioridade;
            byte[] imagem;
            Float preco;

            if (binding.activityCadastroPedidoImagem.getDrawable() != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                imagem = stream.toByteArray();
            } else {
                imagem = null;
            }

            medidas = TransformaEmMedidas.transformaEmMedidas(listaMedidas);

            if (binding.activityCadastroPedidoRbAlta.isChecked()) {
                prioridade = 2;
            } else if (binding.activityCadastroPedidoRbMedia.isChecked()) {
                prioridade = 1;
            } else {
                prioridade = 0;
            }

            if (!binding.activityCadastroPedidoTxtPreco.getText().toString().equals("")) {
                preco = Float.parseFloat(binding.activityCadastroPedidoTxtPreco.getText().toString());
            } else {
                preco = 0f;
            }

            if (!binding.activityCadastroPedidoTxtDataCriacao.getText().toString().equals("")){
                try {
                    dataCriacao = dataFormatada.parse(binding.activityCadastroPedidoTxtDataCriacao.getText().toString());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            } else {
                dataCriacao = null;
            }

            if (!binding.activityCadastroPedidoTxtDataEntrega.getText().toString().equals("")){
                try {
                    dataEntrega = dataFormatada.parse(binding.activityCadastroPedidoTxtDataEntrega.getText().toString());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            } else {
                dataEntrega = null;
            }


            if (!titulo.equals("")) {

                if (binding.activityCadastroPedidoSpinnerNomeCliente.getSelectedItemPosition() > 0 && binding.activityCadastroPedidoSpinnerNomeCliente.getSelectedItemPosition() + 1 < nomesClientes.size()) {

                    String idPedido = UUID.randomUUID().toString();
                    Pedido pedido = new Pedido(idPedido, cliente, prioridade, titulo, preco, descricao, dataEntrega, dataCriacao, listaMaterial, imagem, medidas);
                    viewModel.cadastroPedido(pedido);
                    viewModel.getMsgPedido().observe(this, msg -> {
                        finish();
                    });

                } else if (binding.activityCadastroPedidoSpinnerNomeCliente.getSelectedItemPosition() + 1 == nomesClientes.size()) {
                    if (!binding.activityCadastroPedidoTxtNomeCliente.getText().toString().equals("")) {

                        String id = UUID.randomUUID().toString();
                        cliente = new Cliente(informacoesApp.getCostureiraLogada(), id, null, nomeCliente, null, null, dataEntrega, imagem, 0, null, null, null, 0);
                        viewModelCliente.cadastraCliente(cliente);

                        viewModelCliente.getMsg().observe(this, msg -> {
                            String idPedido = UUID.randomUUID().toString();
                            Pedido pedido = new Pedido(idPedido, cliente, prioridade, titulo, preco, descricao, dataEntrega, dataCriacao, listaMaterial, imagem, medidas);
                            viewModel.cadastroPedido(pedido);
                            viewModel.getMsgPedido().observe(this, msgPedido -> {
                                finish();
                            });
                        });

                    } else {
                        binding.activityCadastroPedidoTxtNomeCliente.requestFocus();
                        binding.activityCadastroPedidoTxtNomeCliente.setError("Insira o nome do Cliente.");
                    }
                } else {
                    Toast.makeText(informacoesApp, "Selecione um cliente.", Toast.LENGTH_SHORT).show();
                }

            } else {
                binding.activityCadastroPedidoTxtTitulo.requestFocus();
                binding.activityCadastroPedidoTxtTitulo.setError("Erro: Insira um título.");
            }
        });

        binding.activityCadastroPedidoFabVoltar.setOnClickListener(v -> {
            finish();
        });

    }


    @Override
    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
        Calendar c = new GregorianCalendar(ano, mes, dia);
        if (dataClicada == 1) {
            binding.activityCadastroPedidoTxtDataCriacao.setText(dataFormatada.format(c.getTime()));
            dataClicada = 0;
        } else if (dataClicada == 2) {
            binding.activityCadastroPedidoTxtDataEntrega.setText(dataFormatada.format(c.getTime()));
            dataClicada = 0;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();

            if (data != null && data.getData() != null) {
                Uri selectedImageUri = data.getData();
                try {
                    selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    binding.activityCadastroPedidoImagem.setImageBitmap(selectedImageBitmap);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    });

    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    @Override
    public void onDialogMaterialCriar(ArrayList<Material> materiais) {
        adapterMaterial = new MateriaisPedidoAdapter(materiais, onMateriaisItemClick);
        binding.activityCadastroPedidoRecyclerMateriais.setAdapter(adapterMaterial);
    }

    public void onDialogMedidaCriar(ArrayList<Medida> listaMedida) {
        this.listaMedidas = listaMedida;
        adapterMedida = new MedidasPedidoAdapter(listaMedida, onMedidasItemClick);
        binding.activityCadastroPedidoRecyclerMedidas.setAdapter(adapterMedida);
    }
}