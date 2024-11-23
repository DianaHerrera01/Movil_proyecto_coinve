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
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tipo_documento, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TipoDocumento tipoDocumento = listaTipoDocumentos.get(position);
        holder.tvTipoDocumento.setText("Tipo Documento: " + tipoDocumento.getNom_tipo_doc());
    }

    @Override
    public int getItemCount() {
        return listaTipoDocumentos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTipoDocumento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTipoDocumento = itemView.findViewById(R.id.tvTipoDocumento);
        }
    }
}
