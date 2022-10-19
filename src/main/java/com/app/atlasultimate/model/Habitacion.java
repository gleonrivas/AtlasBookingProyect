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

    @Column(name = "disponible")
    private Boolean disponible;

    @Column(name = "tipo_habitacion")
    private String tipo_habitacion;

    @Column(name = "num_habitaciones_iguales")
    private Integer num_habitaciones_iguales;

    @Column(name = "num_camas")
    private Integer num_camas;

    @Column(name = "tipo_cama")
    private String tipo_cama;

    @Column(name = "bano")
    private Boolean bano;

    @Column(name = "wifi")
    private Boolean wifi;

    @Column(name = "precio_base")
    private Double precio_base;

    @Column(name = "vistas")
    private Boolean vistas;


    @JsonBackReference
    @OneToMany (mappedBy = "habitacion" , fetch = FetchType.LAZY)
    private Set<Reserva> reservas;

    @JsonBackReference
    @ManyToOne( optional = true, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hotel")
    private Hotel hotel = new Hotel(1);


    public Habitacion( Boolean disponible, String tipo_habitacion, Integer num_habitaciones_iguales, Integer num_camas, String tipo_cama, Boolean bano, Boolean wifi, Double precio_base, Boolean vistas, Set<Reserva> reservas, Hotel hotel) {

        this.disponible = disponible;
        this.tipo_habitacion = tipo_habitacion;
        this.num_habitaciones_iguales = num_habitaciones_iguales;
        this.num_camas = num_camas;
        this.tipo_cama = tipo_cama;
        this.bano = bano;
        this.wifi = wifi;
        this.precio_base = precio_base;
        this.vistas = vistas;
        this.reservas = reservas;
        this.hotel = hotel;
    }

    public Habitacion(Integer id,Boolean disponible, String tipo_habitacion, Integer num_habitaciones_iguales, Integer num_camas, String tipo_cama, Boolean bano, Boolean wifi, Double precio_base, Boolean vistas, Set<Reserva> reservas, Hotel hotel) {
        this.id = id;
        this.disponible = disponible;
        this.tipo_habitacion = tipo_habitacion;
        this.num_habitaciones_iguales = num_habitaciones_iguales;
        this.num_camas = num_camas;
        this.tipo_cama = tipo_cama;
        this.bano = bano;
        this.wifi = wifi;
        this.precio_base = precio_base;
        this.vistas = vistas;
        this.reservas = reservas;
        this.hotel = hotel;
    }

    public Habitacion() {
    }
}
