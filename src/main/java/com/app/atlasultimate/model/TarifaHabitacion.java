package com.app.atlasultimate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tarifa_habitacion")
@Getter
@Setter
@EqualsAndHashCode
public class TarifaHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "baño")
    private boolean baño;
    @Column(name = "wifi")
    private boolean wifi;
    @Column(name = "vistas")
    private boolean vistas;
    @Column(name = "capacidad")
    private boolean capacidad;

    @JsonBackReference
    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "id_habitacion")
    Habitacion habitacion= new Habitacion();
}
