package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository

public interface HabitacionRepository  extends JpaRepository<Habitacion,Integer> {

    @Query(value = "SELECT * FROM habitacion h where id_hotel = :id_hotel", nativeQuery = true)
    List<Habitacion> findAllById(@Param("id_hotel")Integer id_hotel);

    Habitacion findTopById(Integer id_habitacion);

    @Query(value="update habitacion set temporada_id = :id_temporada where id = :id_habitacion", nativeQuery = true)
    Habitacion cambiarIdTemporada(@Param("id_temporada") Integer id_temporada, @Param("id_habitacion") Integer id_habitacion);

    @Query(value="select id_hotel from habitacion h", nativeQuery = true)
    List<Integer> idHotelPorHabitacion();

    @Query(value = "SELECT h.* from habitacion h \n" +
            "join registro r on h.id = r.id_habitacion \n" +
            "where :fecha_inicio not BETWEEN r.f_entrada and r.f_salida \n" +
            "and :fecha_fin not BETWEEN r.f_entrada  and r.f_salida \n" +
            "and h.id_hotel = :id_hotel", nativeQuery = true)
    List<Habitacion> findAllByfechas(@Param("fecha_inicio") String fecha_inicio,
                                     @Param("fecha_fin")String fecha_fin,
                                     @Param("id_hotel")Integer id_hotel);

    @Query(value = "SELECT id_hotel FROM habitacion h where id = :id_hab", nativeQuery = true)
    Integer idHotel(@Param("id_hab")Integer id_hab);

    @Query(value = "SELECT temporada_id FROM habitacion h where id = :id_hab", nativeQuery = true)
    Integer idTemporada(@Param("id_hab")Integer id_hab);

    @Query(value = "SELECT h2.*  FROM habitacion h2\n" +
            "left join registro r2 ON h2.id = r2.id_habitacion\n" +
            "where h2.id_hotel = :id_hotel \n" +
            "and ISNULL(r2.activa)\n" +
            "GROUP by h2.id", nativeQuery = true)
    List<Habitacion> findAllByReservasInactivas(@Param("id_hotel") Integer id_hotel);

    @Query(value = "SELECT h2.*  FROM habitacion h2 \n" +
            "left join registro r2 ON h2.id = r2.id_habitacion \n" +
            "where h2.id_hotel = :id_hotel \n" +
            "and r2.activa = 0 \n" +
            "GROUP by h2.id", nativeQuery = true)
    List<Habitacion> findAllByReservasInactivas2(@Param("id_hotel") Integer id_hotel);

    Habitacion findTopByRegistro(Registro registro);


}
