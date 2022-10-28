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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) && Objects.equals(estrellas, review.estrellas) && Objects.equals(comentario, review.comentario) && Objects.equals(usuario, review.usuario) && Objects.equals(hotel, review.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estrellas, comentario, usuario, hotel);
    }
}
