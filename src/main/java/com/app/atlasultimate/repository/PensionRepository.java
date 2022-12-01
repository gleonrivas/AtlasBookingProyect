package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Pension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PensionRepository extends JpaRepository<Pension, Integer> {
    @Query(value="select p.id from pension p where id_hotel = :id", nativeQuery = true)
    Integer pensionPorIdHotel(@Param("id") Integer id);


    @Query(value="select * from pension p  where p.id = :id", nativeQuery = true)
    Pension pensionPorId(@Param("id") Integer id);

    @Query(value ="select id from pension order by id desc limit 1",nativeQuery = true)
    Integer idUltimaPension();

    @Query(value ="select id_hotel from pension ",nativeQuery = true)
    List<Integer> listarPensionporidhotel();

    @Query(value ="select * from pension where id_hotel = :id_hotel",nativeQuery = true)
    Pension pensionPorHotel(@Param("id_hotel")Integer id_hotel);

/*
    @Query(value = "delete from pension where id_hotel = :id_hotel", nativeQuery = true)
    Pension eliminarPensionPorIdHotel(@Param("id_hotel")Integer id_hotel);

 */


}
