package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "administrador")
@Getter
@Setter
@EqualsAndHashCode
public class Administrador extends Usuario{
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "administrador")
    public Set<Hotel> Hoteles;
}
