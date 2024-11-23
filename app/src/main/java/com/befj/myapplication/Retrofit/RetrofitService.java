package com.befj.myapplication.Retrofit;

import com.befj.myapplication.Models.Cliente;
import com.befj.myapplication.Models.TokenResponse;
import com.befj.myapplication.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface RetrofitService {

  // Obtener token de autenticación (login)
    @POST("login/")
    Call<TokenResponse> loginUser(@Body User user);

    // Obtener clientes con autenticación de token
    @GET("clientes/")
    Call<List<Cliente>> getClientes();


}

