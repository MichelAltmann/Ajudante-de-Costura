package com.example.ajudantedecostura.home.pedido;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ajudantedecostura.databinding.ActivityCadastroPedidoBinding;
import com.example.ajudantedecostura.home.pedido.adapter.MateriaisPedidoAdapter;
import com.example.ajudantedecostura.login.DatePickerFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CadastroPedidoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private ActivityCadastroPedidoBinding binding;
    private MateriaisPedidoAdapter adapterMaterial;
    private MateriaisPedidoAdapter adapterMedida;
    private String[] listaMaterial = {"Lã", "Couro", "Malha"};
    private String[] listaMedidas = {"50cm", "90cm", "405KM"};
    private int tipoMaterial = 1; // tipo material == 1
    private int tipoMedida = 2; // tipo medida == 2
    private int dataClicada = 0; // 1 == dataCriada 2 == dataEntrega
    final DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

    MateriaisPedidoAdapter.OnItemClickListener onItemClick = (view, position, tipo) -> {
        if (tipo == tipoMaterial){
            Toast.makeText(CadastroPedidoActivity.this, "sheesh", Toast.LENGTH_SHORT).show();
        }
        if (tipo == tipoMedida){

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroPedidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapterMaterial = new MateriaisPedidoAdapter(listaMaterial,tipoMaterial, onItemClick);
        binding.activityCadastroPedidoRecyclerMateriais.setAdapter(adapterMaterial);

        adapterMedida = new MateriaisPedidoAdapter(listaMedidas,tipoMedida, onItemClick);
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
}