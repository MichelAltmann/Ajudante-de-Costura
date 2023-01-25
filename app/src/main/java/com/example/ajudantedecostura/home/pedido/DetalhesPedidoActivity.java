package com.example.ajudantedecostura.home.pedido;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.example.ajudantedecostura.R;
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
    private Boolean modoEdicao = false;

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

        mudaInput(InputType.TYPE_NULL,
                InputType.TYPE_NULL,
                InputType.TYPE_NULL,
                InputType.TYPE_NULL,
                InputType.TYPE_NULL,
                View.GONE);

        adapterMaterial = new MateriaisPedidoAdapter(listaMaterial,tipoMaterial, onItemClick);
        binding.activityDetalhesPedidoRecyclerMateriais.setAdapter(adapterMaterial);

        adapterMedida = new MateriaisPedidoAdapter(listaMedidas,tipoMedida, onItemClick);
        binding.activityDetalhesPedidoRecyclerMedidas.setAdapter(adapterMedida);

        binding.activityDetalhesPedidoFabEdicao.setOnClickListener(v -> {
            habilitaDesabilitaEdicao();
        });
    }

    private void habilitaDesabilitaEdicao() {
        if (!modoEdicao){
            modoEdicao = true;
            mudaInput(InputType.TYPE_CLASS_TEXT,
                    InputType.TYPE_TEXT_FLAG_MULTI_LINE,
                    InputType.TYPE_CLASS_DATETIME,
                    InputType.TYPE_NUMBER_FLAG_DECIMAL,
                    InputType.TYPE_TEXT_FLAG_CAP_WORDS,
                    View.VISIBLE);
         //   binding.activityDetalhesPedidoFabEdicao.setImageIcon(R.drawable.ic_cancelar);
        } else if (modoEdicao){
            modoEdicao = false;
            mudaInput(InputType.TYPE_NULL,
                    InputType.TYPE_NULL,
                    InputType.TYPE_NULL,
                    InputType.TYPE_NULL,
                    InputType.TYPE_NULL,
                    View.GONE);
        }
    }

    private void mudaInput(int typeClassText, int typeTextFlagMultiLine, int typeClassDatetime, int typeNumberFlagDecimal, int typeTextFlagCapWords, int visible) {
        binding.activityDetalhesPedidoTxtTitulo.setInputType(typeClassText);
        binding.activityDetalhesPedidoTxtDescricao.setInputType(typeTextFlagMultiLine);
        binding.activityDetalhesPedidoTxtDataCriacao.setInputType(typeClassDatetime);
        binding.activityDetalhesPedidoTxtDataEntrega.setInputType(typeClassDatetime);
        binding.activityDetalhesPedidoTxtPreco.setInputType(typeNumberFlagDecimal);
        binding.activityDetalhesPedidoTxtNomeCliente.setInputType(typeTextFlagCapWords);
        binding.activityDetalhesPedidoFabAddMaterial.setVisibility(visible);
        binding.activityDetalhesPedidoFabAddMedida.setVisibility(visible);
    }
}