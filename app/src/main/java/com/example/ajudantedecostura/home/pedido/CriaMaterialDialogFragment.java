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

            if (!etNome.getText().toString().equals("")) {
                if (!etComprimento.getText().toString().equals("")) {
                    if (!etLargura.getText().toString().equals("")) {
                        if (!etPreco.getText().toString().equals("")) {

                            String nome = etNome.getText().toString();

                            Float comprimento = Float.parseFloat(etComprimento.getText().toString()),
                                    largura = Float.parseFloat(etLargura.getText().toString()),
                                    preco = Float.parseFloat(etPreco.getText().toString());

                            Material material = new Material(nome, comprimento, largura, preco);
                            listaMaterial.add(material);

                            listener.onDialogMaterialCriar(listaMaterial);

                            dismiss();
                        } else {
                            etPreco.requestFocus();
                            etPreco.setError("Erro: campo obrigatório.");
                        }
                    } else {
                        etLargura.requestFocus();
                        etLargura.setError("Erro: Campo obrigatório.");
                    }
                } else {
                    etComprimento.requestFocus();
                    etComprimento.setError("Erro: Campo obrigatório.");
                }
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
