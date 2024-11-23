package com.befj.myapplication.Code;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.befj.myapplication.Adapters.TipoDocumentoAdapter;
import com.befj.myapplication.Models.TipoDocumento;
import com.befj.myapplication.R;
import com.befj.myapplication.Retrofit.RetrofitAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TipodocumentoFragment extends Fragment {

    RecyclerView rv_tiposdocumento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View root = inflater.inflate(R.layout.fragment_tipodocumento, container, false);

        rv_tiposdocumento = root.findViewById(R.id.rv_tiposdocumento);
        rv_tiposdocumento.setLayoutManager(new LinearLayoutManager(root.getContext()));

        // Llamada a la API sin usar Token
        Call<List<TipoDocumento>> traerTiposDocumento = RetrofitAdapter.getservices().getTiposDocumento();

        traerTiposDocumento.enqueue(new Callback<List<TipoDocumento>>() {
            @Override
            public void onResponse(Call<List<TipoDocumento>> call, Response<List<TipoDocumento>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TipoDocumento> tiposDocumentoList = response.body();
                    TipoDocumentoAdapter adaptador = new TipoDocumentoAdapter(tiposDocumentoList, root.getContext());
                    rv_tiposdocumento.setAdapter(adaptador);
                } else {
                    Log.e("Response", "badresponse : " + response.raw() + response.errorBody());
                    Toast.makeText(getContext(), "Error al obtener los tipos de documento", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TipoDocumento>> call, Throwable t) {
                Log.e("Response", "onFailure: " + t.getCause() + t.getMessage());
                Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}
