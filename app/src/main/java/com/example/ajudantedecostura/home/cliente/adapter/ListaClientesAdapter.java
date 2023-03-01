package com.example.ajudantedecostura.home.cliente.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ajudantedecostura.databinding.RecyclerClientesItemBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import modelDominio.Cliente;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ViewHolder> {

    private ArrayList<Cliente> lista;
    private ClientesOnClickListener itemClickListener;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerClientesItemBinding binding;

        public ViewHolder(RecyclerClientesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ClientesOnClickListener{
        public void onClickCliente(View view, int position);
    }


    public ListaClientesAdapter(ArrayList<Cliente> pegaLista, ClientesOnClickListener itemClickListener) {
        this.lista = pegaLista;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RecyclerClientesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.clientesItemNomeCliente.setText(lista.get(position).getNome());

        if (lista.get(position).getTelefone() != null){
            holder.binding.clientesItemNumeroCelular.setText("Telefone: " + lista.get(position).getTelefone());
        }

        if (lista.get(position).getDataNascimento() != null){
            holder.binding.clientesItemEmail.setText(lista.get(position).getEmail());
        }

        byte[] imagem = lista.get(position).getImagem();
        if (imagem != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
            holder.binding.clientesItemImage.setImageBitmap(bitmap);
        }

        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(v -> {
                itemClickListener.onClickCliente(holder.itemView, holder.getAdapterPosition());
            });
        }


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
