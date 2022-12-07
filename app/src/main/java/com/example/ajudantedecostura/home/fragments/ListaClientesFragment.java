package com.example.ajudantedecostura.home.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.ajudantedecostura.databinding.FragmentListaClientesBinding;
import com.example.ajudantedecostura.home.adapters.ListaClientesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaClientesFragment extends Fragment {

    FragmentListaClientesBinding binding;
    Integer[] lista = {1,2,3,4,5,6};
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