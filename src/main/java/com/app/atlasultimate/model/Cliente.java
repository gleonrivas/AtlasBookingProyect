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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
            @JoinTable(name="reserva", joinColumns = {@JoinColumn(name = "id_cliente")},
                    inverseJoinColumns = {@JoinColumn(name = "id_habitacion")})
    private Set<Habitacion> habitaciones;


}
