package com.kelompok5.taskschedulerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kelompok5.taskschedulerapp.R.id;
import com.kelompok5.taskschedulerapp.R.layout;

public class NavBottom extends AppCompatActivity{

    Fragment fragment = null;
    FragmentManager fragmentManager;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_navbottom);

        loadFragment(new Hal_Dashboard ());

        BottomNavigationView bottomNavigationView = findViewById(id.nav_bottom);
        bottomNavigationView.setOnItemSelectedListener( item ->  {
                Fragment fragment;
                switch (item.getItemId()) {
                    case id.dashboard:
                        fragment = new Hal_Dashboard();
                        loadFragment(fragment);
                        break;
                    case id.tanggal:
                        fragment = new Hal_Tanggal();
                        loadFragment(fragment);
                        break;
                    case id.profil:
                        fragment = new Hal_Profil();
                        loadFragment(fragment);
                        break;
                }

                return true;
        });

    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction =
        getSupportFragmentManager().beginTransaction();
        transaction.replace(id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
