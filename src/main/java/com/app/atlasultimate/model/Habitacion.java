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

    @Column(name = "c_indiviual", length = 1)
    private Integer c_individual;
    @Column(name = "c_doble", length = 1)
    private Integer c_doble;
    @Column(name = "precio_base")
    private Double precio_base;
    @Column(name = "bano")
    private Boolean bano;
    @Column(name = "vistas")
    private Boolean vistas;
    @Column(name = "num_habitaciones_iguales", length = 10)
    private Integer num_habitaciones_iguales;


    @JsonBackReference
    @ManyToOne( optional = true, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hotel")
    private Hotel hotel = new Hotel(1);

    @JsonBackReference
    @OneToMany(mappedBy = "habitacion",fetch = FetchType.LAZY)
    private Set<Registro> registro;


}
