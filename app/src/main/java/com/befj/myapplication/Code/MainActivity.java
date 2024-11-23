package com.befj.myapplication.Code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.befj.myapplication.Models.TokenResponse;
import com.befj.myapplication.Models.User;
import com.befj.myapplication.R;
import com.befj.myapplication.Retrofit.RetrofitAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText et_usuario, et_contraseña;
    Button btn_ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_usuario = findViewById(R.id.et_usuario);
        et_contraseña  = findViewById(R.id.et_contraseña);
        btn_ingresar = findViewById(R.id.btn_ingresar);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_usuario.getText().toString();
                String password = et_contraseña.getText().toString();
                iniciarsesion(username, password);
            }
        });
    }

    public void iniciarsesion(String username, String password) {
        User user = new User(username, password);
        Call<TokenResponse> loginUser = RetrofitAdapter.getservices().loginUser(user);

        loginUser.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    TokenResponse tokenResponse = response.body();
                    if (tokenResponse != null) {
                        // Muestra el mensaje de autenticación satisfactoria en lugar del token
                        Toast.makeText(MainActivity.this, "Autenticación satisfactoria.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Log.i("AuthResponse", "Error en la autenticación: " + response.errorBody());
                    Toast.makeText(MainActivity.this, "Error en la autenticación", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e("AuthFailure", "Error de conexión: " + t.getMessage(), t);
                Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}