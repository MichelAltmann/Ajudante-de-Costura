package com.example.ajudantedecostura.home.cliente.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ajudantedecostura.databinding.RecyclerClientesItemBinding;

import java.util.ArrayList;

import modelDominio.Cliente;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ViewHolder> {

    private ArrayList<Cliente> lista;

    public static class ViewHolder extends RecyclerView.ViewHolder{
    private RecyclerClientesItemBinding binding;
        public ViewHolder(RecyclerClientesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public ListaClientesAdapter(ArrayList<Cliente> pegaLista) {
        this.lista = pegaLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RecyclerClientesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.clientesItemNomeCliente.setText(lista.get(position).getNome());
        holder.binding.clientesItemNumeroCelular.setText(lista.get(position).getTelefone());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
