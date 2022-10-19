package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@EqualsAndHashCode
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
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
