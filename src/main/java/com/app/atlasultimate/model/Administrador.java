package com.app.atlasultimate.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "administrador")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {
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

    @Column(name = "num_telefono")
    private String numTelefono;

    @Column(name = "email")
    private String email;

    @Column(name = "contrasena")
    private String contrasena;




    @OneToMany(fetch = FetchType.LAZY, mappedBy = "administrador")
    public Set<Hotel> hoteles;

    public Administrador(String nombre, String apellidos, String dni, String numTelefono, String email, String contrasena) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.numTelefono = numTelefono;
        this.email = email;
        this.contrasena = contrasena;
    }
}
