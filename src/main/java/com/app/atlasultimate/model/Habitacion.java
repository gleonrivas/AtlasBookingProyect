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

    @Column(name = "num_habitacion")
    private Integer num_habitacion;

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
    @ManyToOne( optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

}
