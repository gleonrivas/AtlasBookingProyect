package com.app.atlasultimate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "habitacion")
@Getter
@Setter
@EqualsAndHashCode
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "c_indiviual", length = 1)
    private Integer c_individual;
    @Column(name = "c_doble", length = 1)
    private Integer c_doble;
    @Column(name = "precio_base")
    private Double precio_base;
    @Column(name = "bano")
    private Boolean bano;
    @Column(name = "vistas")
    private Boolean vistas;
    @Column(name = "n_max_personas")
    private Integer n_max_personas;
    @Column(name = "num_habitaciones_iguales", length = 10)
    private Integer num_habitaciones_iguales;


    @JsonBackReference
    @ManyToOne( optional = true, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

    @JsonBackReference
    @OneToMany(mappedBy = "habitacion",fetch = FetchType.LAZY)
    private Set<Registro> registro;

    public Habitacion(Integer id, Integer c_individual, Integer c_doble, Double precio_base, Boolean bano, Boolean vistas,
                      Integer num_habitaciones_iguales, Hotel hotel, Set<Registro> registro, Integer n_max_personas) {
        this.id = id;
        this.c_individual = c_individual;
        this.c_doble = c_doble;
        this.precio_base = precio_base;
        this.bano = bano;
        this.vistas = vistas;
        this.num_habitaciones_iguales = num_habitaciones_iguales;
        this.hotel = hotel;
        this.registro = registro;
        this.n_max_personas= n_max_personas;
    }

    public Habitacion(Habitacion hab) {
        this.id = null;
        this.c_individual = hab.c_individual;
        this.c_doble = hab.c_doble;
        this.precio_base = hab.precio_base;
        this.bano = hab.bano;
        this.vistas = hab.vistas;
        this.num_habitaciones_iguales = hab.num_habitaciones_iguales;
        this.hotel = hab.hotel;
        this.registro =hab.registro;
        this.n_max_personas= hab.n_max_personas;
    }

    public Habitacion() {
    }
}
