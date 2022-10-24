package com.app.atlasultimate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private Integer id;

    @Column(name = "nombre", length = 150)
    private String nombre;

    @Column(name = "apellido", length = 150)
    private String apellido;

    @Column(name = "dni", length = 150)
    private String dni;

    @Column(name = "rol", length = 150)
    private String rol;

    @Column(name = "telefono", length = 150)
    private String telefono;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "contrasena", length = 250)
    private String contrasena;


    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id_usuario")
    public Set<Hotel> hotel;

    @JsonBackReference
    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    private Set<Registro> registro;

    @JsonBackReference
    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    private Set<Review> review;

    public Usuario(String nombre, String apellido, String dni, String rol, String telefono, String email, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.rol = rol;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
    }
}