package com.example.ajudantedecostura.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.example.ajudantedecostura.R;
import com.example.ajudantedecostura.databinding.ActivityHomeBinding;
import com.example.ajudantedecostura.home.adapter.ViewPagerAdapter;
import com.example.ajudantedecostura.home.cliente.CadastroClienteActivity;
import com.example.ajudantedecostura.home.pedido.CadastroPedidoActivity;
import com.example.ajudantedecostura.login.LoginActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    String[] name = {"Clientes","Pedidos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // inicializa campos
        ViewPagerAdapter adapter = new ViewPagerAdapter(HomeActivity.this);
        TabLayout tabLayout = binding.tabLayout;
        ViewPager2 viewPager2 = binding.viewpager;


        // configura adapter responsável por mostrar as páginas do tablayout
        binding.viewpager.setAdapter(adapter);

        // cria o tabLayoutMediator, que edita os dados base de cada tab, aqui sendo os ícones e suas cores
        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> {
            if (position == 0){
                tab.setIcon(R.drawable.ic_clientes);
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.roxo_dark);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            } else if (position == 1){
                tab.setIcon(R.drawable.ic_pedidos);
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.rosa);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }
        })).attach();

        // modifica fab e cores das tabs dependendo em qual aba foi selecionada
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                        binding.fragmentHomeFabAdiciona.setImageDrawable(getResources().getDrawable(R.drawable.ic_clientes));
                        binding.activityHomeLabel.setText("Clientes");
                } else {
                    binding.fragmentHomeFabAdiciona.setImageDrawable(getResources().getDrawable(R.drawable.ic_pedidos));
                    binding.activityHomeLabel.setText("Pedidos");

                }
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.roxo_dark);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.rosa);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // botão fab responsável pela navegação às páginas de cadastro de cliente e pedido
        binding.fragmentHomeFabAdiciona.setOnClickListener(v -> {
            if (binding.viewpager.getCurrentItem() == 0){
                Intent intent = new Intent(this, CadastroClienteActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, CadastroPedidoActivity.class);
                startActivity(intent);
            }
        });

        binding.activityHomeFabPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(this, PerfilCostureiraActivity.class);
            startActivity(intent);
        });

    }
}