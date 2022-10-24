package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Pension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PensionRepository extends JpaRepository<Pension, Integer> {
    @Query(value="select p.id ,p.ad ,p.mp ,p.pc ,p.sa  from pension p join hotel_pension hp ON hp.tipo_pension = p.id\n" +
            "    join hotel h on hp.hotel = h.id where h.id = :id", nativeQuery = true)
    Pension pensionPorIdHotel(@Param("id_habitacion") Integer id);


}
