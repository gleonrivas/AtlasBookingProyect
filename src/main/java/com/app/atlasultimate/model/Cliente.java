package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@EqualsAndHashCode
public class Cliente extends Usuario {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "dni")
    private String dni;

    @Column(name = "contraseña")
    private String contrasena;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    public Cliente(Integer id, String nombre, String apellidos, String dni, String contrasena, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.email = email;
    }
    @OneToMany(mappedBy = "cliente")
    private Set<Reserva> reservas;

    @OneToMany(mappedBy = "cliente")
    private Set<Review> reviews;
    public Cliente() {

    }
}
