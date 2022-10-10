package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipo_pension")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor

public class TipoPension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "desayuno")
    private Integer desayuno;
    @Column(name = "media_pension")
    private Integer mediaPension;
    @Column(name = "pension_completa")
    private Integer pensionCompleta;
    @Column(name = "todo_incluido")
    private Integer todoIncluido;

    @JoinTable(
            name = "hotel_tipo_pension",
            joinColumns = @JoinColumn(name = "tipo_pension", nullable = false),
            inverseJoinColumns = @JoinColumn(name="hotel", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Hotel> hoteles;
}
