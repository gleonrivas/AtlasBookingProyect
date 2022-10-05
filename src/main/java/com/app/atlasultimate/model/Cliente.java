package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@EqualsAndHashCode
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "dni")
    private String dni;

    @Column(name = "contrasena")
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

    public Cliente() {

    }
}
