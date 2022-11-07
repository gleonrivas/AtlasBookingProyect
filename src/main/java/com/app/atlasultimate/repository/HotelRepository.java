package com.app.atlasultimate.repository;


import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Pension;
import com.app.atlasultimate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel, Integer > {


    @Query(value = "SELECT * FROM hotel h where usuario = :id", nativeQuery = true)
    List<Hotel> findHotelById_usuario(@Param("id")Integer id);

    @Query(value = "SELECT h2.*  FROM registro r \n" +
            "join habitacion h on r.id_habitacion = h.id\n" +
            "JOIN  hotel h2 on h.id_hotel = h2.id\n" +
            "where  :fecha_inicio not BETWEEN r.f_entrada  and r.f_salida  \n" +
            "and :fecha_fin not BETWEEN r.f_entrada  and r.f_salida \n" +
            "and h2.ciudad like %:ciudad% \n" +
            "and h.n_max_personas >= :n_max_personas group by h2.id \n", nativeQuery = true)
    List<Hotel> findAllByReservas(@Param("fecha_inicio") String fecha_inicio,
                                  @Param("fecha_fin") String fecha_fin,
                                  @Param("ciudad") String ciudad,
                                  @Param("n_max_personas") Integer n_max_personas);


    @Query(value = "SELECT * FROM hotel h where id = :id", nativeQuery = true)
    Hotel findHotelById(@Param("id")Integer id);

    @Query(value = "SELECT id_hotel FROM habitacion h where id = :id", nativeQuery = true)
    Integer findHotelByIdHab(@Param("id")Integer id);



    Hotel findTopById(Integer id);





}
