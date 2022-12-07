package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Cupon;
import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuponRepository extends JpaRepository<Cupon,Integer> {

    List<Cupon> findAllByUsuario(Usuario usuario);

}
