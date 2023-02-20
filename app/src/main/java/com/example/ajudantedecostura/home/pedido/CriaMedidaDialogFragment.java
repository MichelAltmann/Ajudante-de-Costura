package com.example.ajudantedecostura.home.pedido;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ajudantedecostura.R;
import com.example.ajudantedecostura.databinding.DialogCriaMedidaBinding;

import java.util.ArrayList;

import modelDominio.Medida;

public class CriaMedidaDialogFragment extends DialogFragment {

    private DialogCriaMedidaBinding binding;

    ArrayList<Medida> listaMedidas;
    CriaMedidaDialogListener medidaListener;


    public CriaMedidaDialogFragment(ArrayList<Medida> medidas) {
        this.listaMedidas = medidas;
    }

    public interface CriaMedidaDialogListener {
        public void onDialogMedidaCriar(ArrayList<Medida> medidas);

    }

    public static CriaMedidaDialogFragment newInstance(String titulo, ArrayList<Medida> medidas) {
        CriaMedidaDialogFragment fragment = new CriaMedidaDialogFragment(medidas);
        Bundle args = new Bundle();
        args.putString("titulo", titulo);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogCriaMedidaBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        medidaListener = (CriaMedidaDialogListener) getContext();

        String[] medidas = getResources().getStringArray(R.array.lista_medidas);
        binding.dialogCriaMedidaSpinner.setAdapter(new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, medidas));

        binding.dialogCriaMedidaBtnCriar.setOnClickListener(v -> {
            Integer medidaSelecionada = binding.dialogCriaMedidaSpinner.getSelectedItemPosition();
            if (medidaSelecionada != 0) {
                if (!binding.dialogCriaMedida.getText().toString().equals("")) {
                    String nomeMedida = (String) binding.dialogCriaMedidaSpinner.getSelectedItem();
                    Medida medida = new Medida(medidaSelecionada, nomeMedida, Float.parseFloat(binding.dialogCriaMedida.getText().toString()));


                    for (int i = 0; i < listaMedidas.size(); i++) {
                        if (listaMedidas.get(i).getPos() == medida.getPos()) {
                            listaMedidas.remove(i);
                        }
                    }


                    listaMedidas.add(medida);
                    medidaListener.onDialogMedidaCriar(listaMedidas);
                    dismiss();
                } else {
                    binding.dialogCriaMedida.requestFocus();
                    binding.dialogCriaMedida.setError("Erro: Informe o tamanho da medida.");
                }
            } else {
                binding.dialogCriaMedidaSpinner.requestFocus();
                Toast.makeText(getContext(), "Selecione uma medida válida.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.dialogCriaMedidaBtnCancelar.setOnClickListener(v -> {
            dismiss();
        });
    }
}
