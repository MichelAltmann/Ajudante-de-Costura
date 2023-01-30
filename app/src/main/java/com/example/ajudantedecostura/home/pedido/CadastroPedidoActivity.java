package com.example.ajudantedecostura.home.pedido;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.example.ajudantedecostura.databinding.ActivityCadastroPedidoBinding;
import com.example.ajudantedecostura.home.adapters.MateriaisPedidoAdapter;

public class CadastroPedidoActivity extends AppCompatActivity {

    private ActivityCadastroPedidoBinding binding;
    private MateriaisPedidoAdapter adapterMaterial;
    private MateriaisPedidoAdapter adapterMedida;
    private String[] listaMaterial = {"Lã", "Couro", "Malha"};
    private String[] listaMedidas = {"50cm", "90cm", "405KM"};
    private int tipoMaterial = 1; // tipo material == 1
    private int tipoMedida = 2; // tipo medida == 2

    MateriaisPedidoAdapter.OnItemClickListener onItemClick = new MateriaisPedidoAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position, int tipo) {
            if (tipo == tipoMaterial){
                Toast.makeText(CadastroPedidoActivity.this, "sheesh", Toast.LENGTH_SHORT).show();
            }
            if (tipo == tipoMedida){

            }
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

        binding.activityCadastroPedidoBtnExcluir.setOnClickListener(v -> {

        });

        binding.activityCadastroPedidoBtnFinalizar.setOnClickListener(v -> {

        });
    }



}