package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @JoinColumn(name = "id_cliente")
    private Cliente cliente= new Cliente();
    @ManyToOne
    @JoinColumn(name = "hotel")
    private Hotel hotel= new Hotel();

}
