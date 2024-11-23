package com.befj.myapplication.Models;

public class Cliente {
    private int id_cliente;
    private String nombre_cliente;
    private String apellidos_cliente;
    private String correo;
    private int id_tipo_docum;  // Relación con TipoDocumento (solo el ID)
    private String documento_cli;
    private String telefono;

    // Getters y setters
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellidos_cliente() {
        return apellidos_cliente;
    }

    public void setApellidos_cliente(String apellidos_cliente) {
        this.apellidos_cliente = apellidos_cliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId_tipo_docum() {
        return id_tipo_docum;
    }

    public void setId_tipo_docum(int id_tipo_docum) {
        this.id_tipo_docum = id_tipo_docum;
    }

    public String getDocumento_cli() {
        return documento_cli;
    }

    public void setDocumento_cli(String documento_cli) {
        this.documento_cli = documento_cli;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}





