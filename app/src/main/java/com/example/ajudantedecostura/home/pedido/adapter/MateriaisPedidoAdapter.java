package com.example.ajudantedecostura.home.pedido.adapter;

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

    // cria o ItemClickListener
    public interface OnItemClickListener {
        public void onItemClick(View view, int position, int tipo);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerMedidasMateriaisItemBinding binding;

        public ViewHolder(RecyclerMedidasMateriaisItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    // método construtor do adapter, recebendo a lista e o tipo, se é material ou uma medida
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
        // faz o bind do nome dependendo do tipo, se é material ou medida
        if (tipo == tipoMaterial) {
            holder.binding.recyclerMedidasMateriaisItemTxt.setText(String.valueOf(lista[position]));
        } else if (tipo == tipoMedida) {
            holder.binding.recyclerMedidasMateriaisItemTxt.setText(String.valueOf(lista[position]));
        }
        // check se existe um clickListener ativo
        if (itemClickListener != null) {
            // caso exista, o click vai volta para a activity com os dados de onde foi
            holder.itemView.setOnClickListener(v -> {
                if (tipo == tipoMaterial) {
                    itemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition(), tipoMaterial);
                } else if (tipo == tipoMedida) {
                    itemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition(), tipoMedida);
                }
            });
        }
    }

    // número de items dependendo de qual é a lista
    @Override
    public int getItemCount() {
        if (tipo == 1) {
            return lista.length;
        } else {
            return lista.length;
        }
    }
}
