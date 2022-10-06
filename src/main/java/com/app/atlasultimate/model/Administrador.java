package com.app.atlasultimate.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "administrador", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
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

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "telefono")
    private String NumTelefono;

    @Column(name = "email")
    private String Email;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "administrador")
    public Set<Hotel> Hoteles;

    public Administrador(String nombre, String apellidos, String dni, String contrasena, String numTelefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.contrasena = contrasena;
        NumTelefono = numTelefono;
        Email = email;
    }
}
