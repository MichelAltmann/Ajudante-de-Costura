package com.example.ajudantedecostura.home.cliente;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ajudantedecostura.databinding.FragmentListaClientesBinding;
import com.example.ajudantedecostura.home.cliente.adapter.ListaClientesAdapter;

public class ListaClientesFragment extends Fragment {

    FragmentListaClientesBinding binding;
    String[] lista = {"Pedro Müller","Michel Altmann","Lucas Santos","Guilherme Neis","Lucas Meyer","Alan Werner"};
    ListaClientesAdapter adapter = new ListaClientesAdapter(lista);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentListaClientesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fragmentListaClientesRecyclerview.setAdapter(adapter);
    }
}