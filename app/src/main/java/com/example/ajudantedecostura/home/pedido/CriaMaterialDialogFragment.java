package com.example.ajudantedecostura.home.pedido;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ajudantedecostura.databinding.DialogCriaMaterialBinding;

import java.util.ArrayList;
import java.util.UUID;

import modelDominio.Material;

public class CriaMaterialDialogFragment extends DialogFragment {

    private DialogCriaMaterialBinding binding;
    ArrayList<Material> listaMaterial;

    public CriaMaterialDialogFragment(ArrayList<Material> listaMaterial) {
        this.listaMaterial = listaMaterial;
    }

    public interface CriaMaterialDialogListener {
        public void onDialogMaterialCriar(ArrayList<Material> materiais);
    }

    CriaMaterialDialogListener listener;

    public static CriaMaterialDialogFragment newInstance(String titulo, ArrayList<Material> listaMaterial) {
        CriaMaterialDialogFragment fragment = new CriaMaterialDialogFragment(listaMaterial);
        Bundle args = new Bundle();
        args.putString("titulo", titulo);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogCriaMaterialBinding.inflate(inflater);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText etComprimento = binding.dialogCriaMaterialComprimento;
        EditText etNome = binding.dialogCriaMaterialNome;
        EditText etLargura = binding.dialogCriaMaterialLargura;
        EditText etPreco = binding.dialogCriaMaterialPreco;


        getDialog().setTitle("Adicionar Medida");

        listener = (CriaMaterialDialogListener) getContext();

        binding.dialogCriaMaterialBtnCriar.setOnClickListener(v -> {
            Float preco = 0f;
            Float largura = 0f;
            Float comprimento = 0f;
            if (!etNome.getText().toString().equals("")) {
                String nome = etNome.getText().toString();

                if (!etComprimento.getText().toString().equals("")) {
                    comprimento = Float.parseFloat(etComprimento.getText().toString());
                }

                if (!etLargura.getText().toString().equals("")) {
                    largura = Float.parseFloat(etLargura.getText().toString());
                }

                if (!etPreco.getText().toString().equals("")) {
                    preco = Float.parseFloat(etPreco.getText().toString());
                }

                String idMaterial = UUID.randomUUID().toString();

                Material material = new Material(idMaterial, nome, comprimento, largura, preco);
                listaMaterial.add(material);

                listener.onDialogMaterialCriar(listaMaterial);

                dismiss();
            } else {
                etNome.requestFocus();
                etNome.setError("Erro: Campo obrigatório.");
            }

        });

        binding.dialogCriaMaterialBtnCancelar.setOnClickListener(v -> {
            dismiss();
        });

    }
}
