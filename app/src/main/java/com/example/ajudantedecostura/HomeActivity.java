package com.example.ajudantedecostura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.ajudantedecostura.databinding.ActivityHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

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

        binding.viewpager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> {
            tab.setText(name[position]);
        })).attach();

    }
}