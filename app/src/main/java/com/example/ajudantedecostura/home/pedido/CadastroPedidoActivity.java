package com.example.ajudantedecostura.home.pedido;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.example.ajudantedecostura.login.CadastroActivity.REQUEST_ID_MULTIPLE_PERMISSIONS;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ajudantedecostura.R;
import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.databinding.ActivityCadastroPedidoBinding;
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

import modelDominio.Cliente;
import modelDominio.Material;
import modelDominio.Medida;
import modelDominio.Medidas;
import modelDominio.Pedido;

public class CadastroPedidoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, CriaMaterialDialogFragment.CriaMaterialDialogListener, CriaMedidaDialogFragment.CriaMedidaDialogListener {


    private ActivityCadastroPedidoBinding binding;
    private MateriaisPedidoAdapter adapterMaterial;
    private MedidasPedidoAdapter adapterMedida;
    private ArrayList<Material> listaMaterial = new ArrayList<>();

    Medidas medidas;
    private ArrayList<Medida> listaMedidas = new ArrayList<>();
    private int dataClicada = 0; // 1 == dataCriada 2 == dataEntrega
    final DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    public static final String CAMERA = "android.permission.CAMERA";
    Bitmap selectedImageBitmap;
    InformacoesApp informacoesApp;
    ArrayList<Cliente> clientes;
    ArrayList<String> nomesClientes = new ArrayList<>();
    Cliente cliente = null;


    MateriaisPedidoAdapter.OnMateriaisItemClickListener onMateriaisItemClick = (view, position) -> {
        Toast.makeText(CadastroPedidoActivity.this, "sheesh", Toast.LENGTH_SHORT).show();
    };

    MedidasPedidoAdapter.OnMedidaItemClickListener onMedidasItemClick = (view, position) -> {
        Toast.makeText(this, "Sheesh", Toast.LENGTH_SHORT).show();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroPedidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        informacoesApp = (InformacoesApp) getApplicationContext();

        binding.activityCadastroPedidoTxtDataCriacao.setText(dataFormatada.format(Calendar.getInstance().getTime()));

        adapterMaterial = new MateriaisPedidoAdapter(listaMaterial, onMateriaisItemClick);
        binding.activityCadastroPedidoRecyclerMateriais.setAdapter(adapterMaterial);

        clientes = informacoesApp.getClientes();

        nomesClientes.add("Cliente:");
        for (int i = 0; i < clientes.size(); i++){
            nomesClientes.add(clientes.get(i).getNome());
        }
        nomesClientes.add("Adicionar apenas nome");

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_dropdown_item, nomesClientes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.activityCadastroPedidoSpinnerNomeCliente.setAdapter(adapter);

        binding.activityCadastroPedidoSpinnerNomeCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i + 1 != nomesClientes.size()){
                    binding.activityCadastroPedidoTxtNomeCliente.setVisibility(View.GONE);
                    if (i > 0){
                        cliente = clientes.get(i - 1);
                        Toast.makeText(informacoesApp, cliente.getIdPessoa() + "", Toast.LENGTH_SHORT).show();
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

            Date dataCriacao = null;
            Date dataEntrega = null;
            String titulo = binding.activityCadastroPedidoTxtTitulo.getText().toString();
            String descricao = binding.activityCadastroPedidoTxtDescricao.getText().toString();
            String nomeCliente = binding.activityCadastroPedidoTxtNomeCliente.getText().toString();
            Integer prioridade;
            byte[] imagem = null;
            Float preco = 0f;
            if (binding.activityCadastroPedidoImagem.getDrawable() != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                imagem = stream.toByteArray();
            }


            if (binding.activityCadastroPedidoRbAlta.isChecked()) {
                prioridade = 2;
            } else if (binding.activityCadastroPedidoRbMedia.isChecked()) {
                prioridade = 1;
            } else {
                prioridade = 0;
            }

            if (!binding.activityCadastroPedidoTxtPreco.getText().toString().equals("")) {
                preco = Float.parseFloat(binding.activityCadastroPedidoTxtPreco.getText().toString());
            }

            try {
                dataCriacao = dataFormatada.parse(binding.activityCadastroPedidoTxtDataCriacao.getText().toString());
                dataEntrega = dataFormatada.parse(binding.activityCadastroPedidoTxtDataEntrega.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (!titulo.equals("")){
                if (cliente == null){
                    cliente = new Cliente(informacoesApp.getCostureiraLogada(), null, nomeCliente, null, null, dataEntrega, imagem, 0, null, null, null, 0);
                }

                Toast.makeText(informacoesApp, cliente.getNome(), Toast.LENGTH_SHORT).show();
                Pedido pedido = new Pedido(cliente, prioridade, titulo, preco, descricao, dataEntrega, dataCriacao, listaMaterial, imagem, medidas);

                Log.i("asd", pedido.toString());

                Thread thread = new Thread((Runnable) () -> {
                    ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
                    String msg = conexaoSocket.cadastraPedido(pedido);
                    runOnUiThread((Runnable) () -> {
                        Log.i("asd", "onCreate: " + msg);
                        Toast.makeText(informacoesApp, msg, Toast.LENGTH_SHORT).show();
                    });
                });
                thread.start();

                Toast.makeText(informacoesApp, "Pedido criado com sucesso!", Toast.LENGTH_SHORT).show();

                finish();
            } else {
                binding.activityCadastroPedidoTxtTitulo.requestFocus();
                binding.activityCadastroPedidoTxtTitulo.setError("Erro: Insira um título.");
            }


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


    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
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
            }
    );

    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    public static boolean checkAndRequestPermissions(final Activity context) {
        int WExtstorePermission = ContextCompat.checkSelfPermission(context,
                WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context,
                CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                    .add(WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded
                            .toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }


    @Override
    public void onDialogMaterialCriar(ArrayList<Material> materiais) {
        adapterMaterial = new MateriaisPedidoAdapter(materiais, onMateriaisItemClick);
        binding.activityCadastroPedidoRecyclerMateriais.setAdapter(adapterMaterial);
    }

    public void onDialogMedidaCriar(ArrayList<Medida> listaMedida) {
        medidas = TransformaEmMedidas.transformaEmMedidas(listaMedida);
        adapterMedida = new MedidasPedidoAdapter(listaMedida, onMedidasItemClick);
        binding.activityCadastroPedidoRecyclerMedidas.setAdapter(adapterMedida);
    }
}