package com.befj.myapplication.Models;

public class TipoDocumento {
    private int id_tipo_docum;
    private String nom_tipo_doc;

    // Getters y setters
    public int getId_tipo_docum() {
        return id_tipo_docum;
    }

    public void setId_tipo_docum(int id_tipo_docum) {
        this.id_tipo_docum = id_tipo_docum;
    }

    public String getNom_tipo_doc() {
        return nom_tipo_doc;
    }

    public void setNom_tipo_doc(String nom_tipo_doc) {
        this.nom_tipo_doc = nom_tipo_doc;
    }
}

