package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrador")
@Getter
@Setter
@EqualsAndHashCode
public class Administrador extends Usuario{
}
