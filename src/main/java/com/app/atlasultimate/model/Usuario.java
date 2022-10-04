package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;


@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public class Usuario {
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

    @Column(name = "num_telefono")
    private String NumTelefono;

    @Column(name = "email")
    private String Email;
}
