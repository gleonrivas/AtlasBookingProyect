package com.app.atlasultimate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Getter
@Setter
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
    @Enumerated(value = EnumType.STRING)
    private Rol rol;

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

    public Usuario(String nombre, String apellido, String dni, Rol rol, String telefono, String email, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.rol = rol;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
    }

    public Usuario(Integer id, String nombre, String apellido, String dni, Rol rol, String telefono, String email, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.rol = rol;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
    }

    public Usuario() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(getId(), usuario.getId()) && Objects.equals(getNombre(), usuario.getNombre()) && Objects.equals(getApellido(), usuario.getApellido()) && Objects.equals(getDni(), usuario.getDni()) && getRol() == usuario.getRol() && Objects.equals(getTelefono(), usuario.getTelefono()) && Objects.equals(getEmail(), usuario.getEmail()) && Objects.equals(getContrasena(), usuario.getContrasena()) && Objects.equals(getHotel(), usuario.getHotel()) && Objects.equals(getRegistro(), usuario.getRegistro()) && Objects.equals(getReview(), usuario.getReview());
    }


}
