package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Pension;
import com.app.atlasultimate.model.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporadaRepository extends JpaRepository<Temporada, Integer> {

    @Query(value = "select *  from temporada where id = :id ", nativeQuery = true)
    Temporada temporadaPorId(@Param("id") Integer id);

    @Query(value = "select *  from temporada order by id desc limit 1 ", nativeQuery = true)
    Temporada ultimaTemporada();

    @Query(value = "select *  from temporada ", nativeQuery = true)
    List<Temporada> listaTemporadas();

}
