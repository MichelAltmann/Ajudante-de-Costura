package com.example.ajudantedecostura;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ajudantedecostura.databinding.FragmentListaClientesBinding;

public class ListaClientesFragment extends Fragment {

    FragmentListaClientesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentListaClientesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}