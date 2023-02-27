package com.example.ajudantedecostura.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ajudantedecostura.home.cliente.ListaClientesFragment;
import com.example.ajudantedecostura.home.pedido.ListaPedidosFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override

    // cria fragment dependendo da posição recebida e o retorna para a activity
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new ListaClientesFragment();
            case 1: return new ListaPedidosFragment();
        }
        return new ListaClientesFragment();
    }

    // número fixo de items(fragments)
    @Override
    public int getItemCount() {
        return 2;
    }
}
