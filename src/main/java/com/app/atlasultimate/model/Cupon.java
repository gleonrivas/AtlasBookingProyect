package com.app.atlasultimate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.javafaker.Bool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cupon")
@Getter
@Setter
@AllArgsConstructor
public class Cupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private Integer id;

    @Column(name = "descuento")
    private Integer descuento;

    @Column(name = "fecha_conseguido", length = 150)
    private String fecha_conseguido;

    @Column(name = "activo", length = 150)
    private Boolean activo;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario= new Usuario();

    public Cupon() {

    }
}
