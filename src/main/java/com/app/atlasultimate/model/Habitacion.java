package com.app.atlasultimate.model;

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
    @Column(name = "num_habitaciones_iguales", length = 10)
    private Integer num_habitaciones_iguales;

    @OneToMany (mappedBy = "habitacion")
    private Set<Reserva> reservas;

    @JsonBackReference
    @ManyToOne( optional = true, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hotel")
    private Hotel hotel = new Hotel(1);

    @JsonBackReference
    @OneToMany(mappedBy = "habitacion",fetch = FetchType.LAZY)
    private Set<Registro> registro;


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
