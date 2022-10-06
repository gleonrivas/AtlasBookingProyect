package com.app.atlasultimate.controller.DTO;

import com.app.atlasultimate.model.Hotel;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

public class AdministradorRegistroDTO {
    private Integer id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String contrasena;

    public AdministradorRegistroDTO(String email) {
        Email = email;
    }

    public AdministradorRegistroDTO() {
    }

    public AdministradorRegistroDTO(Integer id, String nombre, String apellidos, String dni, String contrasena, String numTelefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.contrasena = contrasena;
        NumTelefono = numTelefono;
        Email = email;
    }

    public AdministradorRegistroDTO(String nombre, String apellidos, String dni, String contrasena, String numTelefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.contrasena = contrasena;
        NumTelefono = numTelefono;
        Email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    private String NumTelefono;
    private String Email;

}
