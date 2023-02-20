package com.example.ajudantedecostura.home.cliente.adapter;

import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ajudantedecostura.databinding.ActivityDetalhesClienteBinding;
import com.example.ajudantedecostura.databinding.RecyclerMedidasMateriaisItemBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import modelDominio.Pedido;

public class ListaPedidosClienteAdapter extends RecyclerView.Adapter<ListaPedidosClienteAdapter.ViewHolder> {

    OnPedidoItemClickListener pedidoItemClickListener;
    ArrayList<Pedido> pedidos = new ArrayList<>();
    final DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

    public interface OnPedidoItemClickListener {
        public void onClickPedido(View view, int position);
    }

    public ListaPedidosClienteAdapter(ArrayList<Pedido> pedidos, OnPedidoItemClickListener pedidoItemClickListener){
        this.pedidos = pedidos;
        this.pedidoItemClickListener = pedidoItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerMedidasMateriaisItemBinding binding;

        public ViewHolder(RecyclerMedidasMateriaisItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RecyclerMedidasMateriaisItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.recyclerMedidasMateriaisItemTxt.setText(pedidos.get(position).getTitulo());
        if (pedidos.get(position).getDataEntrega() != null){
            holder.binding.recyclerMedidasMateriaisItem2Txt.setText(dataFormatada.format(pedidos.get(position).getDataEntrega()));
        }
    }


    @Override
    public int getItemCount() {
        return pedidos.size();
    }
}
