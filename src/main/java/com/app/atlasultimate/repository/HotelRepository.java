package com.app.atlasultimate.repository;


import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Pension;
import com.app.atlasultimate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel, Integer > {


    @Query(value = "SELECT * FROM hotel h where usuario = :id order by h.id DESC", nativeQuery = true)
    List<Hotel> findHotelById_usuario(@Param("id")Integer id);

    @Query(value = "SELECT * FROM hotel h where id = :id", nativeQuery = true)
    Hotel findHotelById(@Param("id")Integer id);

    @Query(value = "SELECT id_hotel FROM habitacion h where id = :id", nativeQuery = true)
    Integer findHotelByIdHab(@Param("id")Integer id);

    Hotel findTopById(Integer id);

    @Query(value = "SELECT * FROM hotel h", nativeQuery = true)
    List<Hotel> findAll();

    List<Hotel> findAllByCiudad(String ciudad);

    @Query(value = "SELECT h.* FROM hotel h \n" +
            "left JOIN review r on h.id = r.id_hotel\n" +
            "group by h.id order by r.estrellas desc", nativeQuery = true)
    List<Hotel> mejoresValorados();

    @Query(value = "SELECT h.* FROM hotel h left JOIN habitacion h2 on h.id = h2.id_hotel JOIN registro r " +
            "on h2.id = r.id_habitacion and :fecha_entrada" +
                    " not BETWEEN r.f_entrada and r.f_salida  " +
                    "and :fecha_salida not BETWEEN r.f_entrada  and r.f_salida " +
                    " and h2.n_max_personas >= :n_max_personas GROUP by h.id", nativeQuery = true)
    List<Hotel> mejoresValoradosPorReview(@Param("fecha_entrada") String fecha_entrada,
                                  @Param("fecha_salida") String fecha_salida,
                                  @Param("n_max_personas") Integer n_max_personas);


    @Query(value = "SELECT h.* FROM hotel h \n" +
            "group by h.id order by h.estrellas desc", nativeQuery = true)
    List<Hotel> mejoresValoradosPorHotel();

    @Query(value = "SELECT h.* FROM hotel h left JOIN habitacion h2 on h.id = h2.id_hotel JOIN registro r " +
            "on h2.id = r.id_habitacion and :fecha_entrada" +
            " not BETWEEN r.f_entrada and r.f_salida  " +
            "and :fecha_salida not BETWEEN r.f_entrada  and r.f_salida " +
            " and h2.n_max_personas >= :n_max_personas GROUP by h.id order by h.estrellas desc" , nativeQuery = true)
    List<Hotel> mejoresValoradosPorHotelBusqueda(@Param("fecha_entrada") String fecha_entrada,
                                            @Param("fecha_salida") String fecha_salida,
                                            @Param("n_max_personas") Integer n_max_personas);

    @Query(value = "SELECT h2.*  FROM registro r \n" +
            "join habitacion h on r.id_habitacion = h.id\n" +
            "JOIN  hotel h2 on h.id_hotel = h2.id\n" +
            "where  :fecha_inicio not BETWEEN r.f_entrada  and r.f_salida  \n" +
            "and :fecha_fin not BETWEEN r.f_entrada  and r.f_salida \n" +
            "and h2.ciudad like %:ciudad% \n" +
            "and h.n_max_personas >= :n_max_personas group by h2.id\n", nativeQuery = true)
    List<Hotel> buscadorgraphiql(@Param("fecha_inicio")  Date fecha_inicio,
                                  @Param("fecha_fin") Date fecha_fin,
                                  @Param("ciudad") String ciudad,
                                  @Param("n_max_personas") Integer n_max_personas);

    @Query(value = "UPDATE hotel set nombre = :nombre, ciudad = :ciudad, pais = :pais, " +
            "direccion = :direccion, estrellas = :estrellas," +
            "telefono = :telefono, email = :email, cancelacion_g = :cancelacion_g, img=:img, " +
            "wifi = :wifi, mascotas = :mascotas, multilengua = :multilengua, accesibilidad = :accesibilidad," +
            "s_habitacion = :s_habitacion, hc_recepcion = :hc_recepcion, hf_recepcion = :hf_recepcion," +
            "s_transporte = :s_transporte, tours = :tours, comedor = :comedor, espectaculos = :espectaculos, " +
            "patio = :patio, piscina = :piscina, terraza = :terraza, parking = :parking where id = :id", nativeQuery = true)
    Hotel updateByID(@Param("nombre") String nombre,
                       @Param("ciudad") String ciudad,
                       @Param("pais") String pais,
                       @Param("direccion") String direccion,
                       @Param("estrellas") Integer estrellas,
                       @Param("telefono") Integer telefono,
                       @Param("email") String email,
                       @Param("img") String img,
                       @Param("cancelacion_g") Boolean cancelacion_g,
                       @Param("wifi") Boolean wifi,
                       @Param("mascotas") Boolean mascotas,
                       @Param("multilengua") Boolean multilengua,
                       @Param("accesibilidad") Boolean accesibilidad,
                       @Param("s_habitacion") Boolean s_habitacion,
                       @Param("hc_recepcion") Time hc_recepcion,
                       @Param("hf_recepcion") Time hf_recepcion,
                       @Param("s_transporte") Boolean s_transporte,
                       @Param("tours") Boolean tours,
                       @Param("comedor") Boolean comedor,
                       @Param("espectaculos") Boolean espectaculos,
                       @Param("patio") Boolean patio,
                       @Param("piscina") Boolean piscina,
                       @Param("terraza") Boolean terraza,
                       @Param("parking") Boolean parking,
                       @Param("id") Integer id);

    /*saca las habitaciones filtrando por fecha reserva, capacidad de habitacion y ciudad*/
    @Query(value = "SELECT h.* FROM hotel h left JOIN habitacion h2 on h.id = h2.id_hotel JOIN registro r " +
            "on h2.id = r.id_habitacion and h.ciudad like %:ciudad% and :fecha_entrada" +
            " not BETWEEN r.f_entrada and r.f_salida  " +
            "and :fecha_salida not BETWEEN r.f_entrada  and r.f_salida " +
            " and h2.n_max_personas >= :n_max_personas GROUP by h.id", nativeQuery = true)
    List<Hotel> primerBuscador (@Param("fecha_entrada") String fecha_entrada,
                                     @Param("fecha_salida") String fecha_salida,
                                     @Param("ciudad") String ciudad,
                                     @Param("n_max_personas") Integer n_max_personas);


    /*saca las habitaciones que no tienen reserva, pero son de la ciudad y caben tantas personas*/
    @Query(value = "SELECT h2.* FROM habitacion h" +
            " left join registro r on r.id_habitacion = h.id" +
            "    join hotel h2 on h2.id = h.id_hotel" +
            "    where h.id not in (select r2.id_habitacion from registro r2" +
            "    group by r2.id_habitacion)" +
            "    and h2.ciudad like %:ciudad% and h.n_max_personas >=:n_max_personas group by h2.id", nativeQuery = true)
    List<Hotel> segundoBuscador ( @Param("ciudad") String ciudad,
                                  @Param("n_max_personas") Integer n_max_personas);


    @Query(value = "SELECT h2.* FROM habitacion h" +
            " left join registro r on r.id_habitacion = h.id" +
            "    join hotel h2 on h2.id = h.id_hotel" +
            "    where h.id not in (select r2.id_habitacion from registro r2" +
            "    group by r2.id_habitacion)" +
            "    and h.n_max_personas >=:n_max_personas group by h2.id", nativeQuery = true)
    List<Hotel> segundoBuscadorSinCiudad (@Param("n_max_personas") Integer n_max_personas);

    @Query(value = "SELECT * FROM hotel h \n" +
            "join habitacion h2 on h.id = h2.id_hotel\n" +
            "WHEre h2.id = :id_hab\n" +
            "GROUP by h.id\n" +
            "LIMIT 1", nativeQuery = true)
    Hotel findTopByidHabitacion(@Param("id_hab") Integer id_hab);




}
