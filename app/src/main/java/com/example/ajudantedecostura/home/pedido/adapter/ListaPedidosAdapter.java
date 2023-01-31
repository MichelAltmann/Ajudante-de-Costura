package com.example.ajudantedecostura.home.pedido.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ajudantedecostura.databinding.RecyclerPedidosItemBinding;

public class ListaPedidosAdapter extends RecyclerView.Adapter<ListaPedidosAdapter.ViewHolder> {

    private String[] lista;
    private PedidosOnClickListener itemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private RecyclerPedidosItemBinding binding;
        public ViewHolder(RecyclerPedidosItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public ListaPedidosAdapter(String[] pegaLista, PedidosOnClickListener itemClickListener) {
        this.lista = pegaLista;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ListaPedidosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListaPedidosAdapter.ViewHolder(RecyclerPedidosItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPedidosAdapter.ViewHolder holder, int position) {
        holder.binding.pedidosItemTitulo.setText(String.valueOf(lista[position]));

        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(v -> {
                    itemClickListener.onClickPedido(holder.itemView, holder.getAdapterPosition());
            });
        }
    }

    @Override
    public int getItemCount() {
        return lista.length;
    }

    public interface PedidosOnClickListener{
        public void onClickPedido(View view, int position);
    }
}
