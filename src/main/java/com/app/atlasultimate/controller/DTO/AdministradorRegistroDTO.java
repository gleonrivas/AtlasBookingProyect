package com.app.atlasultimate.controller.DTO;

import com.app.atlasultimate.model.Hotel;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

public class AdministradorRegistroDTO {

    private String nombre;
    private String apellidos;
    private String contrasena;
    private String NumTelefono;
    private String Email;



    public AdministradorRegistroDTO(String email) {
        Email = email;
    }

    public AdministradorRegistroDTO() {
    }

    public AdministradorRegistroDTO( String nombre, String apellidos, String contrasena, String numTelefono, String email) {

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasena = contrasena;
        NumTelefono = numTelefono;
        Email = email;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNumTelefono() {
        return NumTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        NumTelefono = numTelefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


}
