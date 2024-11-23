package com.befj.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.befj.myapplication.Models.Cliente;
import com.befj.myapplication.R;
import java.util.List;

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ViewHolder> {

    List<Cliente> _listaClientes;
    Context _contexto;

    public ClientesAdapter(List<Cliente> listaClientes, Context contexto) {
        this._listaClientes = listaClientes;
        this._contexto = contexto;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cliente cliente = _listaClientes.get(position);

        // Mostrar los datos del cliente
        holder.tvNombreCliente.setText("Nombre: " + cliente.getNombre_cliente());
        holder.tvApellidosCliente.setText("Apellidos: " + cliente.getApellidos_cliente());
        holder.tvCorreoCliente.setText("Correo: " + cliente.getCorreo());
        holder.tvDocumentoCliente.setText("Documento: " + cliente.getDocumento_cli());
        holder.tvTelefonoCliente.setText("Teléfono: " + cliente.getTelefono());
    }

    @Override
    public int getItemCount() {
        return _listaClientes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreCliente, tvApellidosCliente, tvCorreoCliente, tvDocumentoCliente, tvTelefonoCliente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inicializar los TextViews para mostrar los datos
            tvNombreCliente = itemView.findViewById(R.id.tvNombreCliente);
            tvApellidosCliente = itemView.findViewById(R.id.tvApellidosCliente);
            tvCorreoCliente = itemView.findViewById(R.id.tvCorreoCliente);
            tvDocumentoCliente = itemView.findViewById(R.id.tvDocumentoCliente);
            tvTelefonoCliente = itemView.findViewById(R.id.tvTelefonoCliente);
        }
    }
}
