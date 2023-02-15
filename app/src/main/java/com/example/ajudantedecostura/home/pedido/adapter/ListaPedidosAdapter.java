package com.example.ajudantedecostura.home.pedido.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ajudantedecostura.R;
import com.example.ajudantedecostura.databinding.RecyclerPedidosItemBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import modelDominio.Pedido;

public class ListaPedidosAdapter extends RecyclerView.Adapter<ListaPedidosAdapter.ViewHolder> {

    private ArrayList<Pedido> listaPedidos;
    private PedidosOnClickListener itemClickListener;
    private Context context;
    final DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private RecyclerPedidosItemBinding binding;
        public ViewHolder(RecyclerPedidosItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public ListaPedidosAdapter(ArrayList<Pedido> pegaLista, PedidosOnClickListener itemClickListener, Context context) {
        this.listaPedidos = pegaLista;
        this.itemClickListener = itemClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaPedidosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListaPedidosAdapter.ViewHolder(RecyclerPedidosItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPedidosAdapter.ViewHolder holder, int position) {
        Pedido pedido = listaPedidos.get(position);
        holder.binding.pedidosItemTitulo.setText(String.valueOf(pedido.getTitulo()));
        holder.binding.pedidosItemNomeCliente.setText(pedido.getCliente().getNome());
        holder.binding.pedidosItemPrazoEntrega.setText(dataFormatada.format(pedido.getDataEntrega()));
        holder.binding.pedidosItemPreco.setText(pedido.getPreco().toString());

        byte[] imagem = pedido.getImagem();
        Bitmap bitmap = null;
        if (imagem != null){
            bitmap = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
        }

        Glide.with(context)
                .load(bitmap)
                .placeholder(R.drawable.splash_screen_loading)
                .into(holder.binding.pedidosItemImage);
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(v -> {
                    itemClickListener.onClickPedido(holder.itemView, holder.getAdapterPosition());
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public interface PedidosOnClickListener{
        public void onClickPedido(View view, int position);
    }
}
