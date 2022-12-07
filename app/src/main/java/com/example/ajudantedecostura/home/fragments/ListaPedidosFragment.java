package com.example.ajudantedecostura.home.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ajudantedecostura.databinding.FragmentListaPedidosBinding;

public class ListaPedidosFragment extends Fragment {

    FragmentListaPedidosBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentListaPedidosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}