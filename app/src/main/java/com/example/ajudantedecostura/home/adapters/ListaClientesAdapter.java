package com.example.ajudantedecostura.home.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ajudantedecostura.databinding.RecyclerClientesItemBinding;

import java.util.List;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ViewHolder> {

    private String[] lista;

    public static class ViewHolder extends RecyclerView.ViewHolder{
    private RecyclerClientesItemBinding binding;
        public ViewHolder(RecyclerClientesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public ListaClientesAdapter(String[] pegaLista) {
        this.lista = pegaLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RecyclerClientesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.clientesItemNomeCliente.setText(String.valueOf(lista[position]));
    }

    @Override
    public int getItemCount() {
        return lista.length;
    }
}
