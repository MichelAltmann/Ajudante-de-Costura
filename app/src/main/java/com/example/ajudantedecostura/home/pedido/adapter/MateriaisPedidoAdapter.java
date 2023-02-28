package com.example.ajudantedecostura.home.pedido.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ajudantedecostura.databinding.RecyclerMedidasMateriaisItemBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import modelDominio.Material;

public class MateriaisPedidoAdapter extends RecyclerView.Adapter<MateriaisPedidoAdapter.ViewHolder> {

    private ArrayList<Material> lista;
    private OnMateriaisItemClickListener itemClickListener;
    private NumberFormat formataPreco = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));


    // cria o ItemClickListener
    public interface OnMateriaisItemClickListener {
        public void onItemClick(View view, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerMedidasMateriaisItemBinding binding;

        public ViewHolder(RecyclerMedidasMateriaisItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

    // método construtor do adapter, recebendo a lista e o tipo, se é material ou uma medida
    public MateriaisPedidoAdapter(ArrayList<Material> pegaLista, OnMateriaisItemClickListener itemClickListener) {
        lista = pegaLista;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MateriaisPedidoAdapter.ViewHolder(RecyclerMedidasMateriaisItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        formataPreco.setCurrency(Currency.getInstance("BRL"));
        // faz o bind do nome dependendo do tipo, se é material ou medida
        holder.binding.recyclerMedidasMateriaisItemTxt.setText(String.valueOf(lista.get(position).getTipo()));
        holder.binding.recyclerMedidasMateriaisItem2Txt.setText(String.valueOf(formataPreco.format(lista.get(position).getPreco())));
        // check se existe um clickListener ativo
        if (itemClickListener != null) {
            // caso exista, o click vai volta para a activity com os dados de onde foi
            holder.itemView.setOnClickListener(v -> {
                    itemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition());
            });
        }
    }

    // número de items dependendo de qual é a lista
    @Override
    public int getItemCount() {
            return lista.size();
    }
}
