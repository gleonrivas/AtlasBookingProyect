package com.app.atlasultimate.repository;


import com.app.atlasultimate.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel, Integer > {

    @Query(value = "SELECT h2.*  FROM reserva r \n" +
            "join habitacion h on r.id_habitacion = h.id\n" +
            "JOIN  hotel h2 on h.id_hotel = h2.id\n" +
            "where  :fecha_inicio not BETWEEN r.dia_hora_llegada and r.dia_hora_salida \n" +
            "and :fecha_fin not BETWEEN r.dia_hora_llegada and r.dia_hora_salida\n" +
            "and h2.ciudad like %:ciudad% \n" +
            "and h.tipo_cama = :tipo_cama", nativeQuery = true)
    List<Hotel> findAllByReservas(@Param("fecha_inicio") Timestamp fecha_inicio,
                                  @Param("fecha_fin") Timestamp fecha_fin,
                                  @Param("ciudad") String ciudad,
                                  @Param("tipo_cama") String tipo_cama);


    @Query(value = "SELECT * FROM hotel h where id = :id", nativeQuery = true)
    Hotel findHotelById(@Param("id")Integer id);
}
