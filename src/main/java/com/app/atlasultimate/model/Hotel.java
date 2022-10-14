package com.app.atlasultimate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

@Entity (name="hotel")
@Table(name = "hotel")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "pais")
    private String pais;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "num_habitaciones")
    private Integer numHabitaciones;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "estrellas")
    private Integer estrellas;

    @Column(name = "idiomas")
    private String listaIdiomas;

    @ColumnDefault("false")
    @Column(name = "terraza" , nullable=true, columnDefinition = "boolean default false")
    private Boolean terraza;

    @ColumnDefault("false")
    @Column(name = "piscina" , nullable=true, columnDefinition = "boolean default false")
    private  Boolean piscina;

    @ColumnDefault("false")
    @Column(name = "patio" , nullable=true, columnDefinition = "boolean default false")
    private Boolean patioInterior;

    @ColumnDefault("false")
    @Column(name = "espectaculos" , nullable=true, columnDefinition = "boolean default false")
    private Boolean espectaculos;

    @ColumnDefault("false")
    @Column(name = "comedor" , nullable=true, columnDefinition = "boolean default false")
    private Boolean comedor;

    @ColumnDefault("false")
    @Column(name = "tours" , nullable=true, columnDefinition = "boolean default false")
    private Boolean tours;

    @ColumnDefault("false")
    @Column(name = "aparcamiento" , nullable=true, columnDefinition = "boolean default false")
    private Boolean aparcamiento;

    @ColumnDefault("false")
    @Column(name = "servicio_transporte" , nullable=true, columnDefinition = "boolean default false")
    private Boolean servicioTransporte;

    @ColumnDefault("false")
    @Column(name = "recepcion" , nullable=true, columnDefinition = "boolean default false")
    private Boolean recepcion;

    @ColumnDefault("false")
    @Column(name = "servicio_limpieza" , nullable=true, columnDefinition = "boolean default false")
    private Boolean servicioLimpieza;

    @ColumnDefault("false")
    @Column(name = "servicio_habitaciones" , nullable=true, columnDefinition = "boolean default false")
    private Boolean servicioHabitaciones;

    @ColumnDefault("false")
    @Column(name = "mascotas", nullable=true , columnDefinition = "boolean default false")
    private Boolean mascotas;

    @ManyToOne()
    @JoinColumn(name="id_administrador")
    private Administrador administrador = new Administrador(1);
    @Column(name = "pagina_web")
    private String web;
    @Column(name = "num_telefono")
    private String telefono;
    @Column(name = "codigo_postal")
    private Integer codigo_postal;
    @Column(name = "accesibilidad")
    private Boolean accesibilidad;

    @Column(name = "fecha_cierre")
    private Date fecha_cierre = new Date(2022-03-03-00-00-00);
    @Column(name = "fecha_apertura")
    private Date fecha_apertura = new Date(2022-03-03-00-00-00);
    @Column(name = "wifi")
    private Boolean wifi;
    @Column(name = "cancelacion_grat")
    private Boolean cancelacionGratuita;
    @Column(name = "preferencia_pago")
    private String preferenciaPago;















    @JsonBackReference
    @OneToMany(mappedBy = "hotel")
    private Set<Review> reservas;

    @JsonBackReference
    @OneToMany(mappedBy = "hotel")
    private Set<Habitacion> habitaciones;

    @JsonBackReference
    @ManyToMany(mappedBy = "hoteles")
    private List<TipoPension> tipoPensiones;

    public Hotel(String nombre, String pais, Date fecha_cierre, Date fecha_apertura, String ciudad, Integer numHabitaciones, String ubicacion, Integer estrellas, Boolean accesibilidad, String listaIdiomas, Boolean terraza, Boolean piscina, Boolean patioInterior, Boolean espectaculos, Boolean comedor, Boolean tours, Boolean aparcamiento,
                 Boolean servicioTransporte, Boolean recepcion, Boolean servicioLimpieza, Boolean servicioHabitaciones, Boolean mascotas, String web, String telefono, Integer codigo_postal, Boolean wifi, Administrador administrador, Set<Review> reservas, Set<Habitacion> habitaciones, List<TipoPension> tipoPensiones) {
        this.nombre = nombre;
        this.pais = pais;
        this.fecha_cierre = fecha_cierre;
        this.fecha_apertura = fecha_apertura;
        this.ciudad = ciudad;
        this.numHabitaciones = numHabitaciones;
        this.ubicacion = ubicacion;
        this.estrellas = estrellas;
        this.accesibilidad = accesibilidad;
        this.listaIdiomas = listaIdiomas;
        this.terraza = terraza;
        this.piscina = piscina;
        this.patioInterior = patioInterior;
        this.espectaculos = espectaculos;
        this.comedor = comedor;
        this.tours = tours;
        this.aparcamiento = aparcamiento;
        this.servicioTransporte = servicioTransporte;
        this.recepcion = recepcion;
        this.servicioLimpieza = servicioLimpieza;
        this.servicioHabitaciones = servicioHabitaciones;
        this.mascotas = mascotas;
        this.web = web;
        this.telefono = telefono;
        this.codigo_postal = codigo_postal;
        this.wifi = wifi;
        this.administrador = administrador;
        this.reservas = reservas;
        this.habitaciones = habitaciones;
        this.tipoPensiones = tipoPensiones;
    }
}
