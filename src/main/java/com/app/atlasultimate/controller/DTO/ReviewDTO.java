package com.app.atlasultimate.controller.DTO;

import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Getter
@Setter
public class ReviewDTO {

    private String nombre;
    private String comentario;
    private Integer estrellas;
    private Usuario id_usuario;
    private Hotel id_hotel;

    public ReviewDTO() {

    }

    public ReviewDTO(String nombre, String comentario, Integer estrellas, Usuario id_usuario, Hotel id_hotel) {
        this.nombre = nombre;
        this.comentario = comentario;
        this.estrellas = estrellas;
        this.id_usuario = id_usuario;
        this.id_hotel = id_hotel;
    }
}
