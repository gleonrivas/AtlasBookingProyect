package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Habitacion;
import org.hibernate.mapping.Set;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HabitacionRepository  extends JpaRepository<Habitacion,Integer> {

    @Query(value = "SELECT * FROM habitacion h where id_hotel = :id_hotel", nativeQuery = true)
    List<Habitacion> findAllById(@Param("id_hotel")Integer id_hotel);

}
