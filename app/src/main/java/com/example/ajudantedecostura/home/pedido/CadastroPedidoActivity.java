package com.example.ajudantedecostura.home.pedido;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ajudantedecostura.databinding.ActivityCadastroPedidoBinding;
import com.example.ajudantedecostura.home.pedido.adapter.MateriaisPedidoAdapter;
import com.example.ajudantedecostura.home.pedido.adapter.MedidasPedidoAdapter;
import com.example.ajudantedecostura.login.DatePickerFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import modelDominio.Material;
import modelDominio.Medida;
import modelDominio.Medidas;

public class CadastroPedidoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, CriaMaterialDialogFragment.CriaMaterialDialogListener, CriaMedidaDialogFragment.CriaMedidaDialogListener {


    private ActivityCadastroPedidoBinding binding;
    private MateriaisPedidoAdapter adapterMaterial;
    private MedidasPedidoAdapter adapterMedida;
    private ArrayList<Material> listaMaterial = new ArrayList<>();
    private ArrayList<Medida> medidas = new ArrayList<>();
    private int dataClicada = 0; // 1 == dataCriada 2 == dataEntrega
    final DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

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

        adapterMaterial = new MateriaisPedidoAdapter(listaMaterial, onMateriaisItemClick);
        binding.activityCadastroPedidoRecyclerMateriais.setAdapter(adapterMaterial);

        binding.activityCadastroPedidoFabAddMaterial.setOnClickListener(v -> {
            FragmentManager fm = getSupportFragmentManager();
            CriaMaterialDialogFragment criaMaterialDialogFragment = CriaMaterialDialogFragment.newInstance("Add Material", listaMaterial);
            criaMaterialDialogFragment.show(fm, "fragment_add_material");
        });

        binding.activityCadastroPedidoFabAddMedida.setOnClickListener(v -> {
            if (medidas.size() >= 20){
                Toast.makeText(this, "Número limite de medidas atingido.", Toast.LENGTH_SHORT).show();
            } else {
                FragmentManager fm = getSupportFragmentManager();
                CriaMedidaDialogFragment criaMedidaDialogFragment = CriaMedidaDialogFragment.newInstance("Add Material", medidas);
                criaMedidaDialogFragment.show(fm, "fragment_add_medida");
            }
            
        });

        adapterMedida = new MedidasPedidoAdapter(medidas, onMedidasItemClick);
        binding.activityCadastroPedidoRecyclerMedidas.setAdapter(adapterMedida);

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

        });
    }


    @Override
    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
        Calendar c = new GregorianCalendar(ano, mes, dia);
        if (dataClicada == 1){
            binding.activityCadastroPedidoTxtDataCriacao.setText(dataFormatada.format(c.getTime()));
            dataClicada = 0;
        } else if (dataClicada == 2){
            binding.activityCadastroPedidoTxtDataEntrega.setText(dataFormatada.format(c.getTime()));
            dataClicada = 0;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    @Override
    public void onDialogMaterialCriar(ArrayList<Material> materiais) {
        adapterMaterial = new MateriaisPedidoAdapter(materiais, onMateriaisItemClick);
        binding.activityCadastroPedidoRecyclerMateriais.setAdapter(adapterMaterial);
    }

    public void onDialogMedidaCriar(ArrayList<Medida> medidas){
        Medidas medidas1 = TransformaEmMedidas.transformaEmMedidas(medidas);
        adapterMedida = new MedidasPedidoAdapter(medidas, onMedidasItemClick);
        binding.activityCadastroPedidoRecyclerMedidas.setAdapter(adapterMedida);
    }
}