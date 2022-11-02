package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Pension;
import com.app.atlasultimate.model.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporadaRepository extends JpaRepository<Temporada, Integer> {

    @Query(value = "select *  from temporada where id = :id ", nativeQuery = true)
    Temporada temporadaPorId(@Param("id") Integer id);
}
