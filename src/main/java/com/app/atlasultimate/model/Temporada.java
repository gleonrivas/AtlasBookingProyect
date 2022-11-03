package com.app.atlasultimate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "temporada")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Temporada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private Integer id;

    @Column(name = "precio_invierno", length = 500)
    private Double precioInvierno;

    @Column(name = "precio_primavera", length = 500)
    private Double precioPrimavera;

    @Column(name = "precio_verano", length = 500)
    private Double precioVerano;

    @Column(name = "precio_otono", length = 500)
    private Double precioOtono;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "temporada_id")
    private List<Habitacion> habitaaciones;

    public Temporada(Double precioInvierno, Double precioPrimavera, Double precioVerano, Double precioOtono, List<Habitacion> habitaaciones) {
        this.precioInvierno = precioInvierno;
        this.precioPrimavera = precioPrimavera;
        this.precioVerano = precioVerano;
        this.precioOtono = precioOtono;
        this.habitaaciones = habitaaciones;
    }
}
