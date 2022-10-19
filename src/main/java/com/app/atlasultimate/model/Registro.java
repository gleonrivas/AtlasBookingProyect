package com.app.atlasultimate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="registro")
@Table(name = "registro")
@Getter
@Setter
@EqualsAndHashCode
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private Integer id;


    @Column(name = "f_entrada")
    private LocalDate f_entrada;
    @Column(name = "f_salida")
    private LocalDate f_salida;
    @Column(name = "n_personas", length = 1)
    private Integer n_personas;
    @Column(name = "t_pago", length = 150)
    private String t_pago;
    @Column(name = "senal")
    private Double senal;
    @Column(name = "t_pension", length = 150)
    private String t_pension;
    @Column(name = "precio_total_dias")
    private Double precio_total_dias;
    @Column(name = "n_dias", length = 3)
    private Integer n_dias;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    Usuario usuario;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_habitacion")
    Habitacion habitacion;
}
