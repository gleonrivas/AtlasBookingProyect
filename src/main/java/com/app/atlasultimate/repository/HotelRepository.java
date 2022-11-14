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


    @Query(value = "SELECT * FROM hotel h where usuario = :id order by h.id DESC", nativeQuery = true)
    List<Hotel> findHotelById_usuario(@Param("id")Integer id);

    @Query(value = "SELECT h.* FROM hotel h \n" +
            "join usuario u on h.usuario = u.id \n" +
            "JOIN habitacion h2 on h.id = h2.id_hotel \n" +
            "left JOIN registro r on h2.id = r.id_habitacion \n" +
            "and :fecha_inicio not BETWEEN r.f_entrada and r.f_salida \n" +
            "and :fecha_fin not BETWEEN r.f_entrada  and r.f_salida \n" +
            "and h.ciudad like %:ciudad% \n" +
            "and h2.n_max_personas >= :n_max_personas group by h.id", nativeQuery = true)
    List<Hotel> findAllByReservas(@Param("fecha_inicio") String fecha_inicio,
                                  @Param("fecha_fin") String fecha_fin,
                                  @Param("ciudad") String ciudad,
                                  @Param("n_max_personas") Integer n_max_personas);


    @Query(value = "SELECT h.* FROM hotel h \n" +
            "join usuario u on h.usuario = u.id \n" +
            "JOIN habitacion h2 on h.id = h2.id_hotel \n" +
            "left JOIN registro r on h2.id = r.id_habitacion \n" +
            "and :fecha_inicio not BETWEEN r.f_entrada and r.f_salida \n" +
            "and :fecha_fin not BETWEEN r.f_entrada  and r.f_salida \n" +
            "and h2.n_max_personas >= :n_max_personas group by h.id", nativeQuery = true)
    List<Hotel> findAllByReservas2(@Param("fecha_inicio") String fecha_inicio,
                                  @Param("fecha_fin") String fecha_fin,
                                  @Param("n_max_personas") Integer n_max_personas);


    @Query(value = "SELECT * FROM hotel h where id = :id", nativeQuery = true)
    Hotel findHotelById(@Param("id")Integer id);

    @Query(value = "SELECT id_hotel FROM habitacion h where id = :id", nativeQuery = true)
    Integer findHotelByIdHab(@Param("id")Integer id);

    Hotel findTopById(Integer id);

    @Query(value = "SELECT * FROM hotel h", nativeQuery = true)
    List<Hotel> findAll();

    List<Hotel> findAllByCiudad(String ciudad);

    @Query(value = "SELECT h.* FROM hotel h \n" +
            "join usuario u on h.usuario = u.id \n" +
            "left JOIN review r on h.id = r.id_hotel\n" +
            "group by h.id order by r.estrellas desc", nativeQuery = true)
    List<Hotel> mejoresValorados();

    @Query(value = "SELECT h.* FROM hotel h \n" +
            "join usuario u on h.usuario = u.id \n" +
            "JOIN habitacion h2 on h.id = h2.id_hotel \n" +
            "left JOIN registro r on h2.id = r.id_habitacion \n" +
            "left JOIN review r2 on h.id = r2.id_hotel\n" +
            "and  :fecha_inicio not BETWEEN r.f_entrada  and r.f_salida  \n" +
            "and :fecha_fin not BETWEEN r.f_entrada  and r.f_salida \n" +
            "and h2.n_max_personas >= :n_max_personas \n" +
            "group by h.id order by r2.estrellas desc", nativeQuery = true)
    List<Hotel> mejoresValoradosPorReview(@Param("fecha_inicio") String fecha_inicio,
                                  @Param("fecha_fin") String fecha_fin,
                                  @Param("n_max_personas") Integer n_max_personas);


    @Query(value = "SELECT h.* FROM hotel h \n" +
            "group by h.id order by h.estrellas desc", nativeQuery = true)
    List<Hotel> mejoresValoradosPorHotel();

    @Query(value = "SELECT h.* FROM hotel h \n" +
            "join usuario u on h.usuario = u.id \n" +
            "JOIN habitacion h2 on h.id = h2.id_hotel \n" +
            "left JOIN registro r on h2.id = r.id_habitacion \n" +
            "and  :fecha_inicio not BETWEEN r.f_entrada  and r.f_salida  \n" +
            "and :fecha_fin not BETWEEN r.f_entrada  and r.f_salida \n" +
            "and h2.n_max_personas >= :n_max_personas \n" +
            "group by h.id order by h.estrellas desc", nativeQuery = true)
    List<Hotel> mejoresValoradosPorHotelBusqueda(@Param("fecha_inicio") String fecha_inicio,
                                            @Param("fecha_fin") String fecha_fin,
                                            @Param("n_max_personas") Integer n_max_personas);



}
