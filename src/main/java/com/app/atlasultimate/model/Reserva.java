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

    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_habitacion")
    Habitacion habitacion;

    @Column(name = "dia_hora_llegada")
    private Timestamp diayHoraLlegada;
    @Column(name = "dia_hora_salida")
    private Timestamp diayHoraSalida;
    @Column(name = "num_adultos")
    private Integer numAdultos;
    @Column(name = "num_ninos")
    private Integer numNinos;


}
