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


    @OneToMany (mappedBy = "habitacion")
    private Set<Reserva> reservas;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

}
