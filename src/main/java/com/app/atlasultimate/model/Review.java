package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "review")
@Getter
@Setter
@EqualsAndHashCode
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "estrellas")
    private Integer estrellas;
    @Column(name = "comentario")
    private String cometario;
    @Column(name = "id_usuario")
    private Integer id_usuario;
    @Column(name = "id_hotel")
    private Integer id_hotel;
    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario= new Usuario();
    @ManyToOne
    @JoinColumn(name = "usuario")
    private Hotel hotel= new Hotel();

}
