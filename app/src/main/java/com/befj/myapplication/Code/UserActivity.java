package com.befj.myapplication.Code;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import com.befj.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserActivity extends AppCompatActivity {

    FragmentContainerView fcv_activityuser;
    BottomNavigationView bnv_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Inicializar el FragmentContainerView y BottomNavigationView
        fcv_activityuser = findViewById(R.id.fcv_activityuser);
        bnv_menu = findViewById(R.id.bnv_menuapp);

        // Reemplazar el fragmento inicial
        reemplazarFragment(new TipodocumentoFragment());

        // Configurar la navegación con BottomNavigationView
        bnv_menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.mnit_tipodocumentos) {
                    reemplazarFragment(new TipodocumentoFragment()); // Mostrar el fragmento de tipos de documento
                } else if (item.getItemId() == R.id.mnit_color1) {
                    reemplazarFragment(new Color1Fragment()); // Fragmento de color1
                } else if (item.getItemId() == R.id.mnit_color2) {
                    reemplazarFragment(new Color2Fragment()); // Fragmento de color2
                }
                return true;
            }
        });
    }

    // Método para reemplazar el fragmento
    public void reemplazarFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fcv_activityuser, fragment);
        transaction.commit();
    }
}
