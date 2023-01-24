package com.example.ajudantedecostura.home.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ajudantedecostura.databinding.RecyclerMedidasMateriaisItemBinding;

public class MateriaisPedidoAdapter extends RecyclerView.Adapter<MateriaisPedidoAdapter.ViewHolder> {

    private String[] lista;
    private OnItemClickListener itemClickListener;
    private int tipo, tipoMaterial = 1, tipoMedida = 2; // 1 == material 2 == medida

    public interface OnItemClickListener {
        public void onItemClick(View view, int position, int tipo);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private RecyclerMedidasMateriaisItemBinding binding;
        public ViewHolder(RecyclerMedidasMateriaisItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public MateriaisPedidoAdapter(String[] pegaLista, int pegaTipo, OnItemClickListener itemClickListener) {
        lista = pegaLista;
        tipo = pegaTipo;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MateriaisPedidoAdapter.ViewHolder(RecyclerMedidasMateriaisItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (tipo == tipoMaterial) {
            holder.binding.recyclerMedidasMateriaisItemTxt.setText(String.valueOf(lista[position]));
        } else if (tipo == tipoMedida){
            holder.binding.recyclerMedidasMateriaisItemTxt.setText(String.valueOf(lista[position]));
        }

        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tipo == tipoMaterial){
                        itemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition(),tipoMaterial);
                    } else if (tipo == tipoMedida){
                        itemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition(),tipoMedida);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (tipo == 1){
            return lista.length;
        } else {
            return lista.length;
        }
    }
}
