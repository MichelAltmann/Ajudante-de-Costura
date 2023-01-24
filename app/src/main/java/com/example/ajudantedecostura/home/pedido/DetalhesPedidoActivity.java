package com.example.ajudantedecostura.home.pedido;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ajudantedecostura.databinding.ActivityDetalhesPedidoBinding;
import com.example.ajudantedecostura.home.adapters.MateriaisPedidoAdapter;

public class DetalhesPedidoActivity extends AppCompatActivity {

    private ActivityDetalhesPedidoBinding binding;
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
                Toast.makeText(DetalhesPedidoActivity.this, "sheesh", Toast.LENGTH_SHORT).show();
            }
            if (tipo == tipoMedida){

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalhesPedidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapterMaterial = new MateriaisPedidoAdapter(listaMaterial,tipoMaterial, onItemClick);
        binding.activityDetalhesPedidoRecyclerMateriais.setAdapter(adapterMaterial);

        adapterMedida = new MateriaisPedidoAdapter(listaMedidas,tipoMedida, onItemClick);
        binding.activityDetalhesPedidoRecyclerMedidas.setAdapter(adapterMedida);



    }
}