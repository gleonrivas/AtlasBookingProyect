package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface HabitacionRepository  extends JpaRepository<Habitacion,Integer> {

    @Query(value = "SELECT * FROM habitacion h where id_hotel = :id_hotel", nativeQuery = true)
    List<Habitacion> findAllById(@Param("id_hotel")Integer id_hotel);

    Habitacion findTopById(Integer id_habitacion);

    @Query(value="update habitacion set temporada_id = :id_temporada where id = :id_habitacion", nativeQuery = true)
    Habitacion cambiarIdTemporada(@Param("id_temporada") Integer id_temporada, @Param("id_habitacion") Integer id_habitacion);

    @Query(value="select id_hotel from habitacion h", nativeQuery = true)
    List<Integer> idHotelPorHabitacion();

}
