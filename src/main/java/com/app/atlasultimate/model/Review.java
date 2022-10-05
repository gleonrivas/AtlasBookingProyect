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
   
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente usuario= new Cliente();
    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel= new Hotel();

}
