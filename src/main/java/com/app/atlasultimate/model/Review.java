package com.app.atlasultimate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "review")
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private Integer id;
    @Column(name = "estrellas", length = 1)
    private Integer estrellas;
    @Column(name = "comentario", length = 500)
    private String comentario;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario= new Usuario();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hotel")
    private Hotel hotel= new Hotel();

    public Review(Integer estrellas, String comentario, Usuario usuario, Hotel hotel) {
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.usuario = usuario;
        this.hotel = hotel;
    }

    public Review() {

    }

    public Review(Integer id, Integer estrellas, String comentario, Usuario usuario, Hotel hotel) {
        this.id = id;
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.usuario = usuario;
        this.hotel = hotel;
    }
   
}