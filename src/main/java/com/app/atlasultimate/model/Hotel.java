package com.app.atlasultimate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity(name = "hotel")
@Table(name = "hotel")
@Getter
@Setter
@ToString
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario id_usuario;

    @JsonBackReference
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Review> review;

    @JsonBackReference
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Habitacion> habiacion;


    public Hotel(Integer id) {
        this.id = id;
    }

    public Hotel() {

    }

    public Hotel(String nombre, String pais, String ciudad, String direccion,
                 Integer estrellas, Integer telefono, String email, Boolean cancelacion_g, Boolean wifi,
                 Boolean mascotas, Boolean multilengua, Boolean accesibilidad, Boolean s_habitacion,
                 Time hc_recepcion, Time hf_recepcion, Boolean s_transporte, Boolean tours, Boolean comedor,
                 Boolean espectaculos, Boolean patio, Boolean piscina, Boolean terraza, Boolean parking) {
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.estrellas = estrellas;
        this.telefono = telefono;
        this.email = email;
        this.cancelacion_g = cancelacion_g;
        this.wifi = wifi;
        this.mascotas = mascotas;
        this.multilengua = multilengua;
        this.accesibilidad = accesibilidad;
        this.s_habitacion = s_habitacion;
        this.hc_recepcion = hc_recepcion;
        this.hf_recepcion = hf_recepcion;
        this.s_transporte = s_transporte;
        this.tours = tours;
        this.comedor = comedor;
        this.espectaculos = espectaculos;
        this.patio = patio;
        this.piscina = piscina;
        this.terraza = terraza;
        this.parking = parking;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getCancelacion_g() {
        return cancelacion_g;
    }

    public void setCancelacion_g(Boolean cancelacion_g) {
        this.cancelacion_g = cancelacion_g;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getMascotas() {
        return mascotas;
    }

    public void setMascotas(Boolean mascotas) {
        this.mascotas = mascotas;
    }

    public Boolean getMultilengua() {
        return multilengua;
    }

    public void setMultilengua(Boolean multilengua) {
        this.multilengua = multilengua;
    }

    public Boolean getAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(Boolean accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public Boolean getS_habitacion() {
        return s_habitacion;
    }

    public void setS_habitacion(Boolean s_habitacion) {
        this.s_habitacion = s_habitacion;
    }

    public Time getHc_recepcion() {
        return hc_recepcion;
    }

    public void setHc_recepcion(Time hc_recepcion) {
        this.hc_recepcion = hc_recepcion;
    }

    public Time getHf_recepcion() {
        return hf_recepcion;
    }

    public void setHf_recepcion(Time hf_recepcion) {
        this.hf_recepcion = hf_recepcion;
    }

    public Boolean getS_transporte() {
        return s_transporte;
    }

    public void setS_transporte(Boolean s_transporte) {
        this.s_transporte = s_transporte;
    }

    public Boolean getTours() {
        return tours;
    }

    public void setTours(Boolean tours) {
        this.tours = tours;
    }

    public Boolean getComedor() {
        return comedor;
    }

    public void setComedor(Boolean comedor) {
        this.comedor = comedor;
    }

    public Boolean getEspectaculos() {
        return espectaculos;
    }

    public void setEspectaculos(Boolean espectaculos) {
        this.espectaculos = espectaculos;
    }

    public Boolean getPatio() {
        return patio;
    }

    public void setPatio(Boolean patio) {
        this.patio = patio;
    }

    public Boolean getPiscina() {
        return piscina;
    }

    public void setPiscina(Boolean piscina) {
        this.piscina = piscina;
    }

    public Boolean getTerraza() {
        return terraza;
    }

    public void setTerraza(Boolean terraza) {
        this.terraza = terraza;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Set<Review> getReview() {
        return review;
    }

    public void setReview(Set<Review> review) {
        this.review = review;
    }

    public Set<Habitacion> getHabiacion() {
        return habiacion;
    }

    public void setHabiacion(Set<Habitacion> habiacion) {
        this.habiacion = habiacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(getId(), hotel.getId()) && Objects.equals(getNombre(), hotel.getNombre()) && Objects.equals(getPais(), hotel.getPais()) && Objects.equals(getCiudad(), hotel.getCiudad()) && Objects.equals(getDireccion(), hotel.getDireccion()) && Objects.equals(getEstrellas(), hotel.getEstrellas()) && Objects.equals(getTelefono(), hotel.getTelefono()) && Objects.equals(getEmail(), hotel.getEmail()) && Objects.equals(getCancelacion_g(), hotel.getCancelacion_g()) && Objects.equals(getImg(), hotel.getImg()) && Objects.equals(getWifi(), hotel.getWifi()) && Objects.equals(getMascotas(), hotel.getMascotas()) && Objects.equals(getMultilengua(), hotel.getMultilengua()) && Objects.equals(getAccesibilidad(), hotel.getAccesibilidad()) && Objects.equals(getS_habitacion(), hotel.getS_habitacion()) && Objects.equals(getHc_recepcion(), hotel.getHc_recepcion()) && Objects.equals(getHf_recepcion(), hotel.getHf_recepcion()) && Objects.equals(getS_transporte(), hotel.getS_transporte()) && Objects.equals(getTours(), hotel.getTours()) && Objects.equals(getComedor(), hotel.getComedor()) && Objects.equals(getEspectaculos(), hotel.getEspectaculos()) && Objects.equals(getPatio(), hotel.getPatio()) && Objects.equals(getPiscina(), hotel.getPiscina()) && Objects.equals(getTerraza(), hotel.getTerraza()) && Objects.equals(getParking(), hotel.getParking()) && Objects.equals(getId_usuario(), hotel.getId_usuario()) && Objects.equals(getReview(), hotel.getReview()) && Objects.equals(getHabiacion(), hotel.getHabiacion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getPais(), getCiudad(), getDireccion(), getEstrellas(), getTelefono(), getEmail(), getCancelacion_g(), getImg(), getWifi(), getMascotas(), getMultilengua(), getAccesibilidad(), getS_habitacion(), getHc_recepcion(), getHf_recepcion(), getS_transporte(), getTours(), getComedor(), getEspectaculos(), getPatio(), getPiscina(), getTerraza(), getParking(), getId_usuario(), getReview(), getHabiacion());
    }
}
