package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity (name="hotel")
@Table(name = "hotel")
@Getter
@Setter
@EqualsAndHashCode
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , length = 10)
    private Integer id;
    @Column(name = "nombre", length = 150)
    private String nombre;
    @Column(name = "pais", length = 150)
    private String pais;
    @Column(name = "ciudad", length = 150)
    private String ciudad;
    @Column(name = "direccion", length = 150)
    private String direccion;
    @Column(name = "estrellas", length = 1)
    private Integer estrellas;
    @Column(name = "telefono", length = 150)
    private Integer telefono;
    @Column(name = "email", length = 150)
    private String email;
    @Column(name = "cancelacion_g")
    private Boolean cancelacion_g;

    @Column(name = "wifi")
    private Boolean wifi;

    @Column(name = "mascotas")
    private Boolean mascotas;

    @Column(name = "multilengua")
    private Boolean multilengua;

    @Column(name = "accesibilidad")
    private Boolean accesibilidad;

    @Column(name = "s_habitacion")
    private Boolean s_habitacion;

    @Column(name = "hc_recepcion")
    private Time hc_recepcion;

    @Column(name = "hf_recepcion")
    private Time hf_recepcion;

    @Column(name = "s_transporte")
    private Boolean s_transporte;

    @Column(name = "tours")
    private Boolean tours;

    @Column(name = "comedor")
    private Boolean comedor;

    @Column(name = "espectaculos")
    private Boolean espectaculos;

    @Column(name = "patio")
    private Boolean patio;

    @Column(name = "piscina")
    private Boolean piscina;

    @Column(name = "terraza")
    private Boolean terraza;

    @Column(name = "parking")
    private Boolean parking;


    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario id_usuario;


    @OneToMany(mappedBy = "hotel")
    private Set<Review> review;
    
    @OneToMany(mappedBy = "hotel")
    private Set<Habitacion> habiacion;

    @ManyToMany(mappedBy = "hotel")
    private List<Pension> pension;


    public Hotel(Integer id) {
        this.id = id;
    }

    public Hotel() {

    }
}
