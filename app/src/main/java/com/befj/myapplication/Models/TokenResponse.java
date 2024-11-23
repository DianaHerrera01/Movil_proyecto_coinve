package com.befj.myapplication.Models;

public class TokenResponse {
    private String access;  // Token de acceso
    private String mensaje; // Mensaje personalizado

    // Constructor
    public TokenResponse(String access, String mensaje) {
        this.access = access;
        this.mensaje = mensaje;
    }

    // Getters y setters
    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


}




