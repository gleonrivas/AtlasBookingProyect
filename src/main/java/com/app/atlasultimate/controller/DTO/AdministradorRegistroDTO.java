package com.app.atlasultimate.controller.DTO;

import com.app.atlasultimate.model.Administrador;
import com.app.atlasultimate.model.Hotel;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

public class AdministradorRegistroDTO {

    private String nombre;
    private String apellidos;
    private String dni;
    private String numTelefono;
    private String email;
    private String contrasena;




    public AdministradorRegistroDTO(String email) {
        email = email;
    }

    public AdministradorRegistroDTO() {
    }

    public AdministradorRegistroDTO( String nombre, String apellidos, String dni, String contrasena, String numTelefono, String email) {

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni=dni;
        this.contrasena = contrasena;
        this.numTelefono = numTelefono;
        this.email = email;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Administrador convertir(AdministradorRegistroDTO administradorRegistroDTO){
        Administrador administrador = new Administrador();
        administrador.setNombre(administradorRegistroDTO.getNombre());
        administrador.setApellidos(administradorRegistroDTO.getApellidos());
        administrador.setDni(administradorRegistroDTO.getDni());
        administrador.setNumTelefono(administradorRegistroDTO.getNumTelefono());
        administrador.setEmail(administradorRegistroDTO.getEmail());
        administrador.setContrasena(administradorRegistroDTO.getContrasena());

        return administrador;
    }


}
