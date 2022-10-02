package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@EqualsAndHashCode
public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente")
    private Set<Reserva> reservas;

    @OneToMany(mappedBy = "cliente")
    private Set<Review> reviews;




}
