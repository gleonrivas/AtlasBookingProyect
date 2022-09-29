package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@EqualsAndHashCode
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
    private String DNICIF;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "email")
    private String Email;

}
