package com.befj.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.befj.myapplication.Models.TipoDocumento;
import com.befj.myapplication.R;

import java.util.List;

public class TipoDocumentoAdapter extends RecyclerView.Adapter<TipoDocumentoAdapter.ViewHolder> {

    List<TipoDocumento> listaTipoDocumentos;
    Context contexto;

    public TipoDocumentoAdapter(List<TipoDocumento> listaTipoDocumentos, Context contexto) {
        this.listaTipoDocumentos = listaTipoDocumentos;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.tiposdocumentocard, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Accede al objeto TipoDocumento desde la lista
        TipoDocumento objaut = listaTipoDocumentos.get(position);

        // Establecer el texto en los TextView
        holder.rv_tiposdocumento.setText("ID Tipo Documento: " + objaut.getnom_tipo_doc());
        holder.rv_idtipodocumento.setText("Tipo Documento: " + objaut.getid_tipo_docum());
    }


    @Override
    public int getItemCount() {
        return listaTipoDocumentos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView rv_idtipodocumento, rv_tiposdocumento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_idtipodocumento = itemView.findViewById(R.id.rv_idtipodocumento);
            rv_tiposdocumento = itemView.findViewById(R.id.rv_tiposdocumento);
        }
    }
}
