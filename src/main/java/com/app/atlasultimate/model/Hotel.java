package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@EqualsAndHashCode
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "pais")
    private String pais;
    @Column(name = "fecha_cierre")
    private Date fecha_cierre;
    @Column(name = "fecha_apertura")
    private Date fecha_apertura;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "num_habitaciones")
    private Integer numHabitaciones;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "estrellas")
    private Integer estrellas;
    @Column(name = "accesibilidad")
    private Boolean accesibilidad;
    @Column(name = "idiomas")
    private String listaIdiomas;
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
    @Column(name = "servicio_limpieza")
    private Boolean servicioLimpieza;
    @Column(name = "servicio_habitaciones")
    private Boolean servicioHabitaciones;
    @Column(name = "mascotas")
    private Boolean mascotas;
    @Column(name = "valoracion_media")
    private Double valoracionMedia;
    @Column(name = "pagina_web")
    private String web;
    @Column(name = "num_telefono")
    private String telefono;
    @Column(name = "codigo_postal")
    private Integer codigo_postal;

    @ManyToOne()
    @JoinColumn(name="id_administrador")
    private Administrador administrador;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Review> reservas;

    @OneToMany(mappedBy = "hotel")
    private Set<Habitacion> habitaciones;

    @ManyToMany(mappedBy = "hoteles")
    private List<TipoPension> books;

}
