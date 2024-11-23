package com.befj.myapplication.Code;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.befj.myapplication.Models.TipoDocumento;
import com.befj.myapplication.R;
import com.befj.myapplication.Retrofit.RetrofitAdapter;
import com.befj.myapplication.Adapters.TipoDocumentoAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TipoDocumentoAdapter tipoDocumentoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        recyclerView = findViewById(R.id.recyclerView);

        // Hacer la llamada para obtener los tipos de documento
        obtenerTiposDocumento();

        // Configurar BottomNavigationView
        BottomNavigationView bnv_menu = findViewById(R.id.bnv_menuapp);
        bnv_menu.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.mnit_clientes) {
                    obtenerTiposDocumento();  // Vuelve a cargar la lista de tipos de documento
                } else if (item.getItemId() == R.id.mnit_color1) {
                    // Lógica para el ítem de color1
                    Log.i("Menu", "Color1 seleccionados");
                    reemplazarFragment(new Color1Fragment()); // Fragmento color1
                } else if (item.getItemId() == R.id.mnit_color2) {
                    // Lógica para el ítem de color2
                    Log.i("Menu", "Color2 seleccionados");
                    reemplazarFragment(new Color2Fragment()); // Fragmento color2
                }
                return true;
            }
        });
    }

    // Método para obtener los tipos de documento desde el servidor
    public void obtenerTiposDocumento() {
        Call<List<TipoDocumento>> tipoDocumentoCall = RetrofitAdapter.getservices().getTiposDocumento();

        tipoDocumentoCall.enqueue(new Callback<List<TipoDocumento>>() {
            @Override
            public void onResponse(Call<List<TipoDocumento>> call, Response<List<TipoDocumento>> response) {
                if (response.isSuccessful()) {
                    List<TipoDocumento> tiposDocumento = response.body();
                    // Actualizar el RecyclerView con la lista de tipos de documento
                    tipoDocumentoAdapter = new TipoDocumentoAdapter(tiposDocumento, UserActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(UserActivity.this));
                    recyclerView.setAdapter(tipoDocumentoAdapter);
                } else {
                    Toast.makeText(UserActivity.this, "Error al obtener los tipos de documento", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TipoDocumento>> call, Throwable t) {
                Toast.makeText(UserActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para reemplazar el fragmento actual por uno nuevo
    private void reemplazarFragment(androidx.fragment.app.Fragment fragment) {
        androidx.fragment.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fcv_activityuser, fragment);
        transaction.addToBackStack(null); // Agregar el fragmento a la pila de retroceso
        transaction.commit();
    }
}
