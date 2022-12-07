package com.example.ajudantedecostura.home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ajudantedecostura.databinding.FragmentListaPedidosBinding;
import com.example.ajudantedecostura.home.adapters.ListaPedidosAdapter;

public class ListaPedidosFragment extends Fragment {

    FragmentListaPedidosBinding binding;
    String[] lista = {"Camiseta Confortável", "Calça chave", "Moletom branco", "Cachecol vermelho", "Camisa social"};
    ListaPedidosAdapter adapter = new ListaPedidosAdapter(lista);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentListaPedidosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fragmentListaPedidosRecyclerview.setAdapter(adapter);
    }
}