package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hotel")
@Getter
@Setter
@EqualsAndHashCode
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "pais")
    private String pais;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "num_habitaciones")
    private Integer numHabitaciones;
    @Column(name = "fechaDisponibilidad")
    private LocalDate FechaDisponible;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "pagina_web")
    private String pagWeb;
    @Column(name = "terraza")
    private Boolean terraza;
    @Column(name = "piscina")
    private  Boolean piscina;
    @Column(name = "patio")
    private Boolean patioInterior;
    @Column(name = "espectaculos")
    private Boolean espectaculos;
    @Column(name = "comedor")
    private Boolean comedor;
    @Column(name = "tours")
    private Boolean tours;
    @Column(name = "aparcamiento")
    private Boolean aparcamiento;
    @Column(name = "servicio_transporte")
    private Boolean servicioTransporte;
    @Column(name = "recepcion")
    private Boolean recepcion;
    @Column(name = "estrellas")
    private Integer estrellas;
    @Column(name = "valoracion_media")
    private Double valoracionMedia;
    @Column(name = "servicio_limpieza")
    private Boolean servicioLimpieza;
    @Column(name = "servicio_habitaciones")
    private Boolean servicioHabitaciones;
    @Column(name = "accesibilidad")
    private Boolean accesibilidad;
    @Column(name = "idiomas")
    private String listaIdiomas;
    @Column(name = "mascotas")
    private Boolean mascotas;


}
