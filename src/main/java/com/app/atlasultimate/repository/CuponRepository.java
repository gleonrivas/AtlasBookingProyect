package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Cupon;
import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

@Repository
public interface CuponRepository extends JpaRepository<Cupon,Integer> {

    List<Cupon> findAllByUsuario(Usuario usuario);


    @Query(value = "SELECT * FROM cupon c \n" +
            "WHERE id_usuario = :id_usuario \n" +
            "and fecha_conseguido = :fecha " , nativeQuery = true)
    List<Cupon> findByFechaAndUsuario(@Param("id_usuario") Integer id_usuario,
                                      @Param("fecha") Date fecha);

    @Query(value = "SELECT * FROM cupon c \n" +
            "WHERE id_usuario = :id_usuario \n" +
            "and activo = :activo " , nativeQuery = true)
    List<Cupon> findByActivoAndUsuario(@Param("id_usuario") Integer id_usuario,
                                      @Param("activo") Boolean activo);



    @Query(value = "update cupon set activo = :activo where id_usuario = :id_usuario and descuento = :descuento" , nativeQuery = true)
    List<Cupon> updateCuponActivo(@Param("activo") Boolean activo, @Param("id_usuario") Integer id_usuario, @Param("descuento") Integer descuento);



}
