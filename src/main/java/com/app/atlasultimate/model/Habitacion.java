package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usuario")
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
    private tipo_habitacion tipo_habitacion;

    @Column(name = "num_habitacion")
    private Integer num_habitacion;

    @Column(name = "num_camas")
    private Integer num_camas;

    @Column(name = "tipo_cama")
    private tipo_cama tipo_cama;

    @Column(name = "baño")
    private Boolean bano;

    @Column(name = "wifi")
    private Boolean wifi;

    @Column(name = "precio_base")
    private Double precio_base;

    @Column(name = "vistas")
    private Boolean vistas;



    @OneToMany (mappedBy = "habitacion")
    private Set<Reserva> reservas;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

}
