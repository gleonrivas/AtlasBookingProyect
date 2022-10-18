package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface HabitacionRepository  extends JpaRepository<Habitacion,Integer> {

    @Query(value = "SELECT * FROM habitacion h where id_hotel = :id_hotel", nativeQuery = true)
    List<Habitacion> findAllById(@Param("id_hotel")Integer id_hotel);

}
