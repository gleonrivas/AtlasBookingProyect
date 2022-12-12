package com.app.atlasultimate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "habitacion")
@Getter
@Setter
@AllArgsConstructor
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Integer id;
    @Column(name = "c_indiviual", length = 1)
    private Integer c_individual;
    @Column(name = "c_doble", length = 1)
    private Integer c_doble;
    @Column(name = "precio_base")
    private Double precio_base;
    @Column(name = "img")
    private String img;
    @Column(name = "bano")
    private Boolean bano;
    @Column(name = "vistas")
    private Boolean vistas;
    @Column(name = "n_max_personas")
    private Integer n_max_personas;
    @Column(name = "num_habitaciones_iguales", length = 10)
    private Integer num_habitaciones_iguales;


    @JsonBackReference
    @ManyToOne( optional = true, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

    @JsonBackReference
    @OneToMany(mappedBy = "habitacion",fetch = FetchType.LAZY)
    private Set<Registro> registro;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "temporada_id")
    private Temporada temporada;

    public Habitacion(int i, int uno, int cero, int uno1, String valueOf, boolean b, boolean b1, int uno2, int uno3, String firstName) {
    }


    @PreRemove
    public void nullificar(){
        registro.forEach(r -> r.setHabitacion(null));
    }


    public Habitacion(Integer id, Integer c_individual, Integer c_doble, Double precio_base, Boolean bano, Boolean vistas,
                      Integer num_habitaciones_iguales, Hotel hotel, Set<Registro> registro, Integer n_max_personas, String img) {
        this.id = id;
        this.c_individual = c_individual;
        this.c_doble = c_doble;
        this.precio_base = precio_base;
        this.bano = bano;
        this.vistas = vistas;
        this.num_habitaciones_iguales = num_habitaciones_iguales;
        this.hotel = hotel;
        this.registro = registro;
        this.n_max_personas= n_max_personas;
        this.img = img;
    }

    public Habitacion(Habitacion hab) {
        this.id = hab.id;
        this.c_individual = hab.c_individual;
        this.c_doble = hab.c_doble;
        this.precio_base = hab.precio_base;
        this.bano = hab.bano;
        this.vistas = hab.vistas;
        this.num_habitaciones_iguales = hab.num_habitaciones_iguales;
        this.hotel = hab.hotel;
        this.registro =hab.registro;
        this.n_max_personas= hab.n_max_personas;
        this.img = hab.img;
    }

    public Habitacion() {
    }

    public Habitacion(Integer c_individual, Integer c_doble, Double precio_base, String img, Boolean bano,
                      Boolean vistas, Integer n_max_personas, Integer num_habitaciones_iguales, Hotel hotel, Set<Registro> registro, Temporada temporada) {
        this.c_individual = c_individual;
        this.c_doble = c_doble;
        this.precio_base = precio_base;
        this.img = img;
        this.bano = bano;
        this.vistas = vistas;
        this.n_max_personas = n_max_personas;
        this.num_habitaciones_iguales = num_habitaciones_iguales;
        this.hotel = hotel;
        this.registro = registro;
        this.temporada = temporada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Habitacion)) return false;
        Habitacion that = (Habitacion) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getC_individual(), that.getC_individual()) && Objects.equals(getC_doble(), that.getC_doble()) && Objects.equals(getPrecio_base(), that.getPrecio_base()) && Objects.equals(getBano(), that.getBano()) && Objects.equals(getVistas(), that.getVistas()) && Objects.equals(getN_max_personas(), that.getN_max_personas()) && Objects.equals(getNum_habitaciones_iguales(), that.getNum_habitaciones_iguales()) && Objects.equals(getHotel(), that.getHotel()) && Objects.equals(getRegistro(), that.getRegistro()) && Objects.equals(getTemporada(), that.getTemporada());
    }


}
