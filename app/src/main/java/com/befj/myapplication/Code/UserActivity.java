package com.befj.myapplication.Code;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.befj.myapplication.Models.Cliente;
import com.befj.myapplication.R;
import com.befj.myapplication.Retrofit.RetrofitAdapter;
import com.befj.myapplication.Adapters.ClientesAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ClientesAdapter clientesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        recyclerView = findViewById(R.id.recyclerView);

        // Hacer la llamada para obtener los clientes
        obtenerClientes();

        // Configurar BottomNavigationView
        BottomNavigationView bnv_menu = findViewById(R.id.bnv_menuapp);
        bnv_menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Aquí manejamos las acciones para cada item del menú
                if (item.getItemId() == R.id.mnit_clientes) {
                    // Lógica para mostrar la lista de clientes
                    Log.i("Menu", "Clientes seleccionados");
                    obtenerClientes();  // Vuelve a cargar la lista de clientes
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

    // Método para reemplazar el fragmento actual por uno nuevo
    private void reemplazarFragment(androidx.fragment.app.Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fcv_activityuser, fragment);
        transaction.addToBackStack(null); // Agregar el fragmento a la pila de retroceso
        transaction.commit();
    }


    // Método para obtener los clientes desde el servidor
    public void obtenerClientes() {
        // Crear el objeto Call para la consulta sin autenticación
        Call<List<Cliente>> clienteCall = RetrofitAdapter.getservices().getClientes();

        clienteCall.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful()) {
                    List<Cliente> clientes = response.body();
                    // Actualizar el RecyclerView con la lista de clientes
                    clientesAdapter = new ClientesAdapter(clientes, UserActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(UserActivity.this));
                    recyclerView.setAdapter(clientesAdapter);
                } else {
                    Toast.makeText(UserActivity.this, "Error al obtener los clientes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Toast.makeText(UserActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
