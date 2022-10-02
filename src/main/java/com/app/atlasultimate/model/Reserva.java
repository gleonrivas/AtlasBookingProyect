package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="reserva")
@Table(name = "reserva")
@Getter
@Setter
@EqualsAndHashCode
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "hora_llegada")
    private LocalDate diayHoraLlegada;
    @Column(name = "hora_salida")
    private LocalDate diayHoraSalida;
    @Column(name = "num_adultos")
    private Integer numAdultos;
    @Column(name = "num_ninos")
    private Integer numNinos;

}
