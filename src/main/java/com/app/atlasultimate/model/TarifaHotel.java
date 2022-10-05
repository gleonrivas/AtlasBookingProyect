package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tarifa_hotel")
@Getter
@Setter
@EqualsAndHashCode
public class TarifaHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "terraza")
    private boolean terraza;
    @Column(name = "piscina")
    private boolean piscina;
    @Column(name = "patio")
    private boolean patio;
    @Column(name = "espectaculos")
    private boolean espectaculos;
    @Column(name = "comedor")
    private boolean comedor;
    @Column(name = "tours")
    private boolean tours;
    @Column(name = "aparcamiento")
    private boolean aparcamiento;
    @Column(name = "servicio_transporte")
    private boolean servicio_transporte;
    @Column(name = "recepcion")
    private boolean recepcion;
    @Column(name = "servicio_limpieza")
    private boolean servicio_limpieza;
    @Column(name = "servicio_habitaciones")
    private boolean servicio_habitaciones;
    @OneToOne
    @JoinColumn(name = "hotel")
    private Hotel hotel= new Hotel();

}
