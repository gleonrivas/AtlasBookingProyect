package com.app.atlasultimate.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity (name="hotel")
@Table(name = "hotel")
@Getter
@Setter
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , length = 10)
    private Integer id;
    @Column(name = "nombre", length = 150)
    private String nombre;
    @Column(name = "pais", length = 150)
    private String pais;
    @Column(name = "ciudad", length = 150)
    private String ciudad;
    @Column(name = "direccion", length = 150)
    private String direccion;
    @Column(name = "estrellas", length = 1)
    private Integer estrellas;
    @Column(name = "telefono", length = 150)
    private Integer telefono;
    @Column(name = "email", length = 150)
    private String email;
    @Column(name = "cancelacion_g")
    private Boolean cancelacion_g;
    @Column(name = "img", length = 1000)
    private String img;

    @Column(name = "wifi")
    private Boolean wifi;

    @Column(name = "mascotas")
    private Boolean mascotas;

    @Column(name = "multilengua")
    private Boolean multilengua;

    @Column(name = "accesibilidad")
    private Boolean accesibilidad;

    @Column(name = "s_habitacion")
    private Boolean s_habitacion;

    @Column(name = "hc_recepcion")
    private Time hc_recepcion;

    @Column(name = "hf_recepcion")
    private Time hf_recepcion;

    @Column(name = "s_transporte")
    private Boolean s_transporte;

    @Column(name = "tours")
    private Boolean tours;

    @Column(name = "comedor")
    private Boolean comedor;

    @Column(name = "espectaculos")
    private Boolean espectaculos;

    @Column(name = "patio")
    private Boolean patio;

    @Column(name = "piscina")
    private Boolean piscina;

    @Column(name = "terraza")
    private Boolean terraza;

    @Column(name = "parking")
    private Boolean parking;


    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario id_usuario;


    @OneToMany(mappedBy = "hotel")
    private Set<Review> review;
    
    @OneToMany(mappedBy = "hotel")
    private Set<Habitacion> habiacion;

    @ManyToMany(mappedBy = "hotel")
    private List<Pension> pension;


    public Hotel(Integer id) {
        this.id = id;
    }

    public Hotel() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(id, hotel.id) && Objects.equals(nombre, hotel.nombre) && Objects.equals(pais, hotel.pais) && Objects.equals(ciudad, hotel.ciudad) && Objects.equals(direccion, hotel.direccion) && Objects.equals(estrellas, hotel.estrellas) && Objects.equals(telefono, hotel.telefono) && Objects.equals(email, hotel.email) && Objects.equals(cancelacion_g, hotel.cancelacion_g) && Objects.equals(img, hotel.img) && Objects.equals(wifi, hotel.wifi) && Objects.equals(mascotas, hotel.mascotas) && Objects.equals(multilengua, hotel.multilengua) && Objects.equals(accesibilidad, hotel.accesibilidad) && Objects.equals(s_habitacion, hotel.s_habitacion) && Objects.equals(hc_recepcion, hotel.hc_recepcion) && Objects.equals(hf_recepcion, hotel.hf_recepcion) && Objects.equals(s_transporte, hotel.s_transporte) && Objects.equals(tours, hotel.tours) && Objects.equals(comedor, hotel.comedor) && Objects.equals(espectaculos, hotel.espectaculos) && Objects.equals(patio, hotel.patio) && Objects.equals(piscina, hotel.piscina) && Objects.equals(terraza, hotel.terraza) && Objects.equals(parking, hotel.parking) && Objects.equals(id_usuario, hotel.id_usuario) && Objects.equals(review, hotel.review) && Objects.equals(habiacion, hotel.habiacion) && Objects.equals(pension, hotel.pension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, pais, ciudad, direccion, estrellas, telefono, email, cancelacion_g, img, wifi, mascotas, multilengua, accesibilidad, s_habitacion, hc_recepcion, hf_recepcion, s_transporte, tours, comedor, espectaculos, patio, piscina, terraza, parking, id_usuario, review, habiacion, pension);
    }
}
