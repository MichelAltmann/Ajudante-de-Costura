package com.example.ajudantedecostura.home.pedido.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ajudantedecostura.databinding.RecyclerMedidasMateriaisItemBinding;

import java.util.ArrayList;

import modelDominio.Material;
import modelDominio.Medidas;

public class MedidasPedidoAdapter extends RecyclerView.Adapter<MedidasPedidoAdapter.ViewHolder> {

    ArrayList<Medidas> lista;

    private OnMedidaItemClickListener itemClickListener;

    public interface OnMedidaItemClickListener {
        public void onMedidaItemClick(View view, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerMedidasMateriaisItemBinding binding;

        public ViewHolder(RecyclerMedidasMateriaisItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public MedidasPedidoAdapter(ArrayList<Medidas> pegaLista, OnMedidaItemClickListener itemClickListener){
        lista = pegaLista;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MedidasPedidoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedidasPedidoAdapter.ViewHolder(RecyclerMedidasMateriaisItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.recyclerMedidasMateriaisItemTxt.setText(String.valueOf(lista.get(position)));

        holder.itemView.setOnClickListener( v -> {
            itemClickListener.onMedidaItemClick(holder.itemView, holder.getAdapterPosition());
        });
    }


    @Override
    public int getItemCount() {
        return lista.size();
    }
}
