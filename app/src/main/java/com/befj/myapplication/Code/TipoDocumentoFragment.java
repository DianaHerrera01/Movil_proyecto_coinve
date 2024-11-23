package com.befj.myapplication.Code;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.befj.myapplication.Models.TipoDocumento;
import com.befj.myapplication.Adapters.TipoDocumentoAdapter;
import com.befj.myapplication.R;
import com.befj.myapplication.Retrofit.RetrofitAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TipoDocumentoFragment extends Fragment {

    RecyclerView recyclerView;
    TipoDocumentoAdapter tipoDocumentoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos el layout del fragmento
        View root = inflater.inflate(R.layout.fragment_tipodocumento, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);

        // Hacer la llamada para obtener los tipos de documento
        obtenerTiposDocumento();

        return root;
    }

    // Método para obtener los tipos de documento desde el servidor
    private void obtenerTiposDocumento() {
        // Crear el objeto Call para la consulta sin autenticación
        Call<List<TipoDocumento>> tipoDocumentoCall = RetrofitAdapter.getservices().getTiposDocumento();

        tipoDocumentoCall.enqueue(new Callback<List<TipoDocumento>>() {
            @Override
            public void onResponse(Call<List<TipoDocumento>> call, Response<List<TipoDocumento>> response) {
                if (response.isSuccessful()) {
                    List<TipoDocumento> tiposDocumento = response.body();
                    // Actualizar el RecyclerView con la lista de tipos de documento
                    tipoDocumentoAdapter = new TipoDocumentoAdapter(tiposDocumento, getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(tipoDocumentoAdapter);
                } else {
                    Toast.makeText(getContext(), "Error al obtener los tipos de documento", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TipoDocumento>> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
