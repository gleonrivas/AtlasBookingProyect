package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import org.hibernate.mapping.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface HabitacionRepository  extends JpaRepository<Habitacion,Integer> {

    @Query(value = "SELECT * FROM habitacion h where id_hotel = :id_hotel", nativeQuery = true)
    List<Habitacion> findAllById(@Param("id_hotel")Integer id_hotel);

    @Query(value="update habitacion set temporada_id = :id_temporada where id = :id_habitacion", nativeQuery = true)
    void cambiarIdTemporada(@Param("id_temporada") Integer id_temporada, @Param ("id_habitacion") Integer id_habitacion);

}
