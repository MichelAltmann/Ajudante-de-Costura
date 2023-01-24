package com.example.ajudantedecostura.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.ajudantedecostura.R;
import com.example.ajudantedecostura.databinding.ActivityHomeBinding;
import com.example.ajudantedecostura.home.adapters.ViewPagerAdapter;
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
        ViewPagerAdapter adapter = new ViewPagerAdapter(HomeActivity.this);
        TabLayout tabLayout = binding.tabLayout;
        ViewPager2 viewPager2 = binding.viewpager;

        if (!getIntent().getExtras().isEmpty()){
            binding.activityHomeNomeCostureira.setText(getIntent().getStringExtra("nome"));
        }

        binding.viewpager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> {
//            tab.setText(name[position]);
            if (position == 0){
                tab.setIcon(R.drawable.ic_clientes);
            } else if (position == 1){
                tab.setIcon(R.drawable.ic_pedidos);
            }
        })).attach();

    }
}